package com.lagou.mapper;

import com.lagou.damain.Resource;
import com.lagou.damain.ResourceCategory;

import java.util.List;

public interface ResourceCategoryMapper {
    /**
     * 查询所有资源分类
     */
    public List<ResourceCategory> findAllResourceCategory();

}
