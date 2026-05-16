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
import com.mall.sms.domain.SmsSeckillSkuNotice;
import com.mall.sms.service.ISmsSeckillSkuNoticeService;
import com.mall.common.core.web.controller.BaseController;
import com.mall.common.core.web.domain.AjaxResult;
import com.mall.common.core.utils.poi.ExcelUtil;
import com.mall.common.core.web.page.TableDataInfo;

/**
 * 秒杀商品通知订阅Controller
 * 
 * @author mall
 * @date 2026-04-11
 */
@RestController
@RequestMapping("/notice")
public class SmsSeckillSkuNoticeController extends BaseController
{
    @Autowired
    private ISmsSeckillSkuNoticeService smsSeckillSkuNoticeService;

    /**
     * 查询秒杀商品通知订阅列表
     */
    @RequiresPermissions("sms:notice:list")
    @GetMapping("/list")
    public TableDataInfo list(SmsSeckillSkuNotice smsSeckillSkuNotice)
    {
        startPage();
        List<SmsSeckillSkuNotice> list = smsSeckillSkuNoticeService.selectSmsSeckillSkuNoticeList(smsSeckillSkuNotice);
        return getDataTable(list);
    }

    /**
     * 导出秒杀商品通知订阅列表
     */
    @RequiresPermissions("sms:notice:export")
    @Log(title = "秒杀商品通知订阅", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SmsSeckillSkuNotice smsSeckillSkuNotice)
    {
        List<SmsSeckillSkuNotice> list = smsSeckillSkuNoticeService.selectSmsSeckillSkuNoticeList(smsSeckillSkuNotice);
        ExcelUtil<SmsSeckillSkuNotice> util = new ExcelUtil<SmsSeckillSkuNotice>(SmsSeckillSkuNotice.class);
        util.exportExcel(response, list, "秒杀商品通知订阅数据");
    }

    /**
     * 获取秒杀商品通知订阅详细信息
     */
    @RequiresPermissions("sms:notice:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(smsSeckillSkuNoticeService.selectSmsSeckillSkuNoticeById(id));
    }

    /**
     * 新增秒杀商品通知订阅
     */
    @RequiresPermissions("sms:notice:add")
    @Log(title = "秒杀商品通知订阅", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SmsSeckillSkuNotice smsSeckillSkuNotice)
    {
        return toAjax(smsSeckillSkuNoticeService.insertSmsSeckillSkuNotice(smsSeckillSkuNotice));
    }

    /**
     * 修改秒杀商品通知订阅
     */
    @RequiresPermissions("sms:notice:edit")
    @Log(title = "秒杀商品通知订阅", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SmsSeckillSkuNotice smsSeckillSkuNotice)
    {
        return toAjax(smsSeckillSkuNoticeService.updateSmsSeckillSkuNotice(smsSeckillSkuNotice));
    }

    /**
     * 删除秒杀商品通知订阅
     */
    @RequiresPermissions("sms:notice:remove")
    @Log(title = "秒杀商品通知订阅", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(smsSeckillSkuNoticeService.deleteSmsSeckillSkuNoticeByIds(ids));
    }
}
