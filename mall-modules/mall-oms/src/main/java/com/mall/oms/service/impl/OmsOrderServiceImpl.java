package com.mall.oms.service.impl;

import java.util.List;
import com.mall.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mall.oms.mapper.OmsOrderMapper;
import com.mall.oms.domain.OmsOrder;
import com.mall.oms.service.IOmsOrderService;

/**
 * 订单Service业务层处理
 * 
 * @author mall
 * @date 2026-04-11
 */
@Service
public class OmsOrderServiceImpl implements IOmsOrderService 
{
    @Autowired
    private OmsOrderMapper omsOrderMapper;

    /**
     * 查询订单
     * 
     * @param id 订单主键
     * @return 订单
     */
    @Override
    public OmsOrder selectOmsOrderById(Long id)
    {
        return omsOrderMapper.selectOmsOrderById(id);
    }

    /**
     * 查询订单列表
     * 
     * @param omsOrder 订单
     * @return 订单
     */
    @Override
    public List<OmsOrder> selectOmsOrderList(OmsOrder omsOrder)
    {
        return omsOrderMapper.selectOmsOrderList(omsOrder);
    }

    /**
     * 新增订单
     * 
     * @param omsOrder 订单
     * @return 结果
     */
    @Override
    public int insertOmsOrder(OmsOrder omsOrder)
    {
        omsOrder.setCreateTime(DateUtils.getNowDate());
        return omsOrderMapper.insertOmsOrder(omsOrder);
    }

    /**
     * 修改订单
     * 
     * @param omsOrder 订单
     * @return 结果
     */
    @Override
    public int updateOmsOrder(OmsOrder omsOrder)
    {
        return omsOrderMapper.updateOmsOrder(omsOrder);
    }

    /**
     * 批量删除订单
     * 
     * @param ids 需要删除的订单主键
     * @return 结果
     */
    @Override
    public int deleteOmsOrderByIds(Long[] ids)
    {
        return omsOrderMapper.deleteOmsOrderByIds(ids);
    }

    /**
     * 删除订单信息
     * 
     * @param id 订单主键
     * @return 结果
     */
    @Override
    public int deleteOmsOrderById(Long id)
    {
        return omsOrderMapper.deleteOmsOrderById(id);
    }
}
