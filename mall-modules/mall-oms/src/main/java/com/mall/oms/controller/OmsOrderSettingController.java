package com.mall.oms.controller;

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
import com.mall.oms.domain.OmsOrderSetting;
import com.mall.oms.service.IOmsOrderSettingService;
import com.mall.common.core.web.controller.BaseController;
import com.mall.common.core.web.domain.AjaxResult;
import com.mall.common.core.utils.poi.ExcelUtil;
import com.mall.common.core.web.page.TableDataInfo;

/**
 * 订单配置信息Controller
 * 
 * @author mall
 * @date 2026-04-11
 */
@RestController
@RequestMapping("/setting")
public class OmsOrderSettingController extends BaseController
{
    @Autowired
    private IOmsOrderSettingService omsOrderSettingService;

    /**
     * 查询订单配置信息列表
     */
    @RequiresPermissions("oms:setting:list")
    @GetMapping("/list")
    public TableDataInfo list(OmsOrderSetting omsOrderSetting)
    {
        startPage();
        List<OmsOrderSetting> list = omsOrderSettingService.selectOmsOrderSettingList(omsOrderSetting);
        return getDataTable(list);
    }

    /**
     * 导出订单配置信息列表
     */
    @RequiresPermissions("oms:setting:export")
    @Log(title = "订单配置信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, OmsOrderSetting omsOrderSetting)
    {
        List<OmsOrderSetting> list = omsOrderSettingService.selectOmsOrderSettingList(omsOrderSetting);
        ExcelUtil<OmsOrderSetting> util = new ExcelUtil<OmsOrderSetting>(OmsOrderSetting.class);
        util.exportExcel(response, list, "订单配置信息数据");
    }

    /**
     * 获取订单配置信息详细信息
     */
    @RequiresPermissions("oms:setting:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(omsOrderSettingService.selectOmsOrderSettingById(id));
    }

    /**
     * 新增订单配置信息
     */
    @RequiresPermissions("oms:setting:add")
    @Log(title = "订单配置信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OmsOrderSetting omsOrderSetting)
    {
        return toAjax(omsOrderSettingService.insertOmsOrderSetting(omsOrderSetting));
    }

    /**
     * 修改订单配置信息
     */
    @RequiresPermissions("oms:setting:edit")
    @Log(title = "订单配置信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OmsOrderSetting omsOrderSetting)
    {
        return toAjax(omsOrderSettingService.updateOmsOrderSetting(omsOrderSetting));
    }

    /**
     * 删除订单配置信息
     */
    @RequiresPermissions("oms:setting:remove")
    @Log(title = "订单配置信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(omsOrderSettingService.deleteOmsOrderSettingByIds(ids));
    }
}
