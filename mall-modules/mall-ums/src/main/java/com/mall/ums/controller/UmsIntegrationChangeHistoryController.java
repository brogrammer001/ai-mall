package com.mall.ums.controller;

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
import com.mall.ums.domain.UmsIntegrationChangeHistory;
import com.mall.ums.service.IUmsIntegrationChangeHistoryService;
import com.mall.common.core.web.controller.BaseController;
import com.mall.common.core.web.domain.AjaxResult;
import com.mall.common.core.utils.poi.ExcelUtil;
import com.mall.common.core.web.page.TableDataInfo;

/**
 * 积分变化历史记录Controller
 * 
 * @author mall
 * @date 2026-04-11
 */
@RestController
@RequestMapping("/history")
public class UmsIntegrationChangeHistoryController extends BaseController
{
    @Autowired
    private IUmsIntegrationChangeHistoryService umsIntegrationChangeHistoryService;

    /**
     * 查询积分变化历史记录列表
     */
    @RequiresPermissions("ums:history:list")
    @GetMapping("/list")
    public TableDataInfo list(UmsIntegrationChangeHistory umsIntegrationChangeHistory)
    {
        startPage();
        List<UmsIntegrationChangeHistory> list = umsIntegrationChangeHistoryService.selectUmsIntegrationChangeHistoryList(umsIntegrationChangeHistory);
        return getDataTable(list);
    }

    /**
     * 导出积分变化历史记录列表
     */
    @RequiresPermissions("ums:history:export")
    @Log(title = "积分变化历史记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, UmsIntegrationChangeHistory umsIntegrationChangeHistory)
    {
        List<UmsIntegrationChangeHistory> list = umsIntegrationChangeHistoryService.selectUmsIntegrationChangeHistoryList(umsIntegrationChangeHistory);
        ExcelUtil<UmsIntegrationChangeHistory> util = new ExcelUtil<UmsIntegrationChangeHistory>(UmsIntegrationChangeHistory.class);
        util.exportExcel(response, list, "积分变化历史记录数据");
    }

    /**
     * 获取积分变化历史记录详细信息
     */
    @RequiresPermissions("ums:history:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(umsIntegrationChangeHistoryService.selectUmsIntegrationChangeHistoryById(id));
    }

    /**
     * 新增积分变化历史记录
     */
    @RequiresPermissions("ums:history:add")
    @Log(title = "积分变化历史记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UmsIntegrationChangeHistory umsIntegrationChangeHistory)
    {
        return toAjax(umsIntegrationChangeHistoryService.insertUmsIntegrationChangeHistory(umsIntegrationChangeHistory));
    }

    /**
     * 修改积分变化历史记录
     */
    @RequiresPermissions("ums:history:edit")
    @Log(title = "积分变化历史记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UmsIntegrationChangeHistory umsIntegrationChangeHistory)
    {
        return toAjax(umsIntegrationChangeHistoryService.updateUmsIntegrationChangeHistory(umsIntegrationChangeHistory));
    }

    /**
     * 删除积分变化历史记录
     */
    @RequiresPermissions("ums:history:remove")
    @Log(title = "积分变化历史记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(umsIntegrationChangeHistoryService.deleteUmsIntegrationChangeHistoryByIds(ids));
    }
}
