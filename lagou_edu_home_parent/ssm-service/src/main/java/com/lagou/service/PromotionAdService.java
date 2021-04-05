package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.damain.PromotionAd;
import com.lagou.damain.PromotionAdVo;

import java.util.List;

public interface PromotionAdService {
    /**
     * 分页查询广告信息
     */
    public PageInfo<PromotionAd> findAllPromotionAdByPage(PromotionAdVo promotionAdVo);
    /**
     * 广告动态上下线
     */
    public void updatePromotionAdStatus(int id,int status);
}
