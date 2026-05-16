package com.mall.sms.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mall.sms.mapper.SmsHomeAdvMapper;
import com.mall.sms.domain.SmsHomeAdv;
import com.mall.sms.service.ISmsHomeAdvService;

/**
 * 首页轮播广告Service业务层处理
 * 
 * @author mall
 * @date 2026-04-11
 */
@Service
public class SmsHomeAdvServiceImpl implements ISmsHomeAdvService 
{
    @Autowired
    private SmsHomeAdvMapper smsHomeAdvMapper;

    /**
     * 查询首页轮播广告
     * 
     * @param id 首页轮播广告主键
     * @return 首页轮播广告
     */
    @Override
    public SmsHomeAdv selectSmsHomeAdvById(Long id)
    {
        return smsHomeAdvMapper.selectSmsHomeAdvById(id);
    }

    /**
     * 查询首页轮播广告列表
     * 
     * @param smsHomeAdv 首页轮播广告
     * @return 首页轮播广告
     */
    @Override
    public List<SmsHomeAdv> selectSmsHomeAdvList(SmsHomeAdv smsHomeAdv)
    {
        return smsHomeAdvMapper.selectSmsHomeAdvList(smsHomeAdv);
    }

    /**
     * 新增首页轮播广告
     * 
     * @param smsHomeAdv 首页轮播广告
     * @return 结果
     */
    @Override
    public int insertSmsHomeAdv(SmsHomeAdv smsHomeAdv)
    {
        return smsHomeAdvMapper.insertSmsHomeAdv(smsHomeAdv);
    }

    /**
     * 修改首页轮播广告
     * 
     * @param smsHomeAdv 首页轮播广告
     * @return 结果
     */
    @Override
    public int updateSmsHomeAdv(SmsHomeAdv smsHomeAdv)
    {
        return smsHomeAdvMapper.updateSmsHomeAdv(smsHomeAdv);
    }

    /**
     * 批量删除首页轮播广告
     * 
     * @param ids 需要删除的首页轮播广告主键
     * @return 结果
     */
    @Override
    public int deleteSmsHomeAdvByIds(Long[] ids)
    {
        return smsHomeAdvMapper.deleteSmsHomeAdvByIds(ids);
    }

    /**
     * 删除首页轮播广告信息
     * 
     * @param id 首页轮播广告主键
     * @return 结果
     */
    @Override
    public int deleteSmsHomeAdvById(Long id)
    {
        return smsHomeAdvMapper.deleteSmsHomeAdvById(id);
    }
}
