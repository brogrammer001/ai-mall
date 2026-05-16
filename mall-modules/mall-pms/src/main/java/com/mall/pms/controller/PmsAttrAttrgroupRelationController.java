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
import com.mall.pms.domain.PmsAttrAttrgroupRelation;
import com.mall.pms.service.IPmsAttrAttrgroupRelationService;
import com.mall.common.core.web.controller.BaseController;
import com.mall.common.core.web.domain.AjaxResult;
import com.mall.common.core.utils.poi.ExcelUtil;
import com.mall.common.core.web.page.TableDataInfo;

/**
 * 属性&属性分组关联Controller
 * 
 * @author mall
 * @date 2026-04-11
 */
@RestController
@RequestMapping("/relation")
public class PmsAttrAttrgroupRelationController extends BaseController
{
    @Autowired
    private IPmsAttrAttrgroupRelationService pmsAttrAttrgroupRelationService;

    /**
     * 查询属性&属性分组关联列表
     */
    @RequiresPermissions("pms:relation:list")
    @GetMapping("/list")
    public TableDataInfo list(PmsAttrAttrgroupRelation pmsAttrAttrgroupRelation)
    {
        startPage();
        List<PmsAttrAttrgroupRelation> list = pmsAttrAttrgroupRelationService.selectPmsAttrAttrgroupRelationList(pmsAttrAttrgroupRelation);
        return getDataTable(list);
    }

    /**
     * 导出属性&属性分组关联列表
     */
    @RequiresPermissions("pms:relation:export")
    @Log(title = "属性&属性分组关联", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PmsAttrAttrgroupRelation pmsAttrAttrgroupRelation)
    {
        List<PmsAttrAttrgroupRelation> list = pmsAttrAttrgroupRelationService.selectPmsAttrAttrgroupRelationList(pmsAttrAttrgroupRelation);
        ExcelUtil<PmsAttrAttrgroupRelation> util = new ExcelUtil<PmsAttrAttrgroupRelation>(PmsAttrAttrgroupRelation.class);
        util.exportExcel(response, list, "属性&属性分组关联数据");
    }

    /**
     * 获取属性&属性分组关联详细信息
     */
    @RequiresPermissions("pms:relation:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(pmsAttrAttrgroupRelationService.selectPmsAttrAttrgroupRelationById(id));
    }

    /**
     * 新增属性&属性分组关联
     */
    @RequiresPermissions("pms:relation:add")
    @Log(title = "属性&属性分组关联", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PmsAttrAttrgroupRelation pmsAttrAttrgroupRelation)
    {
        return toAjax(pmsAttrAttrgroupRelationService.insertPmsAttrAttrgroupRelation(pmsAttrAttrgroupRelation));
    }

    /**
     * 修改属性&属性分组关联
     */
    @RequiresPermissions("pms:relation:edit")
    @Log(title = "属性&属性分组关联", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PmsAttrAttrgroupRelation pmsAttrAttrgroupRelation)
    {
        return toAjax(pmsAttrAttrgroupRelationService.updatePmsAttrAttrgroupRelation(pmsAttrAttrgroupRelation));
    }

    /**
     * 删除属性&属性分组关联
     */
    @RequiresPermissions("pms:relation:remove")
    @Log(title = "属性&属性分组关联", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(pmsAttrAttrgroupRelationService.deletePmsAttrAttrgroupRelationByIds(ids));
    }
}
