package com.lagou.mapper;

import com.lagou.damain.Resource;
import com.lagou.damain.ResourceVo;

import java.util.List;

public interface ResourceMapper {
    /**
     * 资源分页及多条件查询
      */
    public List<Resource> findAllResourceByPage(ResourceVo resourceVo);


}
