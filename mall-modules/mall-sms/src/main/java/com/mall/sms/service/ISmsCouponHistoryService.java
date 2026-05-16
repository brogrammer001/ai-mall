package com.mall.sms.service;

import java.util.List;
import com.mall.sms.domain.SmsCouponHistory;

/**
 * 优惠券领取历史记录Service接口
 * 
 * @author mall
 * @date 2026-04-11
 */
public interface ISmsCouponHistoryService 
{
    /**
     * 查询优惠券领取历史记录
     * 
     * @param id 优惠券领取历史记录主键
     * @return 优惠券领取历史记录
     */
    public SmsCouponHistory selectSmsCouponHistoryById(Long id);

    /**
     * 查询优惠券领取历史记录列表
     * 
     * @param smsCouponHistory 优惠券领取历史记录
     * @return 优惠券领取历史记录集合
     */
    public List<SmsCouponHistory> selectSmsCouponHistoryList(SmsCouponHistory smsCouponHistory);

    /**
     * 新增优惠券领取历史记录
     * 
     * @param smsCouponHistory 优惠券领取历史记录
     * @return 结果
     */
    public int insertSmsCouponHistory(SmsCouponHistory smsCouponHistory);

    /**
     * 修改优惠券领取历史记录
     * 
     * @param smsCouponHistory 优惠券领取历史记录
     * @return 结果
     */
    public int updateSmsCouponHistory(SmsCouponHistory smsCouponHistory);

    /**
     * 批量删除优惠券领取历史记录
     * 
     * @param ids 需要删除的优惠券领取历史记录主键集合
     * @return 结果
     */
    public int deleteSmsCouponHistoryByIds(Long[] ids);

    /**
     * 删除优惠券领取历史记录信息
     * 
     * @param id 优惠券领取历史记录主键
     * @return 结果
     */
    public int deleteSmsCouponHistoryById(Long id);
}
