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
import com.mall.pms.domain.PmsBrand;
import com.mall.pms.service.IPmsBrandService;
import com.mall.common.core.web.controller.BaseController;
import com.mall.common.core.web.domain.AjaxResult;
import com.mall.common.core.utils.poi.ExcelUtil;
import com.mall.common.core.web.page.TableDataInfo;

/**
 * 品牌Controller
 * 
 * @author mall
 * @date 2026-04-11
 */
@RestController
@RequestMapping("/brand")
public class PmsBrandController extends BaseController
{
    @Autowired
    private IPmsBrandService pmsBrandService;

    /**
     * 查询品牌列表
     */
    @RequiresPermissions("pms:brand:list")
    @GetMapping("/list")
    public TableDataInfo list(PmsBrand pmsBrand)
    {
        startPage();
        List<PmsBrand> list = pmsBrandService.selectPmsBrandList(pmsBrand);
        return getDataTable(list);
    }

    /**
     * 导出品牌列表
     */
    @RequiresPermissions("pms:brand:export")
    @Log(title = "品牌", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PmsBrand pmsBrand)
    {
        List<PmsBrand> list = pmsBrandService.selectPmsBrandList(pmsBrand);
        ExcelUtil<PmsBrand> util = new ExcelUtil<PmsBrand>(PmsBrand.class);
        util.exportExcel(response, list, "品牌数据");
    }

    /**
     * 获取品牌详细信息
     */
    @RequiresPermissions("pms:brand:query")
    @GetMapping(value = "/{brandId}")
    public AjaxResult getInfo(@PathVariable("brandId") Long brandId)
    {
        return success(pmsBrandService.selectPmsBrandByBrandId(brandId));
    }

    /**
     * 新增品牌
     */
    @RequiresPermissions("pms:brand:add")
    @Log(title = "品牌", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PmsBrand pmsBrand)
    {
        return toAjax(pmsBrandService.insertPmsBrand(pmsBrand));
    }

    /**
     * 修改品牌
     */
    @RequiresPermissions("pms:brand:edit")
    @Log(title = "品牌", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PmsBrand pmsBrand)
    {
        return toAjax(pmsBrandService.updatePmsBrand(pmsBrand));
    }

    /**
     * 删除品牌
     */
    @RequiresPermissions("pms:brand:remove")
    @Log(title = "品牌", businessType = BusinessType.DELETE)
	@DeleteMapping("/{brandIds}")
    public AjaxResult remove(@PathVariable Long[] brandIds)
    {
        return toAjax(pmsBrandService.deletePmsBrandByBrandIds(brandIds));
    }
}
