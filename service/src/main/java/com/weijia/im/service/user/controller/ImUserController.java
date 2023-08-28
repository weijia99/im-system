package com.weijia.im.service.user.controller;

import com.weijia.im.common.ResponseVO;
import com.weijia.im.service.user.model.req.ImportUserReq;
import com.weijia.im.service.user.service.ImUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/user")
public class ImUserController {
    @Autowired
    ImUserService imUserService;
    @RequestMapping("importUser")
    public ResponseVO importUser(@RequestBody ImportUserReq req, Integer appId){
//        req 是vo层
//        res统一返回
        req.setAppId(appId);
        return imUserService.importUser(req);
    }

}
