package com.mall.sms.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mall.sms.mapper.SmsCouponMapper;
import com.mall.sms.domain.SmsCoupon;
import com.mall.sms.service.ISmsCouponService;

/**
 * 优惠券信息Service业务层处理
 * 
 * @author mall
 * @date 2026-04-11
 */
@Service
public class SmsCouponServiceImpl implements ISmsCouponService 
{
    @Autowired
    private SmsCouponMapper smsCouponMapper;

    /**
     * 查询优惠券信息
     * 
     * @param id 优惠券信息主键
     * @return 优惠券信息
     */
    @Override
    public SmsCoupon selectSmsCouponById(Long id)
    {
        return smsCouponMapper.selectSmsCouponById(id);
    }

    /**
     * 查询优惠券信息列表
     * 
     * @param smsCoupon 优惠券信息
     * @return 优惠券信息
     */
    @Override
    public List<SmsCoupon> selectSmsCouponList(SmsCoupon smsCoupon)
    {
        return smsCouponMapper.selectSmsCouponList(smsCoupon);
    }

    /**
     * 新增优惠券信息
     * 
     * @param smsCoupon 优惠券信息
     * @return 结果
     */
    @Override
    public int insertSmsCoupon(SmsCoupon smsCoupon)
    {
        return smsCouponMapper.insertSmsCoupon(smsCoupon);
    }

    /**
     * 修改优惠券信息
     * 
     * @param smsCoupon 优惠券信息
     * @return 结果
     */
    @Override
    public int updateSmsCoupon(SmsCoupon smsCoupon)
    {
        return smsCouponMapper.updateSmsCoupon(smsCoupon);
    }

    /**
     * 批量删除优惠券信息
     * 
     * @param ids 需要删除的优惠券信息主键
     * @return 结果
     */
    @Override
    public int deleteSmsCouponByIds(Long[] ids)
    {
        return smsCouponMapper.deleteSmsCouponByIds(ids);
    }

    /**
     * 删除优惠券信息信息
     * 
     * @param id 优惠券信息主键
     * @return 结果
     */
    @Override
    public int deleteSmsCouponById(Long id)
    {
        return smsCouponMapper.deleteSmsCouponById(id);
    }
}
