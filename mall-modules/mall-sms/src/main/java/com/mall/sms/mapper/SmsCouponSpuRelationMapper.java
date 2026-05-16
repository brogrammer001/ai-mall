package com.mall.sms.mapper;

import java.util.List;
import com.mall.sms.domain.SmsCouponSpuRelation;

/**
 * 优惠券与产品关联Mapper接口
 * 
 * @author mall
 * @date 2026-04-11
 */
public interface SmsCouponSpuRelationMapper 
{
    /**
     * 查询优惠券与产品关联
     * 
     * @param id 优惠券与产品关联主键
     * @return 优惠券与产品关联
     */
    public SmsCouponSpuRelation selectSmsCouponSpuRelationById(Long id);

    /**
     * 查询优惠券与产品关联列表
     * 
     * @param smsCouponSpuRelation 优惠券与产品关联
     * @return 优惠券与产品关联集合
     */
    public List<SmsCouponSpuRelation> selectSmsCouponSpuRelationList(SmsCouponSpuRelation smsCouponSpuRelation);

    /**
     * 新增优惠券与产品关联
     * 
     * @param smsCouponSpuRelation 优惠券与产品关联
     * @return 结果
     */
    public int insertSmsCouponSpuRelation(SmsCouponSpuRelation smsCouponSpuRelation);

    /**
     * 修改优惠券与产品关联
     * 
     * @param smsCouponSpuRelation 优惠券与产品关联
     * @return 结果
     */
    public int updateSmsCouponSpuRelation(SmsCouponSpuRelation smsCouponSpuRelation);

    /**
     * 删除优惠券与产品关联
     * 
     * @param id 优惠券与产品关联主键
     * @return 结果
     */
    public int deleteSmsCouponSpuRelationById(Long id);

    /**
     * 批量删除优惠券与产品关联
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSmsCouponSpuRelationByIds(Long[] ids);
}
