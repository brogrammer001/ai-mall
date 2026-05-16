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
import com.mall.sms.domain.SmsSkuLadder;
import com.mall.sms.service.ISmsSkuLadderService;
import com.mall.common.core.web.controller.BaseController;
import com.mall.common.core.web.domain.AjaxResult;
import com.mall.common.core.utils.poi.ExcelUtil;
import com.mall.common.core.web.page.TableDataInfo;

/**
 * 商品阶梯价格Controller
 * 
 * @author mall
 * @date 2026-04-11
 */
@RestController
@RequestMapping("/ladder")
public class SmsSkuLadderController extends BaseController
{
    @Autowired
    private ISmsSkuLadderService smsSkuLadderService;

    /**
     * 查询商品阶梯价格列表
     */
    @RequiresPermissions("sms:ladder:list")
    @GetMapping("/list")
    public TableDataInfo list(SmsSkuLadder smsSkuLadder)
    {
        startPage();
        List<SmsSkuLadder> list = smsSkuLadderService.selectSmsSkuLadderList(smsSkuLadder);
        return getDataTable(list);
    }

    /**
     * 导出商品阶梯价格列表
     */
    @RequiresPermissions("sms:ladder:export")
    @Log(title = "商品阶梯价格", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SmsSkuLadder smsSkuLadder)
    {
        List<SmsSkuLadder> list = smsSkuLadderService.selectSmsSkuLadderList(smsSkuLadder);
        ExcelUtil<SmsSkuLadder> util = new ExcelUtil<SmsSkuLadder>(SmsSkuLadder.class);
        util.exportExcel(response, list, "商品阶梯价格数据");
    }

    /**
     * 获取商品阶梯价格详细信息
     */
    @RequiresPermissions("sms:ladder:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(smsSkuLadderService.selectSmsSkuLadderById(id));
    }

    /**
     * 新增商品阶梯价格
     */
    @RequiresPermissions("sms:ladder:add")
    @Log(title = "商品阶梯价格", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SmsSkuLadder smsSkuLadder)
    {
        return toAjax(smsSkuLadderService.insertSmsSkuLadder(smsSkuLadder));
    }

    /**
     * 修改商品阶梯价格
     */
    @RequiresPermissions("sms:ladder:edit")
    @Log(title = "商品阶梯价格", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SmsSkuLadder smsSkuLadder)
    {
        return toAjax(smsSkuLadderService.updateSmsSkuLadder(smsSkuLadder));
    }

    /**
     * 删除商品阶梯价格
     */
    @RequiresPermissions("sms:ladder:remove")
    @Log(title = "商品阶梯价格", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(smsSkuLadderService.deleteSmsSkuLadderByIds(ids));
    }
}
