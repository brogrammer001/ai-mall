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
import com.mall.wms.domain.WmsPurchase;
import com.mall.wms.service.IWmsPurchaseService;
import com.mall.common.core.web.controller.BaseController;
import com.mall.common.core.web.domain.AjaxResult;
import com.mall.common.core.utils.poi.ExcelUtil;
import com.mall.common.core.web.page.TableDataInfo;

/**
 * 采购信息Controller
 * 
 * @author mall
 * @date 2026-04-11
 */
@RestController
@RequestMapping("/purchase")
public class WmsPurchaseController extends BaseController
{
    @Autowired
    private IWmsPurchaseService wmsPurchaseService;

    /**
     * 查询采购信息列表
     */
    @RequiresPermissions("wms:purchase:list")
    @GetMapping("/list")
    public TableDataInfo list(WmsPurchase wmsPurchase)
    {
        startPage();
        List<WmsPurchase> list = wmsPurchaseService.selectWmsPurchaseList(wmsPurchase);
        return getDataTable(list);
    }

    /**
     * 导出采购信息列表
     */
    @RequiresPermissions("wms:purchase:export")
    @Log(title = "采购信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WmsPurchase wmsPurchase)
    {
        List<WmsPurchase> list = wmsPurchaseService.selectWmsPurchaseList(wmsPurchase);
        ExcelUtil<WmsPurchase> util = new ExcelUtil<WmsPurchase>(WmsPurchase.class);
        util.exportExcel(response, list, "采购信息数据");
    }

    /**
     * 获取采购信息详细信息
     */
    @RequiresPermissions("wms:purchase:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(wmsPurchaseService.selectWmsPurchaseById(id));
    }

    /**
     * 新增采购信息
     */
    @RequiresPermissions("wms:purchase:add")
    @Log(title = "采购信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WmsPurchase wmsPurchase)
    {
        return toAjax(wmsPurchaseService.insertWmsPurchase(wmsPurchase));
    }

    /**
     * 修改采购信息
     */
    @RequiresPermissions("wms:purchase:edit")
    @Log(title = "采购信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WmsPurchase wmsPurchase)
    {
        return toAjax(wmsPurchaseService.updateWmsPurchase(wmsPurchase));
    }

    /**
     * 删除采购信息
     */
    @RequiresPermissions("wms:purchase:remove")
    @Log(title = "采购信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(wmsPurchaseService.deleteWmsPurchaseByIds(ids));
    }
}
