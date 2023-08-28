package com.weijia.im.service.user.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weijia.im.service.user.dao.ImUserDataEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface ImUserDataMapper extends BaseMapper<ImUserDataEntity> {
}