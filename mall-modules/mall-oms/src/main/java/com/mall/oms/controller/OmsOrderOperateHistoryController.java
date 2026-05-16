package com.mall.oms.controller;

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
import com.mall.oms.domain.OmsOrderOperateHistory;
import com.mall.oms.service.IOmsOrderOperateHistoryService;
import com.mall.common.core.web.controller.BaseController;
import com.mall.common.core.web.domain.AjaxResult;
import com.mall.common.core.utils.poi.ExcelUtil;
import com.mall.common.core.web.page.TableDataInfo;

/**
 * 订单操作历史记录Controller
 * 
 * @author mall
 * @date 2026-04-11
 */
@RestController
@RequestMapping("/history")
public class OmsOrderOperateHistoryController extends BaseController
{
    @Autowired
    private IOmsOrderOperateHistoryService omsOrderOperateHistoryService;

    /**
     * 查询订单操作历史记录列表
     */
    @RequiresPermissions("oms:history:list")
    @GetMapping("/list")
    public TableDataInfo list(OmsOrderOperateHistory omsOrderOperateHistory)
    {
        startPage();
        List<OmsOrderOperateHistory> list = omsOrderOperateHistoryService.selectOmsOrderOperateHistoryList(omsOrderOperateHistory);
        return getDataTable(list);
    }

    /**
     * 导出订单操作历史记录列表
     */
    @RequiresPermissions("oms:history:export")
    @Log(title = "订单操作历史记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, OmsOrderOperateHistory omsOrderOperateHistory)
    {
        List<OmsOrderOperateHistory> list = omsOrderOperateHistoryService.selectOmsOrderOperateHistoryList(omsOrderOperateHistory);
        ExcelUtil<OmsOrderOperateHistory> util = new ExcelUtil<OmsOrderOperateHistory>(OmsOrderOperateHistory.class);
        util.exportExcel(response, list, "订单操作历史记录数据");
    }

    /**
     * 获取订单操作历史记录详细信息
     */
    @RequiresPermissions("oms:history:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(omsOrderOperateHistoryService.selectOmsOrderOperateHistoryById(id));
    }

    /**
     * 新增订单操作历史记录
     */
    @RequiresPermissions("oms:history:add")
    @Log(title = "订单操作历史记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OmsOrderOperateHistory omsOrderOperateHistory)
    {
        return toAjax(omsOrderOperateHistoryService.insertOmsOrderOperateHistory(omsOrderOperateHistory));
    }

    /**
     * 修改订单操作历史记录
     */
    @RequiresPermissions("oms:history:edit")
    @Log(title = "订单操作历史记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OmsOrderOperateHistory omsOrderOperateHistory)
    {
        return toAjax(omsOrderOperateHistoryService.updateOmsOrderOperateHistory(omsOrderOperateHistory));
    }

    /**
     * 删除订单操作历史记录
     */
    @RequiresPermissions("oms:history:remove")
    @Log(title = "订单操作历史记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(omsOrderOperateHistoryService.deleteOmsOrderOperateHistoryByIds(ids));
    }
}
