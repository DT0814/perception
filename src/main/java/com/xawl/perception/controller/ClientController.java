package com.xawl.perception.controller;

import com.xawl.perception.pojo.Client;
import com.xawl.perception.pojo.ClientMobile;
import com.xawl.perception.pojo.Image;
import com.xawl.perception.pojo.result.LoginConfirmResult;
import com.xawl.perception.pojo.result.LoginRequestResult;
import com.xawl.perception.pojo.result.SimpleResult;
import com.xawl.perception.service.ClientAcodeService;
import com.xawl.perception.service.ClientMobileService;
import com.xawl.perception.service.ClientService;
import com.xawl.perception.service.ImageService;
import com.xawl.perception.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("cli")
public class ClientController {
    @Autowired
    private ClientService service;
    @Autowired
    private ImageService imageService;
    @Autowired
    private ClientMobileService clientMobileService;

    @Autowired
    private ClientAcodeService clientAcodeService;
    private final static String uploadPath = "images/";

    @GetMapping("findAll")
    public SimpleResult findAll(Integer page, Client client) {
        if (null == page || page == 0) {
            List<Client> res = service.findAll();
            return SimpleResult.Success(res);
        } else {
            Page<Client> all = service.findAll(--page, client);
            PageInfo<Client> pageInfo = new PageUtils<Client>().getPageInfo(all);
            SimpleResult instance = SimpleResult.getInstance(0);
            instance.setData(pageInfo);
            return instance;
        }
    }

    @RequestMapping("request")
    public LoginRequestResult request(Integer cid, HttpSession session) {
        if (null == cid || cid <= 0) {
            return LoginRequestResult.getInstance(ResultCode.PARAMETER_ERROR, "");
        }
        if (!service.exist(cid)) {
            return LoginRequestResult.getInstance(ResultCode.REQUEST_CLIENT_NOT_FOUND, "");
        }
        String random = RandomStringUtils.generateString(128);
        session.getServletContext().setAttribute("cid=" + cid, random);
        return LoginRequestResult.getInstance(ResultCode.SUCCESS, random);
    }

    @RequestMapping("out")
    public SimpleResult out(Integer cid, String Acode) {
        if (null == cid || cid <= 0 || StringUtil.isEmpty(Acode)) {
            return SimpleResult.getInstance(ResultCode.PARAMETER_ERROR);
        }
        boolean b = clientAcodeService.out(cid, Acode);
        if (b) {
            return SimpleResult.getInstance(ResultCode.SUCCESS);
        }
        return SimpleResult.getInstance(ResultCode.LOGIN_OUT_FAIL);
    }

    @RequestMapping("confirm")
    public LoginConfirmResult confirm(Integer cid, String confirm, HttpSession session) {
        if (null == cid || cid <= 0) {
            return LoginConfirmResult.getInstance(ResultCode.PARAMETER_ERROR, "");
        }
        String random = (String) session.getServletContext().getAttribute("cid=" + cid);
        if (StringUtil.isEmpty(random)) {
            return LoginConfirmResult.getInstance(ResultCode.LOGIN_CONFIRM_NOT_REQUEST, "");
        } else {
            Client client = service.findById(cid);
            if (null == client) {
                return LoginConfirmResult.getInstance(ResultCode.LOGIN_CONFIRM_NOT_FOUND_CLIENT, "");
            }
            if (!confirm.equals(SHA256Utils.sha256(client.getPass(), random))) {
                return LoginConfirmResult.getInstance(ResultCode.CONFIRM_NO_PASS_FAIL, "");
            } else {
                String acode = RandomStringUtils.generateString(128);
                clientAcodeService.add(cid, acode, new Date());
                return LoginConfirmResult.getInstance(ResultCode.SUCCESS, acode);
            }
        }
    }

    @RequestMapping("add")
    public SimpleResult add(Client client) {
        if (StringUtil.isEmpty(client.getInfo())) {
            client.setInfo("无");
        }
        service.add(client);
        return SimpleResult.getInstance(ResultCode.SUCCESS);
    }

    @RequestMapping("delete")
    public SimpleResult delete(Client client) {
        service.delete(client.getCid());
        return SimpleResult.getInstance(ResultCode.SUCCESS);
    }

    @RequestMapping("update")
    public SimpleResult update(Client client) {
        service.update(client);
        return SimpleResult.getInstance(ResultCode.SUCCESS);
    }

    @RequestMapping("register")
    public SimpleResult register(Client client) {
        service.add(client);
        return SimpleResult.getInstance(ResultCode.SUCCESS);
    }

    @PostMapping("putImg")
    public SimpleResult putImg(Integer cid, String base64Img, HttpServletRequest request) {
        Client client = service.findById(cid);
        base64Img = base64Img.replaceAll(" ", "+");
        String imgName = UUID.randomUUID().toString().replaceAll("-", "");
        String path = Base64ToImg(base64Img, imgName);
        Image image = new Image();
        image.setCid(cid);
        image.setUrl("/" + path);
        image.setcAccount(client.getAccount());
        image.setInTime(new Date());
        image = imageService.add(image);
        ClientMobile clientMobile = new ClientMobile();
        clientMobile.setCid(client.getCid());
        List<ClientMobile> all = clientMobileService.findAll(clientMobile);
        if (null != all && all.size() > 0) {
            ServletContext context = request.getServletContext();
            Map<Integer, List<Image>> mq = (Map<Integer, List<Image>>) context.getAttribute("MQ");
            if (null == mq) {
                mq = new HashMap<>();
            }
            for (ClientMobile obj : all) {
                Integer mid = obj.getMid();
                if (mq.containsKey(mid)) {
                    List<Image> list = mq.get(mid);
                    if (null == list) {
                        list = new ArrayList<>();
                    }
                    list.add(image);
                } else {
                    List<Image> list = new ArrayList<>();
                    list.add(image);
                    mq.put(mid, list);
                }
            }
            context.setAttribute("MQ", mq);
        }
        return SimpleResult.getInstance(ResultCode.SUCCESS);
    }

    private String Base64ToImg(String base64, String name) {
        String path = uploadPath + new SimpleDateFormat("yyyyMMdd").format(new Date()) + "/" + name + ".jpg";
        Base64ImageUtils.Base64ToImage(base64, path);
        return path;
    }
}