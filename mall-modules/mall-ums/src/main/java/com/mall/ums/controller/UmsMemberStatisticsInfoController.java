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
import com.mall.ums.domain.UmsMemberStatisticsInfo;
import com.mall.ums.service.IUmsMemberStatisticsInfoService;
import com.mall.common.core.web.controller.BaseController;
import com.mall.common.core.web.domain.AjaxResult;
import com.mall.common.core.utils.poi.ExcelUtil;
import com.mall.common.core.web.page.TableDataInfo;

/**
 * 会员统计信息Controller
 * 
 * @author mall
 * @date 2026-04-11
 */
@RestController
@RequestMapping("/info")
public class UmsMemberStatisticsInfoController extends BaseController
{
    @Autowired
    private IUmsMemberStatisticsInfoService umsMemberStatisticsInfoService;

    /**
     * 查询会员统计信息列表
     */
    @RequiresPermissions("ums:info:list")
    @GetMapping("/list")
    public TableDataInfo list(UmsMemberStatisticsInfo umsMemberStatisticsInfo)
    {
        startPage();
        List<UmsMemberStatisticsInfo> list = umsMemberStatisticsInfoService.selectUmsMemberStatisticsInfoList(umsMemberStatisticsInfo);
        return getDataTable(list);
    }

    /**
     * 导出会员统计信息列表
     */
    @RequiresPermissions("ums:info:export")
    @Log(title = "会员统计信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, UmsMemberStatisticsInfo umsMemberStatisticsInfo)
    {
        List<UmsMemberStatisticsInfo> list = umsMemberStatisticsInfoService.selectUmsMemberStatisticsInfoList(umsMemberStatisticsInfo);
        ExcelUtil<UmsMemberStatisticsInfo> util = new ExcelUtil<UmsMemberStatisticsInfo>(UmsMemberStatisticsInfo.class);
        util.exportExcel(response, list, "会员统计信息数据");
    }

    /**
     * 获取会员统计信息详细信息
     */
    @RequiresPermissions("ums:info:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(umsMemberStatisticsInfoService.selectUmsMemberStatisticsInfoById(id));
    }

    /**
     * 新增会员统计信息
     */
    @RequiresPermissions("ums:info:add")
    @Log(title = "会员统计信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UmsMemberStatisticsInfo umsMemberStatisticsInfo)
    {
        return toAjax(umsMemberStatisticsInfoService.insertUmsMemberStatisticsInfo(umsMemberStatisticsInfo));
    }

    /**
     * 修改会员统计信息
     */
    @RequiresPermissions("ums:info:edit")
    @Log(title = "会员统计信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UmsMemberStatisticsInfo umsMemberStatisticsInfo)
    {
        return toAjax(umsMemberStatisticsInfoService.updateUmsMemberStatisticsInfo(umsMemberStatisticsInfo));
    }

    /**
     * 删除会员统计信息
     */
    @RequiresPermissions("ums:info:remove")
    @Log(title = "会员统计信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(umsMemberStatisticsInfoService.deleteUmsMemberStatisticsInfoByIds(ids));
    }
}
