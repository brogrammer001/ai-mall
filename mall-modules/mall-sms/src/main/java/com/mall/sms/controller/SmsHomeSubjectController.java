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
import com.mall.sms.domain.SmsHomeSubject;
import com.mall.sms.service.ISmsHomeSubjectService;
import com.mall.common.core.web.controller.BaseController;
import com.mall.common.core.web.domain.AjaxResult;
import com.mall.common.core.utils.poi.ExcelUtil;
import com.mall.common.core.web.page.TableDataInfo;

/**
 * 首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】Controller
 * 
 * @author mall
 * @date 2026-04-11
 */
@RestController
@RequestMapping("/subject")
public class SmsHomeSubjectController extends BaseController
{
    @Autowired
    private ISmsHomeSubjectService smsHomeSubjectService;

    /**
     * 查询首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】列表
     */
    @RequiresPermissions("sms:subject:list")
    @GetMapping("/list")
    public TableDataInfo list(SmsHomeSubject smsHomeSubject)
    {
        startPage();
        List<SmsHomeSubject> list = smsHomeSubjectService.selectSmsHomeSubjectList(smsHomeSubject);
        return getDataTable(list);
    }

    /**
     * 导出首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】列表
     */
    @RequiresPermissions("sms:subject:export")
    @Log(title = "首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SmsHomeSubject smsHomeSubject)
    {
        List<SmsHomeSubject> list = smsHomeSubjectService.selectSmsHomeSubjectList(smsHomeSubject);
        ExcelUtil<SmsHomeSubject> util = new ExcelUtil<SmsHomeSubject>(SmsHomeSubject.class);
        util.exportExcel(response, list, "首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】数据");
    }

    /**
     * 获取首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】详细信息
     */
    @RequiresPermissions("sms:subject:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(smsHomeSubjectService.selectSmsHomeSubjectById(id));
    }

    /**
     * 新增首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
     */
    @RequiresPermissions("sms:subject:add")
    @Log(title = "首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SmsHomeSubject smsHomeSubject)
    {
        return toAjax(smsHomeSubjectService.insertSmsHomeSubject(smsHomeSubject));
    }

    /**
     * 修改首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
     */
    @RequiresPermissions("sms:subject:edit")
    @Log(title = "首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SmsHomeSubject smsHomeSubject)
    {
        return toAjax(smsHomeSubjectService.updateSmsHomeSubject(smsHomeSubject));
    }

    /**
     * 删除首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
     */
    @RequiresPermissions("sms:subject:remove")
    @Log(title = "首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(smsHomeSubjectService.deleteSmsHomeSubjectByIds(ids));
    }
}
