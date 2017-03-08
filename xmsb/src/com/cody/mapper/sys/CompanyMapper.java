package com.cody.mapper.sys;

import com.cody.entity.sys.Company;

public interface CompanyMapper {
    int deleteByPrimaryKey(String id);

    int insert(Company record);

    int insertSelective(Company record);

    Company selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Company record);

    int updateByPrimaryKey(Company record);
}