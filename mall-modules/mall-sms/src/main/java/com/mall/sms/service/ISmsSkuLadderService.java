package com.mall.sms.service;

import java.util.List;
import com.mall.sms.domain.SmsSkuLadder;

/**
 * 商品阶梯价格Service接口
 * 
 * @author mall
 * @date 2026-04-11
 */
public interface ISmsSkuLadderService 
{
    /**
     * 查询商品阶梯价格
     * 
     * @param id 商品阶梯价格主键
     * @return 商品阶梯价格
     */
    public SmsSkuLadder selectSmsSkuLadderById(Long id);

    /**
     * 查询商品阶梯价格列表
     * 
     * @param smsSkuLadder 商品阶梯价格
     * @return 商品阶梯价格集合
     */
    public List<SmsSkuLadder> selectSmsSkuLadderList(SmsSkuLadder smsSkuLadder);

    /**
     * 新增商品阶梯价格
     * 
     * @param smsSkuLadder 商品阶梯价格
     * @return 结果
     */
    public int insertSmsSkuLadder(SmsSkuLadder smsSkuLadder);

    /**
     * 修改商品阶梯价格
     * 
     * @param smsSkuLadder 商品阶梯价格
     * @return 结果
     */
    public int updateSmsSkuLadder(SmsSkuLadder smsSkuLadder);

    /**
     * 批量删除商品阶梯价格
     * 
     * @param ids 需要删除的商品阶梯价格主键集合
     * @return 结果
     */
    public int deleteSmsSkuLadderByIds(Long[] ids);

    /**
     * 删除商品阶梯价格信息
     * 
     * @param id 商品阶梯价格主键
     * @return 结果
     */
    public int deleteSmsSkuLadderById(Long id);
}
