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
import com.mall.ums.domain.UmsMemberLoginLog;
import com.mall.ums.service.IUmsMemberLoginLogService;
import com.mall.common.core.web.controller.BaseController;
import com.mall.common.core.web.domain.AjaxResult;
import com.mall.common.core.utils.poi.ExcelUtil;
import com.mall.common.core.web.page.TableDataInfo;

/**
 * 会员登录记录Controller
 * 
 * @author mall
 * @date 2026-04-11
 */
@RestController
@RequestMapping("/log")
public class UmsMemberLoginLogController extends BaseController
{
    @Autowired
    private IUmsMemberLoginLogService umsMemberLoginLogService;

    /**
     * 查询会员登录记录列表
     */
    @RequiresPermissions("ums:log:list")
    @GetMapping("/list")
    public TableDataInfo list(UmsMemberLoginLog umsMemberLoginLog)
    {
        startPage();
        List<UmsMemberLoginLog> list = umsMemberLoginLogService.selectUmsMemberLoginLogList(umsMemberLoginLog);
        return getDataTable(list);
    }

    /**
     * 导出会员登录记录列表
     */
    @RequiresPermissions("ums:log:export")
    @Log(title = "会员登录记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, UmsMemberLoginLog umsMemberLoginLog)
    {
        List<UmsMemberLoginLog> list = umsMemberLoginLogService.selectUmsMemberLoginLogList(umsMemberLoginLog);
        ExcelUtil<UmsMemberLoginLog> util = new ExcelUtil<UmsMemberLoginLog>(UmsMemberLoginLog.class);
        util.exportExcel(response, list, "会员登录记录数据");
    }

    /**
     * 获取会员登录记录详细信息
     */
    @RequiresPermissions("ums:log:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(umsMemberLoginLogService.selectUmsMemberLoginLogById(id));
    }

    /**
     * 新增会员登录记录
     */
    @RequiresPermissions("ums:log:add")
    @Log(title = "会员登录记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UmsMemberLoginLog umsMemberLoginLog)
    {
        return toAjax(umsMemberLoginLogService.insertUmsMemberLoginLog(umsMemberLoginLog));
    }

    /**
     * 修改会员登录记录
     */
    @RequiresPermissions("ums:log:edit")
    @Log(title = "会员登录记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UmsMemberLoginLog umsMemberLoginLog)
    {
        return toAjax(umsMemberLoginLogService.updateUmsMemberLoginLog(umsMemberLoginLog));
    }

    /**
     * 删除会员登录记录
     */
    @RequiresPermissions("ums:log:remove")
    @Log(title = "会员登录记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(umsMemberLoginLogService.deleteUmsMemberLoginLogByIds(ids));
    }
}
