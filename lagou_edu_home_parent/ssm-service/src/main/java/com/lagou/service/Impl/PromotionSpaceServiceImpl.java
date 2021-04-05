package com.lagou.service.Impl;

import com.lagou.damain.PromotionSpace;
import com.lagou.mapper.PromotionSpaceMapper;
import com.lagou.service.PromotionSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class PromotionSpaceServiceImpl implements PromotionSpaceService {

    @Autowired
    private PromotionSpaceMapper promotionSpaceMapper;
    @Override
    public List<PromotionSpace> findAllPromotionSpace() {
        List<PromotionSpace> list = promotionSpaceMapper.findAllPromotionSpace();

        return list;
    }

    @Override
    public void savePromotionSpace(PromotionSpace promotionSpace) {

        //1.封装数据
        Date date = new Date();
        promotionSpace.setCreateTime(date);
        promotionSpace.setSpaceKey(UUID.randomUUID().toString());
        promotionSpace.setUpdateTime(date);
        promotionSpace.setIsDel(0);
        //2.调用mapper方法
        promotionSpaceMapper.savePromotionSpace(promotionSpace);
    }

    @Override
    public PromotionSpace findPromotionSpaceByID(int id) {
        PromotionSpace promotionSpace = promotionSpaceMapper.findPromotionSpaceByID(id);

        return promotionSpace;
    }

    /**
     * 修改广告位名称
     * @param promotionSpace
     */
    @Override
    public void updatePromotionSpace(PromotionSpace promotionSpace) {
        //1.封装数据
            promotionSpace.setUpdateTime(new Date());
        //2.调用mapper
        promotionSpaceMapper.updatePromotionSpace(promotionSpace);
    }


}
