package com.mall.wms.controller;

import java.util.List;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mall.common.log.annotation.Log;
import com.mall.common.log.enums.BusinessType;
import com.mall.common.security.annotation.RequiresPermissions;
import com.mall.wms.domain.WmsWareInfo;
import com.mall.wms.service.IWmsWareInfoService;
import com.mall.common.core.web.controller.BaseController;
import com.mall.common.core.web.domain.AjaxResult;
import com.mall.common.core.utils.poi.ExcelUtil;
import com.mall.common.core.web.page.TableDataInfo;

/**
 * 仓库信息Controller
 * 
 * @author mall
 * @date 2026-04-11
 */
@RestController
@RequestMapping("/info")
public class WmsWareInfoController extends BaseController
{
    @Autowired
    private IWmsWareInfoService wmsWareInfoService;

    /**
     * 查询仓库信息列表
     */
    @RequiresPermissions("wms:info:list")
    @GetMapping("/list")
    public TableDataInfo list(WmsWareInfo wmsWareInfo)
    {
        startPage();
        List<WmsWareInfo> list = wmsWareInfoService.selectWmsWareInfoList(wmsWareInfo);
        return getDataTable(list);
    }

    /**
     * 导出仓库信息列表
     */
    @RequiresPermissions("wms:info:export")
    @Log(title = "仓库信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WmsWareInfo wmsWareInfo)
    {
        List<WmsWareInfo> list = wmsWareInfoService.selectWmsWareInfoList(wmsWareInfo);
        ExcelUtil<WmsWareInfo> util = new ExcelUtil<WmsWareInfo>(WmsWareInfo.class);
        util.exportExcel(response, list, "仓库信息数据");
    }

    /**
     * 获取仓库信息详细信息
     */
    @RequiresPermissions("wms:info:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(wmsWareInfoService.selectWmsWareInfoById(id));
    }

    /**
     * 新增仓库信息
     */
    @RequiresPermissions("wms:info:add")
    @Log(title = "仓库信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WmsWareInfo wmsWareInfo)
    {
        return toAjax(wmsWareInfoService.insertWmsWareInfo(wmsWareInfo));
    }

    /**
     * 修改仓库信息
     */
    @RequiresPermissions("wms:info:edit")
    @Log(title = "仓库信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WmsWareInfo wmsWareInfo)
    {
        return toAjax(wmsWareInfoService.updateWmsWareInfo(wmsWareInfo));
    }

    /**
     * 删除仓库信息
     */
    @RequiresPermissions("wms:info:remove")
    @Log(title = "仓库信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(wmsWareInfoService.deleteWmsWareInfoByIds(ids));
    }
}
