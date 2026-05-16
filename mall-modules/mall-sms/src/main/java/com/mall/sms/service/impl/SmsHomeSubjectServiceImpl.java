package com.mall.sms.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mall.sms.mapper.SmsHomeSubjectMapper;
import com.mall.sms.domain.SmsHomeSubject;
import com.mall.sms.service.ISmsHomeSubjectService;

/**
 * 首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】Service业务层处理
 * 
 * @author mall
 * @date 2026-04-11
 */
@Service
public class SmsHomeSubjectServiceImpl implements ISmsHomeSubjectService 
{
    @Autowired
    private SmsHomeSubjectMapper smsHomeSubjectMapper;

    /**
     * 查询首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
     * 
     * @param id 首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】主键
     * @return 首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
     */
    @Override
    public SmsHomeSubject selectSmsHomeSubjectById(Long id)
    {
        return smsHomeSubjectMapper.selectSmsHomeSubjectById(id);
    }

    /**
     * 查询首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】列表
     * 
     * @param smsHomeSubject 首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
     * @return 首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
     */
    @Override
    public List<SmsHomeSubject> selectSmsHomeSubjectList(SmsHomeSubject smsHomeSubject)
    {
        return smsHomeSubjectMapper.selectSmsHomeSubjectList(smsHomeSubject);
    }

    /**
     * 新增首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
     * 
     * @param smsHomeSubject 首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
     * @return 结果
     */
    @Override
    public int insertSmsHomeSubject(SmsHomeSubject smsHomeSubject)
    {
        return smsHomeSubjectMapper.insertSmsHomeSubject(smsHomeSubject);
    }

    /**
     * 修改首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
     * 
     * @param smsHomeSubject 首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
     * @return 结果
     */
    @Override
    public int updateSmsHomeSubject(SmsHomeSubject smsHomeSubject)
    {
        return smsHomeSubjectMapper.updateSmsHomeSubject(smsHomeSubject);
    }

    /**
     * 批量删除首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
     * 
     * @param ids 需要删除的首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】主键
     * @return 结果
     */
    @Override
    public int deleteSmsHomeSubjectByIds(Long[] ids)
    {
        return smsHomeSubjectMapper.deleteSmsHomeSubjectByIds(ids);
    }

    /**
     * 删除首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】信息
     * 
     * @param id 首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】主键
     * @return 结果
     */
    @Override
    public int deleteSmsHomeSubjectById(Long id)
    {
        return smsHomeSubjectMapper.deleteSmsHomeSubjectById(id);
    }
}
