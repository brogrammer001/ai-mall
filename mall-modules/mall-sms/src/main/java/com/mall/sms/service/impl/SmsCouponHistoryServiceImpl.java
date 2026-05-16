package com.mall.sms.service.impl;

import java.util.List;
import com.mall.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mall.sms.mapper.SmsCouponHistoryMapper;
import com.mall.sms.domain.SmsCouponHistory;
import com.mall.sms.service.ISmsCouponHistoryService;

/**
 * 优惠券领取历史记录Service业务层处理
 * 
 * @author mall
 * @date 2026-04-11
 */
@Service
public class SmsCouponHistoryServiceImpl implements ISmsCouponHistoryService 
{
    @Autowired
    private SmsCouponHistoryMapper smsCouponHistoryMapper;

    /**
     * 查询优惠券领取历史记录
     * 
     * @param id 优惠券领取历史记录主键
     * @return 优惠券领取历史记录
     */
    @Override
    public SmsCouponHistory selectSmsCouponHistoryById(Long id)
    {
        return smsCouponHistoryMapper.selectSmsCouponHistoryById(id);
    }

    /**
     * 查询优惠券领取历史记录列表
     * 
     * @param smsCouponHistory 优惠券领取历史记录
     * @return 优惠券领取历史记录
     */
    @Override
    public List<SmsCouponHistory> selectSmsCouponHistoryList(SmsCouponHistory smsCouponHistory)
    {
        return smsCouponHistoryMapper.selectSmsCouponHistoryList(smsCouponHistory);
    }

    /**
     * 新增优惠券领取历史记录
     * 
     * @param smsCouponHistory 优惠券领取历史记录
     * @return 结果
     */
    @Override
    public int insertSmsCouponHistory(SmsCouponHistory smsCouponHistory)
    {
        smsCouponHistory.setCreateTime(DateUtils.getNowDate());
        return smsCouponHistoryMapper.insertSmsCouponHistory(smsCouponHistory);
    }

    /**
     * 修改优惠券领取历史记录
     * 
     * @param smsCouponHistory 优惠券领取历史记录
     * @return 结果
     */
    @Override
    public int updateSmsCouponHistory(SmsCouponHistory smsCouponHistory)
    {
        return smsCouponHistoryMapper.updateSmsCouponHistory(smsCouponHistory);
    }

    /**
     * 批量删除优惠券领取历史记录
     * 
     * @param ids 需要删除的优惠券领取历史记录主键
     * @return 结果
     */
    @Override
    public int deleteSmsCouponHistoryByIds(Long[] ids)
    {
        return smsCouponHistoryMapper.deleteSmsCouponHistoryByIds(ids);
    }

    /**
     * 删除优惠券领取历史记录信息
     * 
     * @param id 优惠券领取历史记录主键
     * @return 结果
     */
    @Override
    public int deleteSmsCouponHistoryById(Long id)
    {
        return smsCouponHistoryMapper.deleteSmsCouponHistoryById(id);
    }
}
