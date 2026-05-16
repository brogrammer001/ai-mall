package com.mall.ums.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mall.ums.mapper.UmsMemberReceiveAddressMapper;
import com.mall.ums.domain.UmsMemberReceiveAddress;
import com.mall.ums.service.IUmsMemberReceiveAddressService;

/**
 * 会员收货地址Service业务层处理
 * 
 * @author mall
 * @date 2026-04-11
 */
@Service
public class UmsMemberReceiveAddressServiceImpl implements IUmsMemberReceiveAddressService 
{
    @Autowired
    private UmsMemberReceiveAddressMapper umsMemberReceiveAddressMapper;

    /**
     * 查询会员收货地址
     * 
     * @param id 会员收货地址主键
     * @return 会员收货地址
     */
    @Override
    public UmsMemberReceiveAddress selectUmsMemberReceiveAddressById(Long id)
    {
        return umsMemberReceiveAddressMapper.selectUmsMemberReceiveAddressById(id);
    }

    /**
     * 查询会员收货地址列表
     * 
     * @param umsMemberReceiveAddress 会员收货地址
     * @return 会员收货地址
     */
    @Override
    public List<UmsMemberReceiveAddress> selectUmsMemberReceiveAddressList(UmsMemberReceiveAddress umsMemberReceiveAddress)
    {
        return umsMemberReceiveAddressMapper.selectUmsMemberReceiveAddressList(umsMemberReceiveAddress);
    }

    /**
     * 新增会员收货地址
     * 
     * @param umsMemberReceiveAddress 会员收货地址
     * @return 结果
     */
    @Override
    public int insertUmsMemberReceiveAddress(UmsMemberReceiveAddress umsMemberReceiveAddress)
    {
        return umsMemberReceiveAddressMapper.insertUmsMemberReceiveAddress(umsMemberReceiveAddress);
    }

    /**
     * 修改会员收货地址
     * 
     * @param umsMemberReceiveAddress 会员收货地址
     * @return 结果
     */
    @Override
    public int updateUmsMemberReceiveAddress(UmsMemberReceiveAddress umsMemberReceiveAddress)
    {
        return umsMemberReceiveAddressMapper.updateUmsMemberReceiveAddress(umsMemberReceiveAddress);
    }

    /**
     * 批量删除会员收货地址
     * 
     * @param ids 需要删除的会员收货地址主键
     * @return 结果
     */
    @Override
    public int deleteUmsMemberReceiveAddressByIds(Long[] ids)
    {
        return umsMemberReceiveAddressMapper.deleteUmsMemberReceiveAddressByIds(ids);
    }

    /**
     * 删除会员收货地址信息
     * 
     * @param id 会员收货地址主键
     * @return 结果
     */
    @Override
    public int deleteUmsMemberReceiveAddressById(Long id)
    {
        return umsMemberReceiveAddressMapper.deleteUmsMemberReceiveAddressById(id);
    }
}
