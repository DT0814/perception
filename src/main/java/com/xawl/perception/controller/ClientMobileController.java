package com.xawl.perception.controller;

import com.xawl.perception.pojo.Admin;
import com.xawl.perception.pojo.Client;
import com.xawl.perception.pojo.ClientMobile;
import com.xawl.perception.pojo.Mobile;
import com.xawl.perception.pojo.result.SimpleResult;
import com.xawl.perception.service.AdminService;
import com.xawl.perception.service.ClientMobileService;
import com.xawl.perception.service.ClientService;
import com.xawl.perception.service.MobileService;
import com.xawl.perception.utils.ResultCode;
import com.xawl.perception.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("cliMob")
public class ClientMobileController {
    @Autowired
    private ClientMobileService service;
    @Autowired
    private ClientService clientService;
    @Autowired
    private MobileService mobileService;

    @GetMapping("findAll")
    public SimpleResult findAll(ClientMobile clientMobile) {
        List<ClientMobile> res = service.findAll(clientMobile);
        return SimpleResult.Success(res);
    }

    @PostMapping("add")
    public SimpleResult add(ClientMobile clientMobile) {
        Client ClibyId = clientService.findById(clientMobile.getCid());
        Mobile mobById = mobileService.findById(clientMobile.getMid());
        clientMobile.setcAccount(ClibyId.getAccount());
        clientMobile.setmAccount(mobById.getAccount());
        service.add(clientMobile);
        return SimpleResult.Success(null);
    }

    @PostMapping("delete")
    public SimpleResult delete(ClientMobile clientMobile) {
        service.delete(clientMobile);
        return SimpleResult.Success(null);
    }
}
