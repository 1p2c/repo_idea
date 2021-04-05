package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.damain.Resource;
import com.lagou.damain.ResourceVo;

import java.util.List;

public interface ResourceService {
    /**
     * 资源分页及多条件查询
     */
    public PageInfo<Resource> findAllResourceByPage(ResourceVo resourceVo);
}
