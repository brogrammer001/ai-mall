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
import com.mall.pms.domain.PmsSkuImages;
import com.mall.pms.service.IPmsSkuImagesService;
import com.mall.common.core.web.controller.BaseController;
import com.mall.common.core.web.domain.AjaxResult;
import com.mall.common.core.utils.poi.ExcelUtil;
import com.mall.common.core.web.page.TableDataInfo;

/**
 * sku图片Controller
 * 
 * @author mall
 * @date 2026-04-11
 */
@RestController
@RequestMapping("/images")
public class PmsSkuImagesController extends BaseController
{
    @Autowired
    private IPmsSkuImagesService pmsSkuImagesService;

    /**
     * 查询sku图片列表
     */
    @RequiresPermissions("pms:images:list")
    @GetMapping("/list")
    public TableDataInfo list(PmsSkuImages pmsSkuImages)
    {
        startPage();
        List<PmsSkuImages> list = pmsSkuImagesService.selectPmsSkuImagesList(pmsSkuImages);
        return getDataTable(list);
    }

    /**
     * 导出sku图片列表
     */
    @RequiresPermissions("pms:images:export")
    @Log(title = "sku图片", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PmsSkuImages pmsSkuImages)
    {
        List<PmsSkuImages> list = pmsSkuImagesService.selectPmsSkuImagesList(pmsSkuImages);
        ExcelUtil<PmsSkuImages> util = new ExcelUtil<PmsSkuImages>(PmsSkuImages.class);
        util.exportExcel(response, list, "sku图片数据");
    }

    /**
     * 获取sku图片详细信息
     */
    @RequiresPermissions("pms:images:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(pmsSkuImagesService.selectPmsSkuImagesById(id));
    }

    /**
     * 新增sku图片
     */
    @RequiresPermissions("pms:images:add")
    @Log(title = "sku图片", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PmsSkuImages pmsSkuImages)
    {
        return toAjax(pmsSkuImagesService.insertPmsSkuImages(pmsSkuImages));
    }

    /**
     * 修改sku图片
     */
    @RequiresPermissions("pms:images:edit")
    @Log(title = "sku图片", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PmsSkuImages pmsSkuImages)
    {
        return toAjax(pmsSkuImagesService.updatePmsSkuImages(pmsSkuImages));
    }

    /**
     * 删除sku图片
     */
    @RequiresPermissions("pms:images:remove")
    @Log(title = "sku图片", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(pmsSkuImagesService.deletePmsSkuImagesByIds(ids));
    }
}
