package com.xawl.perception.interceptor;

import com.xawl.perception.pojo.ClientAcode;
import com.xawl.perception.pojo.Mobile;
import com.xawl.perception.pojo.MobileAcode;
import com.xawl.perception.pojo.result.SimpleResult;
import com.xawl.perception.service.ClientAcodeService;
import com.xawl.perception.service.MobileAcodeService;
import com.xawl.perception.service.MobileService;
import com.xawl.perception.utils.JsonUtils;
import com.xawl.perception.utils.ResultCode;
import com.xawl.perception.utils.StringUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@Component
public class MobileRoleInterceptor implements HandlerInterceptor {
    private MobileAcodeService service;
    private MobileService mobileService;

    public MobileRoleInterceptor(MobileAcodeService service, MobileService mobileService) {
        this.service = service;
        this.mobileService = mobileService;
    }

    PrintWriter writer = null;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String account = request.getParameter("account");
        String acode = request.getParameter("Acode");
        Mobile mobile = mobileService.findByAccount(account);
        MobileAcode byId = service.findById(mobile.getMid());
        if (null == byId || StringUtil.isEmpty(byId.getAcode())) {
            try {
                writer = response.getWriter();
                writer.write(JsonUtils.objectToJson(SimpleResult.getInstance(ResultCode.ACODE_NO_EQUALS)));
                return false;
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (writer != null) {
                    writer.close();
                }
            }
        }
        if (!StringUtil.isEmpty(acode) && acode.equals(byId.getAcode())) {
            return true;
        } else {
            try {
                writer = response.getWriter();
                SimpleResult instance = SimpleResult.getInstance(ResultCode.ACODE_NO_EQUALS);
                writer.write(JsonUtils.objectToJson(instance));
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (writer != null) {
                    writer.close();
                }
            }
            return false;
        }
    }
}
