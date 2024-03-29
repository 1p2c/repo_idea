package com.lagou.service.Impl;

import com.lagou.damain.Test;
import com.lagou.mapper.TestMapper;
import com.lagou.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestMapper testMapper;

    @Override
    public List<Test> findAll() {
        List<Test> all = testMapper.findAll();
        return all;
    }
}
