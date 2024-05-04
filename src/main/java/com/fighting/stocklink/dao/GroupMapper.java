package com.fighting.stocklink.dao;

import com.fighting.stocklink.model.Group;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GroupMapper {
    int deleteByPrimaryKey(Integer groupId);

    int insert(Group record);

    int insertSelective(Group record);

    Group selectByPrimaryKey(Integer groupId);

    int updateByPrimaryKeySelective(Group record);

    int updateByPrimaryKeyWithBLOBs(Group record);

    int updateByPrimaryKey(Group record);
}