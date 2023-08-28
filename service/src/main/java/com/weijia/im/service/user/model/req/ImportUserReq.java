package com.weijia.im.service.user.model.req;

import com.weijia.im.common.model.RequestBase;
import com.weijia.im.service.user.dao.ImUserDataEntity;
import lombok.Data;

import java.util.List;

//与传入的api一一对应参数
@Data
public class ImportUserReq extends RequestBase {
    private List<ImUserDataEntity> userData;
}
