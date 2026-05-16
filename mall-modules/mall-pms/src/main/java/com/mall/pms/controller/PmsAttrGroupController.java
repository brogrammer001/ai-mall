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
import com.mall.pms.domain.PmsAttrGroup;
import com.mall.pms.service.IPmsAttrGroupService;
import com.mall.common.core.web.controller.BaseController;
import com.mall.common.core.web.domain.AjaxResult;
import com.mall.common.core.utils.poi.ExcelUtil;
import com.mall.common.core.web.page.TableDataInfo;

/**
 * 属性分组Controller
 * 
 * @author mall
 * @date 2026-04-11
 */
@RestController
@RequestMapping("/group")
public class PmsAttrGroupController extends BaseController
{
    @Autowired
    private IPmsAttrGroupService pmsAttrGroupService;

    /**
     * 查询属性分组列表
     */
    @RequiresPermissions("pms:group:list")
    @GetMapping("/list")
    public TableDataInfo list(PmsAttrGroup pmsAttrGroup)
    {
        startPage();
        List<PmsAttrGroup> list = pmsAttrGroupService.selectPmsAttrGroupList(pmsAttrGroup);
        return getDataTable(list);
    }

    /**
     * 导出属性分组列表
     */
    @RequiresPermissions("pms:group:export")
    @Log(title = "属性分组", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PmsAttrGroup pmsAttrGroup)
    {
        List<PmsAttrGroup> list = pmsAttrGroupService.selectPmsAttrGroupList(pmsAttrGroup);
        ExcelUtil<PmsAttrGroup> util = new ExcelUtil<PmsAttrGroup>(PmsAttrGroup.class);
        util.exportExcel(response, list, "属性分组数据");
    }

    /**
     * 获取属性分组详细信息
     */
    @RequiresPermissions("pms:group:query")
    @GetMapping(value = "/{attrGroupId}")
    public AjaxResult getInfo(@PathVariable("attrGroupId") Long attrGroupId)
    {
        return success(pmsAttrGroupService.selectPmsAttrGroupByAttrGroupId(attrGroupId));
    }

    /**
     * 新增属性分组
     */
    @RequiresPermissions("pms:group:add")
    @Log(title = "属性分组", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PmsAttrGroup pmsAttrGroup)
    {
        return toAjax(pmsAttrGroupService.insertPmsAttrGroup(pmsAttrGroup));
    }

    /**
     * 修改属性分组
     */
    @RequiresPermissions("pms:group:edit")
    @Log(title = "属性分组", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PmsAttrGroup pmsAttrGroup)
    {
        return toAjax(pmsAttrGroupService.updatePmsAttrGroup(pmsAttrGroup));
    }

    /**
     * 删除属性分组
     */
    @RequiresPermissions("pms:group:remove")
    @Log(title = "属性分组", businessType = BusinessType.DELETE)
	@DeleteMapping("/{attrGroupIds}")
    public AjaxResult remove(@PathVariable Long[] attrGroupIds)
    {
        return toAjax(pmsAttrGroupService.deletePmsAttrGroupByAttrGroupIds(attrGroupIds));
    }
}
