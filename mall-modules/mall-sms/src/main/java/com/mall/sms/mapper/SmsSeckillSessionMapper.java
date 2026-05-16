package com.mall.sms.mapper;

import java.util.List;
import com.mall.sms.domain.SmsSeckillSession;

/**
 * 秒杀活动场次Mapper接口
 * 
 * @author mall
 * @date 2026-04-11
 */
public interface SmsSeckillSessionMapper 
{
    /**
     * 查询秒杀活动场次
     * 
     * @param id 秒杀活动场次主键
     * @return 秒杀活动场次
     */
    public SmsSeckillSession selectSmsSeckillSessionById(Long id);

    /**
     * 查询秒杀活动场次列表
     * 
     * @param smsSeckillSession 秒杀活动场次
     * @return 秒杀活动场次集合
     */
    public List<SmsSeckillSession> selectSmsSeckillSessionList(SmsSeckillSession smsSeckillSession);

    /**
     * 新增秒杀活动场次
     * 
     * @param smsSeckillSession 秒杀活动场次
     * @return 结果
     */
    public int insertSmsSeckillSession(SmsSeckillSession smsSeckillSession);

    /**
     * 修改秒杀活动场次
     * 
     * @param smsSeckillSession 秒杀活动场次
     * @return 结果
     */
    public int updateSmsSeckillSession(SmsSeckillSession smsSeckillSession);

    /**
     * 删除秒杀活动场次
     * 
     * @param id 秒杀活动场次主键
     * @return 结果
     */
    public int deleteSmsSeckillSessionById(Long id);

    /**
     * 批量删除秒杀活动场次
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSmsSeckillSessionByIds(Long[] ids);
}
