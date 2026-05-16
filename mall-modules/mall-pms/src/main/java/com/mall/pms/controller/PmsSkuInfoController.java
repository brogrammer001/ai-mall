package com.mall.pms.controller;

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
import com.mall.pms.domain.PmsSkuInfo;
import com.mall.pms.service.IPmsSkuInfoService;
import com.mall.common.core.web.controller.BaseController;
import com.mall.common.core.web.domain.AjaxResult;
import com.mall.common.core.utils.poi.ExcelUtil;
import com.mall.common.core.web.page.TableDataInfo;

/**
 * sku信息Controller
 * 
 * @author mall
 * @date 2026-04-11
 */
@RestController
@RequestMapping("/info")
public class PmsSkuInfoController extends BaseController
{
    @Autowired
    private IPmsSkuInfoService pmsSkuInfoService;

    /**
     * 查询sku信息列表
     */
    @RequiresPermissions("pms:info:list")
    @GetMapping("/list")
    public TableDataInfo list(PmsSkuInfo pmsSkuInfo)
    {
        startPage();
        List<PmsSkuInfo> list = pmsSkuInfoService.selectPmsSkuInfoList(pmsSkuInfo);
        return getDataTable(list);
    }

    /**
     * 导出sku信息列表
     */
    @RequiresPermissions("pms:info:export")
    @Log(title = "sku信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PmsSkuInfo pmsSkuInfo)
    {
        List<PmsSkuInfo> list = pmsSkuInfoService.selectPmsSkuInfoList(pmsSkuInfo);
        ExcelUtil<PmsSkuInfo> util = new ExcelUtil<PmsSkuInfo>(PmsSkuInfo.class);
        util.exportExcel(response, list, "sku信息数据");
    }

    /**
     * 获取sku信息详细信息
     */
    @RequiresPermissions("pms:info:query")
    @GetMapping(value = "/{skuId}")
    public AjaxResult getInfo(@PathVariable("skuId") Long skuId)
    {
        return success(pmsSkuInfoService.selectPmsSkuInfoBySkuId(skuId));
    }

    /**
     * 新增sku信息
     */
    @RequiresPermissions("pms:info:add")
    @Log(title = "sku信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PmsSkuInfo pmsSkuInfo)
    {
        return toAjax(pmsSkuInfoService.insertPmsSkuInfo(pmsSkuInfo));
    }

    /**
     * 修改sku信息
     */
    @RequiresPermissions("pms:info:edit")
    @Log(title = "sku信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PmsSkuInfo pmsSkuInfo)
    {
        return toAjax(pmsSkuInfoService.updatePmsSkuInfo(pmsSkuInfo));
    }

    /**
     * 删除sku信息
     */
    @RequiresPermissions("pms:info:remove")
    @Log(title = "sku信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{skuIds}")
    public AjaxResult remove(@PathVariable Long[] skuIds)
    {
        return toAjax(pmsSkuInfoService.deletePmsSkuInfoBySkuIds(skuIds));
    }
}
