package com.mall.sms.service;

import java.util.List;
import com.mall.sms.domain.SmsSeckillSkuRelation;

/**
 * 秒杀活动商品关联Service接口
 * 
 * @author mall
 * @date 2026-04-11
 */
public interface ISmsSeckillSkuRelationService 
{
    /**
     * 查询秒杀活动商品关联
     * 
     * @param id 秒杀活动商品关联主键
     * @return 秒杀活动商品关联
     */
    public SmsSeckillSkuRelation selectSmsSeckillSkuRelationById(Long id);

    /**
     * 查询秒杀活动商品关联列表
     * 
     * @param smsSeckillSkuRelation 秒杀活动商品关联
     * @return 秒杀活动商品关联集合
     */
    public List<SmsSeckillSkuRelation> selectSmsSeckillSkuRelationList(SmsSeckillSkuRelation smsSeckillSkuRelation);

    /**
     * 新增秒杀活动商品关联
     * 
     * @param smsSeckillSkuRelation 秒杀活动商品关联
     * @return 结果
     */
    public int insertSmsSeckillSkuRelation(SmsSeckillSkuRelation smsSeckillSkuRelation);

    /**
     * 修改秒杀活动商品关联
     * 
     * @param smsSeckillSkuRelation 秒杀活动商品关联
     * @return 结果
     */
    public int updateSmsSeckillSkuRelation(SmsSeckillSkuRelation smsSeckillSkuRelation);

    /**
     * 批量删除秒杀活动商品关联
     * 
     * @param ids 需要删除的秒杀活动商品关联主键集合
     * @return 结果
     */
    public int deleteSmsSeckillSkuRelationByIds(Long[] ids);

    /**
     * 删除秒杀活动商品关联信息
     * 
     * @param id 秒杀活动商品关联主键
     * @return 结果
     */
    public int deleteSmsSeckillSkuRelationById(Long id);
}
