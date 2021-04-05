package com.lagou.service;

import com.lagou.damain.PromotionSpace;

import java.util.List;

public interface PromotionSpaceService {
    /**
     * 获取所有广告位
     */
    public List<PromotionSpace> findAllPromotionSpace();
    //添加广告位
    public void savePromotionSpace(PromotionSpace promotionSpace);

    /**
     * 根据id查询广告为对象
     */
    public PromotionSpace findPromotionSpaceByID(int id);

    //更新广告位名称
    public void updatePromotionSpace(PromotionSpace promotionSpace);
}
