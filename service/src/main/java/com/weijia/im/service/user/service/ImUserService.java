package com.weijia.im.service.user.service;

import com.weijia.im.common.ResponseVO;
import com.weijia.im.service.user.dao.ImUserDataEntity;
import com.weijia.im.service.user.model.req.DeleteUserReq;
import com.weijia.im.service.user.model.req.GetUserInfoReq;
import com.weijia.im.service.user.model.req.ImportUserReq;
import com.weijia.im.service.user.model.req.ModifyUserInfoReq;
import com.weijia.im.service.user.model.resp.GetUserInfoResp;

public interface ImUserService {
    public ResponseVO importUser(ImportUserReq req);
    public ResponseVO<GetUserInfoResp> getUserInfo(GetUserInfoReq req);

    public ResponseVO<ImUserDataEntity> getSingleUserInfo(String userId , Integer appId);

    public ResponseVO deleteUser(DeleteUserReq req);

    public ResponseVO modifyUserInfo(ModifyUserInfoReq req);

}
