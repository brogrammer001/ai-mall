package com.mall.sms.service.impl;

import java.util.List;
import com.mall.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mall.sms.mapper.SmsSeckillSessionMapper;
import com.mall.sms.domain.SmsSeckillSession;
import com.mall.sms.service.ISmsSeckillSessionService;

/**
 * 秒杀活动场次Service业务层处理
 * 
 * @author mall
 * @date 2026-04-11
 */
@Service
public class SmsSeckillSessionServiceImpl implements ISmsSeckillSessionService 
{
    @Autowired
    private SmsSeckillSessionMapper smsSeckillSessionMapper;

    /**
     * 查询秒杀活动场次
     * 
     * @param id 秒杀活动场次主键
     * @return 秒杀活动场次
     */
    @Override
    public SmsSeckillSession selectSmsSeckillSessionById(Long id)
    {
        return smsSeckillSessionMapper.selectSmsSeckillSessionById(id);
    }

    /**
     * 查询秒杀活动场次列表
     * 
     * @param smsSeckillSession 秒杀活动场次
     * @return 秒杀活动场次
     */
    @Override
    public List<SmsSeckillSession> selectSmsSeckillSessionList(SmsSeckillSession smsSeckillSession)
    {
        return smsSeckillSessionMapper.selectSmsSeckillSessionList(smsSeckillSession);
    }

    /**
     * 新增秒杀活动场次
     * 
     * @param smsSeckillSession 秒杀活动场次
     * @return 结果
     */
    @Override
    public int insertSmsSeckillSession(SmsSeckillSession smsSeckillSession)
    {
        smsSeckillSession.setCreateTime(DateUtils.getNowDate());
        return smsSeckillSessionMapper.insertSmsSeckillSession(smsSeckillSession);
    }

    /**
     * 修改秒杀活动场次
     * 
     * @param smsSeckillSession 秒杀活动场次
     * @return 结果
     */
    @Override
    public int updateSmsSeckillSession(SmsSeckillSession smsSeckillSession)
    {
        return smsSeckillSessionMapper.updateSmsSeckillSession(smsSeckillSession);
    }

    /**
     * 批量删除秒杀活动场次
     * 
     * @param ids 需要删除的秒杀活动场次主键
     * @return 结果
     */
    @Override
    public int deleteSmsSeckillSessionByIds(Long[] ids)
    {
        return smsSeckillSessionMapper.deleteSmsSeckillSessionByIds(ids);
    }

    /**
     * 删除秒杀活动场次信息
     * 
     * @param id 秒杀活动场次主键
     * @return 结果
     */
    @Override
    public int deleteSmsSeckillSessionById(Long id)
    {
        return smsSeckillSessionMapper.deleteSmsSeckillSessionById(id);
    }
}
