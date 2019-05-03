package com.xawl.perception.controller;

import com.xawl.perception.pojo.Image;
import com.xawl.perception.pojo.Mobile;
import com.xawl.perception.pojo.result.LoginConfirmResult;
import com.xawl.perception.pojo.result.LoginRequestResult;
import com.xawl.perception.pojo.result.QueryMobileResult;
import com.xawl.perception.pojo.result.SimpleResult;
import com.xawl.perception.service.MobileAcodeService;
import com.xawl.perception.service.MobileService;

import com.xawl.perception.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;


@RestController()
@RequestMapping("mobile")
public class MobileController {

    @Autowired
    private MobileService service;
    @Autowired
    private MobileAcodeService mobileAcodeService;

    @RequestMapping("request")
    public LoginRequestResult request(String account, HttpSession session) {
        if (StringUtil.isEmpty(account)) {
            return LoginRequestResult.getInstance(ResultCode.PARAMETER_ERROR, "");
        }
        if (!service.exist(account)) {
            return LoginRequestResult.getInstance(ResultCode.REQUEST_CLIENT_NOT_FOUND, "");
        }
        String random = RandomStringUtils.generateString(128);
        session.getServletContext().setAttribute("account=" + account, random);
        return LoginRequestResult.getInstance(ResultCode.SUCCESS, random);
    }

    @RequestMapping("out")
    public SimpleResult out(String account, String Acode) {
        if (StringUtil.isEmpty(account) || StringUtil.isEmpty(Acode)) {
            return SimpleResult.getInstance(ResultCode.PARAMETER_ERROR);
        }
        boolean b = mobileAcodeService.out(account, Acode);
        if (b) {
            return SimpleResult.getInstance(ResultCode.SUCCESS);
        }
        return SimpleResult.getInstance(ResultCode.LOGIN_OUT_FAIL);
    }

    @RequestMapping("confirm")
    public LoginConfirmResult confirm(String account, String confirm, HttpSession session) {
        if (StringUtil.isEmpty(account)) {
            return LoginConfirmResult.getInstance(ResultCode.PARAMETER_ERROR, "");
        }
        String random = (String) session.getServletContext().getAttribute("account=" + account);
        if (StringUtil.isEmpty(random)) {
            return LoginConfirmResult.getInstance(ResultCode.LOGIN_CONFIRM_NOT_REQUEST, "");
        } else {
            Mobile mobile = service.findByAccount(account);
            if (null == mobile) {
                return LoginConfirmResult.getInstance(ResultCode.LOGIN_CONFIRM_NOT_FOUND_CLIENT, "");
            }
            if (!confirm.equals(SHA256Utils.sha256(mobile.getPass(), random))) {
                return LoginConfirmResult.getInstance(ResultCode.CONFIRM_NO_PASS_FAIL, "");
            } else {
                String acode = RandomStringUtils.generateString(128);
                mobileAcodeService.add(mobile.getMid(), acode, new Date());
                return LoginConfirmResult.getInstance(ResultCode.SUCCESS, acode);
            }
        }
    }


    @RequestMapping("haveImg")
    public SimpleResult haveImg(String account, HttpServletRequest request) {
        Mobile mobile = service.findByAccount(account);
        SimpleResult instance = SimpleResult.getInstance(0);
        ServletContext context = request.getServletContext();
        Map<Integer, List<Image>> mq = (Map<Integer, List<Image>>) context.getAttribute("MQ");
        if (null == mq) {
            instance.setMsg("false");
        } else if (mq.containsKey(mobile.getMid())) {
            instance.setMsg("true");
        } else {
            instance.setMsg("false");
        }
        return instance;
    }

    @RequestMapping("getImg")
    public SimpleResult getImg(String account, HttpServletRequest request) {
        Mobile mobile = service.findByAccount(account);
        ServletContext context = request.getServletContext();
        Map<Integer, List<Image>> mq = (Map<Integer, List<Image>>) context.getAttribute("MQ");
        if (mq.containsKey(mobile.getMid())) {
            return SimpleResult.Success(mq.get(mobile.getMid()));
        } else {
            return null;
        }
    }

    @GetMapping("findAll")
    public SimpleResult findAll(Integer page, Mobile mobile) {
        if (null == page || page == 0) {
            List<Mobile> res = service.findAll();
            return SimpleResult.Success(res);
        }
        Page<Mobile> all = service.findAll(--page, mobile);
        PageInfo<Mobile> pageInfo = new PageUtils<Mobile>().getPageInfo(all);
        SimpleResult instance = SimpleResult.getInstance(0);
        instance.setData(pageInfo);
        return instance;
    }

    @PostMapping("add")
    public SimpleResult add(Mobile mobile) {
        if (StringUtil.isEmpty(mobile.getAccount())) {
            return SimpleResult.getInstance(ResultCode.MOBILE_ADD_ACCOUNT_NULL);
        }
        if (StringUtil.isEmpty(mobile.getMinfo())) {
            mobile.setMinfo("无");
        }
        if (StringUtil.isEmpty(mobile.getName())) {
            return SimpleResult.getInstance(ResultCode.MOBILE_ADD_NAME_NULL);
        }
        if (StringUtil.isEmpty(mobile.getPass())) {
            return SimpleResult.getInstance(ResultCode.MOBILE_ADD_PASS_NULL);
        }
        mobile.setInTime(new Date());
        service.add(mobile);
        return SimpleResult.Success(null);
    }

    @PostMapping("update")
    public SimpleResult update(Mobile mobile) {
        service.update(mobile);
        return SimpleResult.Success(null);
    }

    @PostMapping("delete")
    public SimpleResult delete(Integer mid) {
        service.delete(mid);
        return SimpleResult.Success(null);
    }
}
