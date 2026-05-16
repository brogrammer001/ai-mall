package com.mall.wms.service;

import java.util.List;
import com.mall.wms.domain.WmsPurchase;

/**
 * 采购信息Service接口
 * 
 * @author mall
 * @date 2026-04-11
 */
public interface IWmsPurchaseService 
{
    /**
     * 查询采购信息
     * 
     * @param id 采购信息主键
     * @return 采购信息
     */
    public WmsPurchase selectWmsPurchaseById(Long id);

    /**
     * 查询采购信息列表
     * 
     * @param wmsPurchase 采购信息
     * @return 采购信息集合
     */
    public List<WmsPurchase> selectWmsPurchaseList(WmsPurchase wmsPurchase);

    /**
     * 新增采购信息
     * 
     * @param wmsPurchase 采购信息
     * @return 结果
     */
    public int insertWmsPurchase(WmsPurchase wmsPurchase);

    /**
     * 修改采购信息
     * 
     * @param wmsPurchase 采购信息
     * @return 结果
     */
    public int updateWmsPurchase(WmsPurchase wmsPurchase);

    /**
     * 批量删除采购信息
     * 
     * @param ids 需要删除的采购信息主键集合
     * @return 结果
     */
    public int deleteWmsPurchaseByIds(Long[] ids);

    /**
     * 删除采购信息信息
     * 
     * @param id 采购信息主键
     * @return 结果
     */
    public int deleteWmsPurchaseById(Long id);
}
