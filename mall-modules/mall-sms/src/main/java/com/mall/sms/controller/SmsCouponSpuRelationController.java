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
import com.mall.sms.domain.SmsCouponSpuRelation;
import com.mall.sms.service.ISmsCouponSpuRelationService;
import com.mall.common.core.web.controller.BaseController;
import com.mall.common.core.web.domain.AjaxResult;
import com.mall.common.core.utils.poi.ExcelUtil;
import com.mall.common.core.web.page.TableDataInfo;

/**
 * 优惠券与产品关联Controller
 * 
 * @author mall
 * @date 2026-04-11
 */
@RestController
@RequestMapping("/relation")
public class SmsCouponSpuRelationController extends BaseController
{
    @Autowired
    private ISmsCouponSpuRelationService smsCouponSpuRelationService;

    /**
     * 查询优惠券与产品关联列表
     */
    @RequiresPermissions("sms:relation:list")
    @GetMapping("/list")
    public TableDataInfo list(SmsCouponSpuRelation smsCouponSpuRelation)
    {
        startPage();
        List<SmsCouponSpuRelation> list = smsCouponSpuRelationService.selectSmsCouponSpuRelationList(smsCouponSpuRelation);
        return getDataTable(list);
    }

    /**
     * 导出优惠券与产品关联列表
     */
    @RequiresPermissions("sms:relation:export")
    @Log(title = "优惠券与产品关联", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SmsCouponSpuRelation smsCouponSpuRelation)
    {
        List<SmsCouponSpuRelation> list = smsCouponSpuRelationService.selectSmsCouponSpuRelationList(smsCouponSpuRelation);
        ExcelUtil<SmsCouponSpuRelation> util = new ExcelUtil<SmsCouponSpuRelation>(SmsCouponSpuRelation.class);
        util.exportExcel(response, list, "优惠券与产品关联数据");
    }

    /**
     * 获取优惠券与产品关联详细信息
     */
    @RequiresPermissions("sms:relation:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(smsCouponSpuRelationService.selectSmsCouponSpuRelationById(id));
    }

    /**
     * 新增优惠券与产品关联
     */
    @RequiresPermissions("sms:relation:add")
    @Log(title = "优惠券与产品关联", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SmsCouponSpuRelation smsCouponSpuRelation)
    {
        return toAjax(smsCouponSpuRelationService.insertSmsCouponSpuRelation(smsCouponSpuRelation));
    }

    /**
     * 修改优惠券与产品关联
     */
    @RequiresPermissions("sms:relation:edit")
    @Log(title = "优惠券与产品关联", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SmsCouponSpuRelation smsCouponSpuRelation)
    {
        return toAjax(smsCouponSpuRelationService.updateSmsCouponSpuRelation(smsCouponSpuRelation));
    }

    /**
     * 删除优惠券与产品关联
     */
    @RequiresPermissions("sms:relation:remove")
    @Log(title = "优惠券与产品关联", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(smsCouponSpuRelationService.deleteSmsCouponSpuRelationByIds(ids));
    }
}
