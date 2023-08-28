package com.weijia.im.service.user.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.weijia.im.common.ResponseVO;
import com.weijia.im.common.enums.DelFlagEnum;
import com.weijia.im.common.enums.UserErrorCode;
import com.weijia.im.common.exception.ApplicationException;
import com.weijia.im.service.user.dao.ImUserDataEntity;
import com.weijia.im.service.user.dao.mapper.ImUserDataMapper;
import com.weijia.im.service.user.model.req.DeleteUserReq;
import com.weijia.im.service.user.model.req.GetUserInfoReq;
import com.weijia.im.service.user.model.req.ImportUserReq;
import com.weijia.im.service.user.model.req.ModifyUserInfoReq;
import com.weijia.im.service.user.model.resp.GetUserInfoResp;
import com.weijia.im.service.user.model.resp.ImportUserResp;
import com.weijia.im.service.user.service.ImUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//service字段也要标记

@Service
public class ImUserServiceImpl implements ImUserService {


    @Autowired
    public ImUserDataMapper imUserDataMapper;
    @Override
    public ResponseVO importUser(ImportUserReq req) {
//        return null;
        if(req.getUserData().size()>100){
//            传递的太多
//            todo：返回太多
        }
        List<String> successId=new ArrayList<>();
        List<String> errorId = new ArrayList<>();
        req.getUserData().forEach(e->{
//            插入元素值
            try {
                e.setAppId(req.getAppId());
                int insert = imUserDataMapper.insert(e);
                if(insert==1){
                    successId.add(e.getUserId());
//                    插入成功
                }

            }catch (Exception ex){
                ex.printStackTrace();
                errorId.add(e.getUserId());
            }
        });
        ImportUserResp resp =new ImportUserResp();
        resp.setErrorId(errorId);
        resp.setSuccessId(successId);
        return ResponseVO.successResponse(resp);
    }

    @Override
    public ResponseVO<GetUserInfoResp> getUserInfo(GetUserInfoReq req) {
        QueryWrapper<ImUserDataEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("app_id",req.getAppId());
        queryWrapper.in("user_id",req.getUserIds());
        queryWrapper.eq("del_flag", DelFlagEnum.NORMAL.getCode());

        List<ImUserDataEntity> userDataEntities = imUserDataMapper.selectList(queryWrapper);
        HashMap<String, ImUserDataEntity> map = new HashMap<>();

        for (ImUserDataEntity data:
                userDataEntities) {
            map.put(data.getUserId(),data);
        }

        List<String> failUser = new ArrayList<>();
        for (String uid:
                req.getUserIds()) {
            if(!map.containsKey(uid)){
                failUser.add(uid);
            }
        }

        GetUserInfoResp resp = new GetUserInfoResp();
        resp.setUserDataItem(userDataEntities);
        resp.setFailUser(failUser);
        return ResponseVO.successResponse(resp);
    }

    @Override
    public ResponseVO<ImUserDataEntity> getSingleUserInfo(String userId, Integer appId) {
        QueryWrapper<ImUserDataEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        queryWrapper.eq("app_id",appId);

        ImUserDataEntity imUserDataEntity = imUserDataMapper.selectOne(queryWrapper);
        if(ObjectUtil.isNull(imUserDataEntity)){
            return ResponseVO.errorResponse();
        }
        return ResponseVO.successResponse(imUserDataEntity);
    }




    /**
     * 删除用户
     * @param req
     * @return
     */
    @Override
    public ResponseVO deleteUser(DeleteUserReq req) {
        ImUserDataEntity entity = new ImUserDataEntity();
        entity.setDelFlag(DelFlagEnum.DELETE.getCode());

        List<String> errorId = new ArrayList();
        List<String> successId = new ArrayList();

        for (String userId:
                req.getUserId()) {
            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq("app_id",req.getAppId());
            wrapper.eq("user_id",userId);
            wrapper.eq("del_flag",DelFlagEnum.NORMAL.getCode());
            int update = 0;

            try {
                update =  imUserDataMapper.update(entity, wrapper);
                if(update > 0){
                    successId.add(userId);
                }else{
                    errorId.add(userId);
                }
            }catch (Exception e){
                errorId.add(userId);
            }
        }

        ImportUserResp resp = new ImportUserResp();
        resp.setSuccessId(successId);
        resp.setErrorId(errorId);
        return ResponseVO.successResponse(resp);
    }

    /**
     * 修改用户
     * @param req
     * @return
     */
    @Override
    @Transactional
    public ResponseVO modifyUserInfo(ModifyUserInfoReq req) {
        QueryWrapper query = new QueryWrapper<>();
        query.eq("app_id",req.getAppId());
        query.eq("user_id",req.getUserId());
        query.eq("del_flag",DelFlagEnum.NORMAL.getCode());
        ImUserDataEntity user = imUserDataMapper.selectOne(query);
        if(user == null){
            throw new ApplicationException(UserErrorCode.USER_IS_NOT_EXIST);
        }

        ImUserDataEntity update = new ImUserDataEntity();
        BeanUtils.copyProperties(req,update);

        update.setAppId(null);
        update.setUserId(null);
        int update1 = imUserDataMapper.update(update, query);

        //修改成功
        if(update1 == 1){



            return ResponseVO.successResponse();
        }
        throw new ApplicationException(UserErrorCode.MODIFY_USER_ERROR);
    }
}
