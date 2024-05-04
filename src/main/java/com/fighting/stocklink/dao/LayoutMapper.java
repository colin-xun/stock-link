package com.fighting.stocklink.dao;

import com.fighting.stocklink.model.Layout;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LayoutMapper {
    int deleteByPrimaryKey(Integer layoutId);

    int insert(Layout record);

    int insertSelective(Layout record);

    Layout selectByPrimaryKey(Integer layoutId);

    int updateByPrimaryKeySelective(Layout record);

    int updateByPrimaryKeyWithBLOBs(Layout record);

    int updateByPrimaryKey(Layout record);

    List<Layout> findByUserId(@Param("userId") int userId);
}