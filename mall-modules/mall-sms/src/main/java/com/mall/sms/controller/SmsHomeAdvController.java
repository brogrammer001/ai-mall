package com.mall.sms.controller;

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
import com.mall.sms.domain.SmsHomeAdv;
import com.mall.sms.service.ISmsHomeAdvService;
import com.mall.common.core.web.controller.BaseController;
import com.mall.common.core.web.domain.AjaxResult;
import com.mall.common.core.utils.poi.ExcelUtil;
import com.mall.common.core.web.page.TableDataInfo;

/**
 * 首页轮播广告Controller
 * 
 * @author mall
 * @date 2026-04-11
 */
@RestController
@RequestMapping("/adv")
public class SmsHomeAdvController extends BaseController
{
    @Autowired
    private ISmsHomeAdvService smsHomeAdvService;

    /**
     * 查询首页轮播广告列表
     */
    @RequiresPermissions("sms:adv:list")
    @GetMapping("/list")
    public TableDataInfo list(SmsHomeAdv smsHomeAdv)
    {
        startPage();
        List<SmsHomeAdv> list = smsHomeAdvService.selectSmsHomeAdvList(smsHomeAdv);
        return getDataTable(list);
    }

    /**
     * 导出首页轮播广告列表
     */
    @RequiresPermissions("sms:adv:export")
    @Log(title = "首页轮播广告", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SmsHomeAdv smsHomeAdv)
    {
        List<SmsHomeAdv> list = smsHomeAdvService.selectSmsHomeAdvList(smsHomeAdv);
        ExcelUtil<SmsHomeAdv> util = new ExcelUtil<SmsHomeAdv>(SmsHomeAdv.class);
        util.exportExcel(response, list, "首页轮播广告数据");
    }

    /**
     * 获取首页轮播广告详细信息
     */
    @RequiresPermissions("sms:adv:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(smsHomeAdvService.selectSmsHomeAdvById(id));
    }

    /**
     * 新增首页轮播广告
     */
    @RequiresPermissions("sms:adv:add")
    @Log(title = "首页轮播广告", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SmsHomeAdv smsHomeAdv)
    {
        return toAjax(smsHomeAdvService.insertSmsHomeAdv(smsHomeAdv));
    }

    /**
     * 修改首页轮播广告
     */
    @RequiresPermissions("sms:adv:edit")
    @Log(title = "首页轮播广告", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SmsHomeAdv smsHomeAdv)
    {
        return toAjax(smsHomeAdvService.updateSmsHomeAdv(smsHomeAdv));
    }

    /**
     * 删除首页轮播广告
     */
    @RequiresPermissions("sms:adv:remove")
    @Log(title = "首页轮播广告", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(smsHomeAdvService.deleteSmsHomeAdvByIds(ids));
    }
}
