package com.xawl.perception.controller;

import com.xawl.perception.pojo.Admin;
import com.xawl.perception.pojo.result.SimpleResult;
import com.xawl.perception.service.AdminService;
import com.xawl.perception.utils.ResultCode;
import com.xawl.perception.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private AdminService service;

    @PostMapping("login")
    public SimpleResult login(Admin admin) {
        if (StringUtil.isEmpty(admin.getAccount())) {
            return SimpleResult.getInstance(ResultCode.ADMIN_LOGIN_ACCOUNT_NULL);
        }
        Admin byAccount = service.findByAccount(admin.getAccount());
        if (!byAccount.getPass().equals(admin.getPass())) {
            return SimpleResult.getInstance(ResultCode.ADMIN_LOGIN_PASS_ERROT);
        }
        return SimpleResult.Success(byAccount);
    }
}
