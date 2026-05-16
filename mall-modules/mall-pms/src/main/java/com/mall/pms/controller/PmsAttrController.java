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
import com.mall.pms.domain.PmsAttr;
import com.mall.pms.service.IPmsAttrService;
import com.mall.common.core.web.controller.BaseController;
import com.mall.common.core.web.domain.AjaxResult;
import com.mall.common.core.utils.poi.ExcelUtil;
import com.mall.common.core.web.page.TableDataInfo;

/**
 * 商品属性Controller
 * 
 * @author mall
 * @date 2026-04-11
 */
@RestController
@RequestMapping("/attr")
public class PmsAttrController extends BaseController
{
    @Autowired
    private IPmsAttrService pmsAttrService;

    /**
     * 查询商品属性列表
     */
    @RequiresPermissions("pms:attr:list")
    @GetMapping("/list")
    public TableDataInfo list(PmsAttr pmsAttr)
    {
        startPage();
        List<PmsAttr> list = pmsAttrService.selectPmsAttrList(pmsAttr);
        return getDataTable(list);
    }

    /**
     * 导出商品属性列表
     */
    @RequiresPermissions("pms:attr:export")
    @Log(title = "商品属性", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PmsAttr pmsAttr)
    {
        List<PmsAttr> list = pmsAttrService.selectPmsAttrList(pmsAttr);
        ExcelUtil<PmsAttr> util = new ExcelUtil<PmsAttr>(PmsAttr.class);
        util.exportExcel(response, list, "商品属性数据");
    }

    /**
     * 获取商品属性详细信息
     */
    @RequiresPermissions("pms:attr:query")
    @GetMapping(value = "/{attrId}")
    public AjaxResult getInfo(@PathVariable("attrId") Long attrId)
    {
        return success(pmsAttrService.selectPmsAttrByAttrId(attrId));
    }

    /**
     * 新增商品属性
     */
    @RequiresPermissions("pms:attr:add")
    @Log(title = "商品属性", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PmsAttr pmsAttr)
    {
        return toAjax(pmsAttrService.insertPmsAttr(pmsAttr));
    }

    /**
     * 修改商品属性
     */
    @RequiresPermissions("pms:attr:edit")
    @Log(title = "商品属性", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PmsAttr pmsAttr)
    {
        return toAjax(pmsAttrService.updatePmsAttr(pmsAttr));
    }

    /**
     * 删除商品属性
     */
    @RequiresPermissions("pms:attr:remove")
    @Log(title = "商品属性", businessType = BusinessType.DELETE)
	@DeleteMapping("/{attrIds}")
    public AjaxResult remove(@PathVariable Long[] attrIds)
    {
        return toAjax(pmsAttrService.deletePmsAttrByAttrIds(attrIds));
    }
}
