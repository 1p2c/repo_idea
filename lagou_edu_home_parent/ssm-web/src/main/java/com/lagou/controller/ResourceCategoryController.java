package com.lagou.controller;

import com.lagou.damain.ResourceCategory;
import com.lagou.damain.ResponseResult;
import com.lagou.service.ResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ResourceCategory")
public class ResourceCategoryController {

    @Autowired
    private ResourceCategoryService resourceCategoryService;

    @RequestMapping("/findAllResourceCategory")
    public ResponseResult findAllResourceCategory(){

        List<ResourceCategory> allResourceCategory = resourceCategoryService.findAllResourceCategory();
        return new ResponseResult(true,200,"查询资源响应成功",allResourceCategory);

    }
}
