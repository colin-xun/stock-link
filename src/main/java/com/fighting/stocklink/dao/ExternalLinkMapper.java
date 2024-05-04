package com.fighting.stocklink.dao;

import com.fighting.stocklink.model.ExternalLink;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ExternalLinkMapper {
    int deleteByPrimaryKey(Integer linkId);

    int insert(ExternalLink record);

    int insertSelective(ExternalLink record);

    ExternalLink selectByPrimaryKey(Integer linkId);

    int updateByPrimaryKeySelective(ExternalLink record);

    int updateByPrimaryKey(ExternalLink record);

    List<ExternalLink> findByIsSystemDefinedTrue();
}