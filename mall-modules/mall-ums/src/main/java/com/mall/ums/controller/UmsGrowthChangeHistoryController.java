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
import com.mall.ums.domain.UmsGrowthChangeHistory;
import com.mall.ums.service.IUmsGrowthChangeHistoryService;
import com.mall.common.core.web.controller.BaseController;
import com.mall.common.core.web.domain.AjaxResult;
import com.mall.common.core.utils.poi.ExcelUtil;
import com.mall.common.core.web.page.TableDataInfo;

/**
 * 成长值变化历史记录Controller
 * 
 * @author mall
 * @date 2026-04-11
 */
@RestController
@RequestMapping("/history")
public class UmsGrowthChangeHistoryController extends BaseController
{
    @Autowired
    private IUmsGrowthChangeHistoryService umsGrowthChangeHistoryService;

    /**
     * 查询成长值变化历史记录列表
     */
    @RequiresPermissions("ums:history:list")
    @GetMapping("/list")
    public TableDataInfo list(UmsGrowthChangeHistory umsGrowthChangeHistory)
    {
        startPage();
        List<UmsGrowthChangeHistory> list = umsGrowthChangeHistoryService.selectUmsGrowthChangeHistoryList(umsGrowthChangeHistory);
        return getDataTable(list);
    }

    /**
     * 导出成长值变化历史记录列表
     */
    @RequiresPermissions("ums:history:export")
    @Log(title = "成长值变化历史记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, UmsGrowthChangeHistory umsGrowthChangeHistory)
    {
        List<UmsGrowthChangeHistory> list = umsGrowthChangeHistoryService.selectUmsGrowthChangeHistoryList(umsGrowthChangeHistory);
        ExcelUtil<UmsGrowthChangeHistory> util = new ExcelUtil<UmsGrowthChangeHistory>(UmsGrowthChangeHistory.class);
        util.exportExcel(response, list, "成长值变化历史记录数据");
    }

    /**
     * 获取成长值变化历史记录详细信息
     */
    @RequiresPermissions("ums:history:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(umsGrowthChangeHistoryService.selectUmsGrowthChangeHistoryById(id));
    }

    /**
     * 新增成长值变化历史记录
     */
    @RequiresPermissions("ums:history:add")
    @Log(title = "成长值变化历史记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UmsGrowthChangeHistory umsGrowthChangeHistory)
    {
        return toAjax(umsGrowthChangeHistoryService.insertUmsGrowthChangeHistory(umsGrowthChangeHistory));
    }

    /**
     * 修改成长值变化历史记录
     */
    @RequiresPermissions("ums:history:edit")
    @Log(title = "成长值变化历史记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UmsGrowthChangeHistory umsGrowthChangeHistory)
    {
        return toAjax(umsGrowthChangeHistoryService.updateUmsGrowthChangeHistory(umsGrowthChangeHistory));
    }

    /**
     * 删除成长值变化历史记录
     */
    @RequiresPermissions("ums:history:remove")
    @Log(title = "成长值变化历史记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(umsGrowthChangeHistoryService.deleteUmsGrowthChangeHistoryByIds(ids));
    }
}
