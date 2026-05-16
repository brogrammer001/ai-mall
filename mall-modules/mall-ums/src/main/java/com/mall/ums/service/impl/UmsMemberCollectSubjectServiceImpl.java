package com.mall.ums.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mall.ums.mapper.UmsMemberCollectSubjectMapper;
import com.mall.ums.domain.UmsMemberCollectSubject;
import com.mall.ums.service.IUmsMemberCollectSubjectService;

/**
 * 会员收藏的专题活动Service业务层处理
 * 
 * @author mall
 * @date 2026-04-11
 */
@Service
public class UmsMemberCollectSubjectServiceImpl implements IUmsMemberCollectSubjectService 
{
    @Autowired
    private UmsMemberCollectSubjectMapper umsMemberCollectSubjectMapper;

    /**
     * 查询会员收藏的专题活动
     * 
     * @param id 会员收藏的专题活动主键
     * @return 会员收藏的专题活动
     */
    @Override
    public UmsMemberCollectSubject selectUmsMemberCollectSubjectById(Long id)
    {
        return umsMemberCollectSubjectMapper.selectUmsMemberCollectSubjectById(id);
    }

    /**
     * 查询会员收藏的专题活动列表
     * 
     * @param umsMemberCollectSubject 会员收藏的专题活动
     * @return 会员收藏的专题活动
     */
    @Override
    public List<UmsMemberCollectSubject> selectUmsMemberCollectSubjectList(UmsMemberCollectSubject umsMemberCollectSubject)
    {
        return umsMemberCollectSubjectMapper.selectUmsMemberCollectSubjectList(umsMemberCollectSubject);
    }

    /**
     * 新增会员收藏的专题活动
     * 
     * @param umsMemberCollectSubject 会员收藏的专题活动
     * @return 结果
     */
    @Override
    public int insertUmsMemberCollectSubject(UmsMemberCollectSubject umsMemberCollectSubject)
    {
        return umsMemberCollectSubjectMapper.insertUmsMemberCollectSubject(umsMemberCollectSubject);
    }

    /**
     * 修改会员收藏的专题活动
     * 
     * @param umsMemberCollectSubject 会员收藏的专题活动
     * @return 结果
     */
    @Override
    public int updateUmsMemberCollectSubject(UmsMemberCollectSubject umsMemberCollectSubject)
    {
        return umsMemberCollectSubjectMapper.updateUmsMemberCollectSubject(umsMemberCollectSubject);
    }

    /**
     * 批量删除会员收藏的专题活动
     * 
     * @param ids 需要删除的会员收藏的专题活动主键
     * @return 结果
     */
    @Override
    public int deleteUmsMemberCollectSubjectByIds(Long[] ids)
    {
        return umsMemberCollectSubjectMapper.deleteUmsMemberCollectSubjectByIds(ids);
    }

    /**
     * 删除会员收藏的专题活动信息
     * 
     * @param id 会员收藏的专题活动主键
     * @return 结果
     */
    @Override
    public int deleteUmsMemberCollectSubjectById(Long id)
    {
        return umsMemberCollectSubjectMapper.deleteUmsMemberCollectSubjectById(id);
    }
}
