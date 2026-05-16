package com.mall.ums.service;

import java.util.List;
import com.mall.ums.domain.UmsMember;

/**
 * 会员Service接口
 * 
 * @author mall
 * @date 2026-04-11
 */
public interface IUmsMemberService 
{
    /**
     * 查询会员
     * 
     * @param id 会员主键
     * @return 会员
     */
    public UmsMember selectUmsMemberById(Long id);

    /**
     * 查询会员列表
     * 
     * @param umsMember 会员
     * @return 会员集合
     */
    public List<UmsMember> selectUmsMemberList(UmsMember umsMember);

    /**
     * 新增会员
     * 
     * @param umsMember 会员
     * @return 结果
     */
    public int insertUmsMember(UmsMember umsMember);

    /**
     * 修改会员
     * 
     * @param umsMember 会员
     * @return 结果
     */
    public int updateUmsMember(UmsMember umsMember);

    /**
     * 批量删除会员
     * 
     * @param ids 需要删除的会员主键集合
     * @return 结果
     */
    public int deleteUmsMemberByIds(Long[] ids);

    /**
     * 删除会员信息
     * 
     * @param id 会员主键
     * @return 结果
     */
    public int deleteUmsMemberById(Long id);
}
