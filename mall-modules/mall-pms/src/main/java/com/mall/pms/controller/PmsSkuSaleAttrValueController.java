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
import com.mall.pms.domain.PmsSkuSaleAttrValue;
import com.mall.pms.service.IPmsSkuSaleAttrValueService;
import com.mall.common.core.web.controller.BaseController;
import com.mall.common.core.web.domain.AjaxResult;
import com.mall.common.core.utils.poi.ExcelUtil;
import com.mall.common.core.web.page.TableDataInfo;

/**
 * sku销售属性&值Controller
 * 
 * @author mall
 * @date 2026-04-11
 */
@RestController
@RequestMapping("/value")
public class PmsSkuSaleAttrValueController extends BaseController
{
    @Autowired
    private IPmsSkuSaleAttrValueService pmsSkuSaleAttrValueService;

    /**
     * 查询sku销售属性&值列表
     */
    @RequiresPermissions("pms:value:list")
    @GetMapping("/list")
    public TableDataInfo list(PmsSkuSaleAttrValue pmsSkuSaleAttrValue)
    {
        startPage();
        List<PmsSkuSaleAttrValue> list = pmsSkuSaleAttrValueService.selectPmsSkuSaleAttrValueList(pmsSkuSaleAttrValue);
        return getDataTable(list);
    }

    /**
     * 导出sku销售属性&值列表
     */
    @RequiresPermissions("pms:value:export")
    @Log(title = "sku销售属性&值", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PmsSkuSaleAttrValue pmsSkuSaleAttrValue)
    {
        List<PmsSkuSaleAttrValue> list = pmsSkuSaleAttrValueService.selectPmsSkuSaleAttrValueList(pmsSkuSaleAttrValue);
        ExcelUtil<PmsSkuSaleAttrValue> util = new ExcelUtil<PmsSkuSaleAttrValue>(PmsSkuSaleAttrValue.class);
        util.exportExcel(response, list, "sku销售属性&值数据");
    }

    /**
     * 获取sku销售属性&值详细信息
     */
    @RequiresPermissions("pms:value:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(pmsSkuSaleAttrValueService.selectPmsSkuSaleAttrValueById(id));
    }

    /**
     * 新增sku销售属性&值
     */
    @RequiresPermissions("pms:value:add")
    @Log(title = "sku销售属性&值", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PmsSkuSaleAttrValue pmsSkuSaleAttrValue)
    {
        return toAjax(pmsSkuSaleAttrValueService.insertPmsSkuSaleAttrValue(pmsSkuSaleAttrValue));
    }

    /**
     * 修改sku销售属性&值
     */
    @RequiresPermissions("pms:value:edit")
    @Log(title = "sku销售属性&值", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PmsSkuSaleAttrValue pmsSkuSaleAttrValue)
    {
        return toAjax(pmsSkuSaleAttrValueService.updatePmsSkuSaleAttrValue(pmsSkuSaleAttrValue));
    }

    /**
     * 删除sku销售属性&值
     */
    @RequiresPermissions("pms:value:remove")
    @Log(title = "sku销售属性&值", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(pmsSkuSaleAttrValueService.deletePmsSkuSaleAttrValueByIds(ids));
    }
}
