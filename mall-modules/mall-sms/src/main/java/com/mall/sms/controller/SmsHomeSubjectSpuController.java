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
import com.mall.sms.domain.SmsHomeSubjectSpu;
import com.mall.sms.service.ISmsHomeSubjectSpuService;
import com.mall.common.core.web.controller.BaseController;
import com.mall.common.core.web.domain.AjaxResult;
import com.mall.common.core.utils.poi.ExcelUtil;
import com.mall.common.core.web.page.TableDataInfo;

/**
 * 专题商品Controller
 * 
 * @author mall
 * @date 2026-04-11
 */
@RestController
@RequestMapping("/spu")
public class SmsHomeSubjectSpuController extends BaseController
{
    @Autowired
    private ISmsHomeSubjectSpuService smsHomeSubjectSpuService;

    /**
     * 查询专题商品列表
     */
    @RequiresPermissions("sms:spu:list")
    @GetMapping("/list")
    public TableDataInfo list(SmsHomeSubjectSpu smsHomeSubjectSpu)
    {
        startPage();
        List<SmsHomeSubjectSpu> list = smsHomeSubjectSpuService.selectSmsHomeSubjectSpuList(smsHomeSubjectSpu);
        return getDataTable(list);
    }

    /**
     * 导出专题商品列表
     */
    @RequiresPermissions("sms:spu:export")
    @Log(title = "专题商品", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SmsHomeSubjectSpu smsHomeSubjectSpu)
    {
        List<SmsHomeSubjectSpu> list = smsHomeSubjectSpuService.selectSmsHomeSubjectSpuList(smsHomeSubjectSpu);
        ExcelUtil<SmsHomeSubjectSpu> util = new ExcelUtil<SmsHomeSubjectSpu>(SmsHomeSubjectSpu.class);
        util.exportExcel(response, list, "专题商品数据");
    }

    /**
     * 获取专题商品详细信息
     */
    @RequiresPermissions("sms:spu:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(smsHomeSubjectSpuService.selectSmsHomeSubjectSpuById(id));
    }

    /**
     * 新增专题商品
     */
    @RequiresPermissions("sms:spu:add")
    @Log(title = "专题商品", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SmsHomeSubjectSpu smsHomeSubjectSpu)
    {
        return toAjax(smsHomeSubjectSpuService.insertSmsHomeSubjectSpu(smsHomeSubjectSpu));
    }

    /**
     * 修改专题商品
     */
    @RequiresPermissions("sms:spu:edit")
    @Log(title = "专题商品", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SmsHomeSubjectSpu smsHomeSubjectSpu)
    {
        return toAjax(smsHomeSubjectSpuService.updateSmsHomeSubjectSpu(smsHomeSubjectSpu));
    }

    /**
     * 删除专题商品
     */
    @RequiresPermissions("sms:spu:remove")
    @Log(title = "专题商品", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(smsHomeSubjectSpuService.deleteSmsHomeSubjectSpuByIds(ids));
    }
}
