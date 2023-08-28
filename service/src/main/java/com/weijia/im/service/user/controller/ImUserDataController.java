package com.weijia.im.service.user.controller;

import com.weijia.im.common.ResponseVO;
import com.weijia.im.service.user.model.req.DeleteUserReq;
import com.weijia.im.service.user.model.req.GetUserInfoReq;
import com.weijia.im.service.user.model.req.ModifyUserInfoReq;
import com.weijia.im.service.user.service.ImUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/user/data")
public class ImUserDataController {
    @Autowired
    private ImUserService userService;

    @PostMapping("getUserInfo")
    public ResponseVO getUserInfo(@RequestBody GetUserInfoReq userInfoReq, Integer appId){
        userInfoReq.setAppId(appId);
        return userService.getUserInfo(userInfoReq);
    }
    @PostMapping("deleteUser")
    public ResponseVO deleteUser(@RequestBody DeleteUserReq userReq, Integer appId){
        userReq.setAppId(appId);
        return userService.deleteUser(userReq);
    }
    @PostMapping("update")
    public ResponseVO updateUser(@RequestBody ModifyUserInfoReq userInfoReq, Integer appId){
        userInfoReq.setAppId(appId);
        return userService.modifyUserInfo(userInfoReq);
    }
}
