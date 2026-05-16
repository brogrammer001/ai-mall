package com.mall.wms.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mall.wms.mapper.WmsWareInfoMapper;
import com.mall.wms.domain.WmsWareInfo;
import com.mall.wms.service.IWmsWareInfoService;

/**
 * 仓库信息Service业务层处理
 * 
 * @author mall
 * @date 2026-04-11
 */
@Service
public class WmsWareInfoServiceImpl implements IWmsWareInfoService 
{
    @Autowired
    private WmsWareInfoMapper wmsWareInfoMapper;

    /**
     * 查询仓库信息
     * 
     * @param id 仓库信息主键
     * @return 仓库信息
     */
    @Override
    public WmsWareInfo selectWmsWareInfoById(Long id)
    {
        return wmsWareInfoMapper.selectWmsWareInfoById(id);
    }

    /**
     * 查询仓库信息列表
     * 
     * @param wmsWareInfo 仓库信息
     * @return 仓库信息
     */
    @Override
    public List<WmsWareInfo> selectWmsWareInfoList(WmsWareInfo wmsWareInfo)
    {
        return wmsWareInfoMapper.selectWmsWareInfoList(wmsWareInfo);
    }

    /**
     * 新增仓库信息
     * 
     * @param wmsWareInfo 仓库信息
     * @return 结果
     */
    @Override
    public int insertWmsWareInfo(WmsWareInfo wmsWareInfo)
    {
        return wmsWareInfoMapper.insertWmsWareInfo(wmsWareInfo);
    }

    /**
     * 修改仓库信息
     * 
     * @param wmsWareInfo 仓库信息
     * @return 结果
     */
    @Override
    public int updateWmsWareInfo(WmsWareInfo wmsWareInfo)
    {
        return wmsWareInfoMapper.updateWmsWareInfo(wmsWareInfo);
    }

    /**
     * 批量删除仓库信息
     * 
     * @param ids 需要删除的仓库信息主键
     * @return 结果
     */
    @Override
    public int deleteWmsWareInfoByIds(Long[] ids)
    {
        return wmsWareInfoMapper.deleteWmsWareInfoByIds(ids);
    }

    /**
     * 删除仓库信息信息
     * 
     * @param id 仓库信息主键
     * @return 结果
     */
    @Override
    public int deleteWmsWareInfoById(Long id)
    {
        return wmsWareInfoMapper.deleteWmsWareInfoById(id);
    }
}
