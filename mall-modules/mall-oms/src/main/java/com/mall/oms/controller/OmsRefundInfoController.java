package com.mall.oms.controller;

import com.mall.common.core.utils.poi.ExcelUtil;
import com.mall.common.core.web.controller.BaseController;
import com.mall.common.core.web.domain.AjaxResult;
import com.mall.common.core.web.page.TableDataInfo;
import com.mall.common.log.annotation.Log;
import com.mall.common.log.enums.BusinessType;
import com.mall.common.security.annotation.RequiresPermissions;
import com.mall.oms.domain.OmsRefundInfo;
import com.mall.oms.service.IOmsRefundInfoService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 退款信息Controller
 * 
 * @author mall
 * @date 2026-04-11
 */
@RestController
@RequestMapping("/refund")
public class OmsRefundInfoController extends BaseController
{
    @Autowired
    private IOmsRefundInfoService omsRefundInfoService;

    /**
     * 查询退款信息列表
     */
    @RequiresPermissions("oms:info:list")
    @GetMapping("/list")
    public TableDataInfo list(OmsRefundInfo omsRefundInfo)
    {
        startPage();
        List<OmsRefundInfo> list = omsRefundInfoService.selectOmsRefundInfoList(omsRefundInfo);
        return getDataTable(list);
    }

    /**
     * 导出退款信息列表
     */
    @RequiresPermissions("oms:info:export")
    @Log(title = "退款信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, OmsRefundInfo omsRefundInfo)
    {
        List<OmsRefundInfo> list = omsRefundInfoService.selectOmsRefundInfoList(omsRefundInfo);
        ExcelUtil<OmsRefundInfo> util = new ExcelUtil<OmsRefundInfo>(OmsRefundInfo.class);
        util.exportExcel(response, list, "退款信息数据");
    }

    /**
     * 获取退款信息详细信息
     */
    @RequiresPermissions("oms:info:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(omsRefundInfoService.selectOmsRefundInfoById(id));
    }

    /**
     * 新增退款信息
     */
    @RequiresPermissions("oms:info:add")
    @Log(title = "退款信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OmsRefundInfo omsRefundInfo)
    {
        return toAjax(omsRefundInfoService.insertOmsRefundInfo(omsRefundInfo));
    }

    /**
     * 修改退款信息
     */
    @RequiresPermissions("oms:info:edit")
    @Log(title = "退款信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OmsRefundInfo omsRefundInfo)
    {
        return toAjax(omsRefundInfoService.updateOmsRefundInfo(omsRefundInfo));
    }

    /**
     * 删除退款信息
     */
    @RequiresPermissions("oms:info:remove")
    @Log(title = "退款信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(omsRefundInfoService.deleteOmsRefundInfoByIds(ids));
    }
}
