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
import com.mall.pms.domain.PmsCategoryBrandRelation;
import com.mall.pms.service.IPmsCategoryBrandRelationService;
import com.mall.common.core.web.controller.BaseController;
import com.mall.common.core.web.domain.AjaxResult;
import com.mall.common.core.utils.poi.ExcelUtil;
import com.mall.common.core.web.page.TableDataInfo;

/**
 * 品牌分类关联Controller
 * 
 * @author mall
 * @date 2026-04-11
 */
@RestController
@RequestMapping("/relation")
public class PmsCategoryBrandRelationController extends BaseController
{
    @Autowired
    private IPmsCategoryBrandRelationService pmsCategoryBrandRelationService;

    /**
     * 查询品牌分类关联列表
     */
    @RequiresPermissions("pms:relation:list")
    @GetMapping("/list")
    public TableDataInfo list(PmsCategoryBrandRelation pmsCategoryBrandRelation)
    {
        startPage();
        List<PmsCategoryBrandRelation> list = pmsCategoryBrandRelationService.selectPmsCategoryBrandRelationList(pmsCategoryBrandRelation);
        return getDataTable(list);
    }

    /**
     * 导出品牌分类关联列表
     */
    @RequiresPermissions("pms:relation:export")
    @Log(title = "品牌分类关联", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PmsCategoryBrandRelation pmsCategoryBrandRelation)
    {
        List<PmsCategoryBrandRelation> list = pmsCategoryBrandRelationService.selectPmsCategoryBrandRelationList(pmsCategoryBrandRelation);
        ExcelUtil<PmsCategoryBrandRelation> util = new ExcelUtil<PmsCategoryBrandRelation>(PmsCategoryBrandRelation.class);
        util.exportExcel(response, list, "品牌分类关联数据");
    }

    /**
     * 获取品牌分类关联详细信息
     */
    @RequiresPermissions("pms:relation:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(pmsCategoryBrandRelationService.selectPmsCategoryBrandRelationById(id));
    }

    /**
     * 新增品牌分类关联
     */
    @RequiresPermissions("pms:relation:add")
    @Log(title = "品牌分类关联", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PmsCategoryBrandRelation pmsCategoryBrandRelation)
    {
        return toAjax(pmsCategoryBrandRelationService.insertPmsCategoryBrandRelation(pmsCategoryBrandRelation));
    }

    /**
     * 修改品牌分类关联
     */
    @RequiresPermissions("pms:relation:edit")
    @Log(title = "品牌分类关联", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PmsCategoryBrandRelation pmsCategoryBrandRelation)
    {
        return toAjax(pmsCategoryBrandRelationService.updatePmsCategoryBrandRelation(pmsCategoryBrandRelation));
    }

    /**
     * 删除品牌分类关联
     */
    @RequiresPermissions("pms:relation:remove")
    @Log(title = "品牌分类关联", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(pmsCategoryBrandRelationService.deletePmsCategoryBrandRelationByIds(ids));
    }
}
