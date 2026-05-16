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
import com.mall.pms.domain.PmsCategory;
import com.mall.pms.service.IPmsCategoryService;
import com.mall.common.core.web.controller.BaseController;
import com.mall.common.core.web.domain.AjaxResult;
import com.mall.common.core.utils.poi.ExcelUtil;
import com.mall.common.core.web.page.TableDataInfo;

/**
 * 商品三级分类Controller
 * 
 * @author mall
 * @date 2026-04-11
 */
@RestController
@RequestMapping("/category")
public class PmsCategoryController extends BaseController
{
    @Autowired
    private IPmsCategoryService pmsCategoryService;

    /**
     * 查询商品三级分类列表
     */
    @RequiresPermissions("pms:category:list")
    @GetMapping("/list")
    public TableDataInfo list(PmsCategory pmsCategory)
    {
        startPage();
        List<PmsCategory> list = pmsCategoryService.selectPmsCategoryList(pmsCategory);
        return getDataTable(list);
    }

    /**
     * 导出商品三级分类列表
     */
    @RequiresPermissions("pms:category:export")
    @Log(title = "商品三级分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PmsCategory pmsCategory)
    {
        List<PmsCategory> list = pmsCategoryService.selectPmsCategoryList(pmsCategory);
        ExcelUtil<PmsCategory> util = new ExcelUtil<PmsCategory>(PmsCategory.class);
        util.exportExcel(response, list, "商品三级分类数据");
    }

    /**
     * 获取商品三级分类详细信息
     */
    @RequiresPermissions("pms:category:query")
    @GetMapping(value = "/{catId}")
    public AjaxResult getInfo(@PathVariable("catId") Long catId)
    {
        return success(pmsCategoryService.selectPmsCategoryByCatId(catId));
    }

    /**
     * 新增商品三级分类
     */
    @RequiresPermissions("pms:category:add")
    @Log(title = "商品三级分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PmsCategory pmsCategory)
    {
        return toAjax(pmsCategoryService.insertPmsCategory(pmsCategory));
    }

    /**
     * 修改商品三级分类
     */
    @RequiresPermissions("pms:category:edit")
    @Log(title = "商品三级分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PmsCategory pmsCategory)
    {
        return toAjax(pmsCategoryService.updatePmsCategory(pmsCategory));
    }

    /**
     * 删除商品三级分类
     */
    @RequiresPermissions("pms:category:remove")
    @Log(title = "商品三级分类", businessType = BusinessType.DELETE)
	@DeleteMapping("/{catIds}")
    public AjaxResult remove(@PathVariable Long[] catIds)
    {
        return toAjax(pmsCategoryService.deletePmsCategoryByCatIds(catIds));
    }
}
