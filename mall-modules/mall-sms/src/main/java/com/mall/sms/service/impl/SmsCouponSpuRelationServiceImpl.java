package com.mall.sms.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mall.sms.mapper.SmsCouponSpuRelationMapper;
import com.mall.sms.domain.SmsCouponSpuRelation;
import com.mall.sms.service.ISmsCouponSpuRelationService;

/**
 * 优惠券与产品关联Service业务层处理
 * 
 * @author mall
 * @date 2026-04-11
 */
@Service
public class SmsCouponSpuRelationServiceImpl implements ISmsCouponSpuRelationService 
{
    @Autowired
    private SmsCouponSpuRelationMapper smsCouponSpuRelationMapper;

    /**
     * 查询优惠券与产品关联
     * 
     * @param id 优惠券与产品关联主键
     * @return 优惠券与产品关联
     */
    @Override
    public SmsCouponSpuRelation selectSmsCouponSpuRelationById(Long id)
    {
        return smsCouponSpuRelationMapper.selectSmsCouponSpuRelationById(id);
    }

    /**
     * 查询优惠券与产品关联列表
     * 
     * @param smsCouponSpuRelation 优惠券与产品关联
     * @return 优惠券与产品关联
     */
    @Override
    public List<SmsCouponSpuRelation> selectSmsCouponSpuRelationList(SmsCouponSpuRelation smsCouponSpuRelation)
    {
        return smsCouponSpuRelationMapper.selectSmsCouponSpuRelationList(smsCouponSpuRelation);
    }

    /**
     * 新增优惠券与产品关联
     * 
     * @param smsCouponSpuRelation 优惠券与产品关联
     * @return 结果
     */
    @Override
    public int insertSmsCouponSpuRelation(SmsCouponSpuRelation smsCouponSpuRelation)
    {
        return smsCouponSpuRelationMapper.insertSmsCouponSpuRelation(smsCouponSpuRelation);
    }

    /**
     * 修改优惠券与产品关联
     * 
     * @param smsCouponSpuRelation 优惠券与产品关联
     * @return 结果
     */
    @Override
    public int updateSmsCouponSpuRelation(SmsCouponSpuRelation smsCouponSpuRelation)
    {
        return smsCouponSpuRelationMapper.updateSmsCouponSpuRelation(smsCouponSpuRelation);
    }

    /**
     * 批量删除优惠券与产品关联
     * 
     * @param ids 需要删除的优惠券与产品关联主键
     * @return 结果
     */
    @Override
    public int deleteSmsCouponSpuRelationByIds(Long[] ids)
    {
        return smsCouponSpuRelationMapper.deleteSmsCouponSpuRelationByIds(ids);
    }

    /**
     * 删除优惠券与产品关联信息
     * 
     * @param id 优惠券与产品关联主键
     * @return 结果
     */
    @Override
    public int deleteSmsCouponSpuRelationById(Long id)
    {
        return smsCouponSpuRelationMapper.deleteSmsCouponSpuRelationById(id);
    }
}
