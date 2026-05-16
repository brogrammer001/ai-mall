package com.mall.sms.mapper;

import java.util.List;
import com.mall.sms.domain.SmsSeckillPromotion;

/**
 * 秒杀活动Mapper接口
 * 
 * @author mall
 * @date 2026-04-11
 */
public interface SmsSeckillPromotionMapper 
{
    /**
     * 查询秒杀活动
     * 
     * @param id 秒杀活动主键
     * @return 秒杀活动
     */
    public SmsSeckillPromotion selectSmsSeckillPromotionById(Long id);

    /**
     * 查询秒杀活动列表
     * 
     * @param smsSeckillPromotion 秒杀活动
     * @return 秒杀活动集合
     */
    public List<SmsSeckillPromotion> selectSmsSeckillPromotionList(SmsSeckillPromotion smsSeckillPromotion);

    /**
     * 新增秒杀活动
     * 
     * @param smsSeckillPromotion 秒杀活动
     * @return 结果
     */
    public int insertSmsSeckillPromotion(SmsSeckillPromotion smsSeckillPromotion);

    /**
     * 修改秒杀活动
     * 
     * @param smsSeckillPromotion 秒杀活动
     * @return 结果
     */
    public int updateSmsSeckillPromotion(SmsSeckillPromotion smsSeckillPromotion);

    /**
     * 删除秒杀活动
     * 
     * @param id 秒杀活动主键
     * @return 结果
     */
    public int deleteSmsSeckillPromotionById(Long id);

    /**
     * 批量删除秒杀活动
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSmsSeckillPromotionByIds(Long[] ids);
}
