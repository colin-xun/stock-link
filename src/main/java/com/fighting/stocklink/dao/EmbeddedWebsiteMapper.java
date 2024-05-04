package com.fighting.stocklink.dao;

import com.fighting.stocklink.model.EmbeddedWebsite;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmbeddedWebsiteMapper {
    int deleteByPrimaryKey(Integer websiteId);

    int insert(EmbeddedWebsite record);

    int insertSelective(EmbeddedWebsite record);

    EmbeddedWebsite selectByPrimaryKey(Integer websiteId);

    int updateByPrimaryKeySelective(EmbeddedWebsite record);

    int updateByPrimaryKey(EmbeddedWebsite record);
}