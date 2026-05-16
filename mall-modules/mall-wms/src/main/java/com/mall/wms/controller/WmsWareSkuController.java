package com.mall.wms.controller;

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
import com.mall.wms.domain.WmsWareSku;
import com.mall.wms.service.IWmsWareSkuService;
import com.mall.common.core.web.controller.BaseController;
import com.mall.common.core.web.domain.AjaxResult;
import com.mall.common.core.utils.poi.ExcelUtil;
import com.mall.common.core.web.page.TableDataInfo;

/**
 * 商品库存Controller
 * 
 * @author mall
 * @date 2026-04-11
 */
@RestController
@RequestMapping("/sku")
public class WmsWareSkuController extends BaseController
{
    @Autowired
    private IWmsWareSkuService wmsWareSkuService;

    /**
     * 查询商品库存列表
     */
    @RequiresPermissions("wms:sku:list")
    @GetMapping("/list")
    public TableDataInfo list(WmsWareSku wmsWareSku)
    {
        startPage();
        List<WmsWareSku> list = wmsWareSkuService.selectWmsWareSkuList(wmsWareSku);
        return getDataTable(list);
    }

    /**
     * 导出商品库存列表
     */
    @RequiresPermissions("wms:sku:export")
    @Log(title = "商品库存", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WmsWareSku wmsWareSku)
    {
        List<WmsWareSku> list = wmsWareSkuService.selectWmsWareSkuList(wmsWareSku);
        ExcelUtil<WmsWareSku> util = new ExcelUtil<WmsWareSku>(WmsWareSku.class);
        util.exportExcel(response, list, "商品库存数据");
    }

    /**
     * 获取商品库存详细信息
     */
    @RequiresPermissions("wms:sku:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(wmsWareSkuService.selectWmsWareSkuById(id));
    }

    /**
     * 新增商品库存
     */
    @RequiresPermissions("wms:sku:add")
    @Log(title = "商品库存", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WmsWareSku wmsWareSku)
    {
        return toAjax(wmsWareSkuService.insertWmsWareSku(wmsWareSku));
    }

    /**
     * 修改商品库存
     */
    @RequiresPermissions("wms:sku:edit")
    @Log(title = "商品库存", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WmsWareSku wmsWareSku)
    {
        return toAjax(wmsWareSkuService.updateWmsWareSku(wmsWareSku));
    }

    /**
     * 删除商品库存
     */
    @RequiresPermissions("wms:sku:remove")
    @Log(title = "商品库存", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(wmsWareSkuService.deleteWmsWareSkuByIds(ids));
    }
}
