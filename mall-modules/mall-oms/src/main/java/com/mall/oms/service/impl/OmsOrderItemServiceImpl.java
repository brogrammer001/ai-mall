package com.mall.oms.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mall.oms.mapper.OmsOrderItemMapper;
import com.mall.oms.domain.OmsOrderItem;
import com.mall.oms.service.IOmsOrderItemService;

/**
 * 订单项信息Service业务层处理
 * 
 * @author mall
 * @date 2026-04-11
 */
@Service
public class OmsOrderItemServiceImpl implements IOmsOrderItemService 
{
    @Autowired
    private OmsOrderItemMapper omsOrderItemMapper;

    /**
     * 查询订单项信息
     * 
     * @param id 订单项信息主键
     * @return 订单项信息
     */
    @Override
    public OmsOrderItem selectOmsOrderItemById(Long id)
    {
        return omsOrderItemMapper.selectOmsOrderItemById(id);
    }

    /**
     * 查询订单项信息列表
     * 
     * @param omsOrderItem 订单项信息
     * @return 订单项信息
     */
    @Override
    public List<OmsOrderItem> selectOmsOrderItemList(OmsOrderItem omsOrderItem)
    {
        return omsOrderItemMapper.selectOmsOrderItemList(omsOrderItem);
    }

    /**
     * 新增订单项信息
     * 
     * @param omsOrderItem 订单项信息
     * @return 结果
     */
    @Override
    public int insertOmsOrderItem(OmsOrderItem omsOrderItem)
    {
        return omsOrderItemMapper.insertOmsOrderItem(omsOrderItem);
    }

    /**
     * 修改订单项信息
     * 
     * @param omsOrderItem 订单项信息
     * @return 结果
     */
    @Override
    public int updateOmsOrderItem(OmsOrderItem omsOrderItem)
    {
        return omsOrderItemMapper.updateOmsOrderItem(omsOrderItem);
    }

    /**
     * 批量删除订单项信息
     * 
     * @param ids 需要删除的订单项信息主键
     * @return 结果
     */
    @Override
    public int deleteOmsOrderItemByIds(Long[] ids)
    {
        return omsOrderItemMapper.deleteOmsOrderItemByIds(ids);
    }

    /**
     * 删除订单项信息信息
     * 
     * @param id 订单项信息主键
     * @return 结果
     */
    @Override
    public int deleteOmsOrderItemById(Long id)
    {
        return omsOrderItemMapper.deleteOmsOrderItemById(id);
    }
}
