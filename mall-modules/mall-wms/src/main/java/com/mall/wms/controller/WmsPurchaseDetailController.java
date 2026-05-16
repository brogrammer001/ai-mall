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
import com.mall.wms.domain.WmsPurchaseDetail;
import com.mall.wms.service.IWmsPurchaseDetailService;
import com.mall.common.core.web.controller.BaseController;
import com.mall.common.core.web.domain.AjaxResult;
import com.mall.common.core.utils.poi.ExcelUtil;
import com.mall.common.core.web.page.TableDataInfo;

/**
 * 【请填写功能名称】Controller
 * 
 * @author mall
 * @date 2026-04-11
 */
@RestController
@RequestMapping("/detail")
public class WmsPurchaseDetailController extends BaseController
{
    @Autowired
    private IWmsPurchaseDetailService wmsPurchaseDetailService;

    /**
     * 查询【请填写功能名称】列表
     */
    @RequiresPermissions("wms:detail:list")
    @GetMapping("/list")
    public TableDataInfo list(WmsPurchaseDetail wmsPurchaseDetail)
    {
        startPage();
        List<WmsPurchaseDetail> list = wmsPurchaseDetailService.selectWmsPurchaseDetailList(wmsPurchaseDetail);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @RequiresPermissions("wms:detail:export")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WmsPurchaseDetail wmsPurchaseDetail)
    {
        List<WmsPurchaseDetail> list = wmsPurchaseDetailService.selectWmsPurchaseDetailList(wmsPurchaseDetail);
        ExcelUtil<WmsPurchaseDetail> util = new ExcelUtil<WmsPurchaseDetail>(WmsPurchaseDetail.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @RequiresPermissions("wms:detail:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(wmsPurchaseDetailService.selectWmsPurchaseDetailById(id));
    }

    /**
     * 新增【请填写功能名称】
     */
    @RequiresPermissions("wms:detail:add")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WmsPurchaseDetail wmsPurchaseDetail)
    {
        return toAjax(wmsPurchaseDetailService.insertWmsPurchaseDetail(wmsPurchaseDetail));
    }

    /**
     * 修改【请填写功能名称】
     */
    @RequiresPermissions("wms:detail:edit")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WmsPurchaseDetail wmsPurchaseDetail)
    {
        return toAjax(wmsPurchaseDetailService.updateWmsPurchaseDetail(wmsPurchaseDetail));
    }

    /**
     * 删除【请填写功能名称】
     */
    @RequiresPermissions("wms:detail:remove")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(wmsPurchaseDetailService.deleteWmsPurchaseDetailByIds(ids));
    }
}
