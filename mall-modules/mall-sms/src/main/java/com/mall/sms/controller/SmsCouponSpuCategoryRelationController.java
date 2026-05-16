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
import com.mall.sms.domain.SmsCouponSpuCategoryRelation;
import com.mall.sms.service.ISmsCouponSpuCategoryRelationService;
import com.mall.common.core.web.controller.BaseController;
import com.mall.common.core.web.domain.AjaxResult;
import com.mall.common.core.utils.poi.ExcelUtil;
import com.mall.common.core.web.page.TableDataInfo;

/**
 * 优惠券分类关联Controller
 * 
 * @author mall
 * @date 2026-04-11
 */
@RestController
@RequestMapping("/relation")
public class SmsCouponSpuCategoryRelationController extends BaseController
{
    @Autowired
    private ISmsCouponSpuCategoryRelationService smsCouponSpuCategoryRelationService;

    /**
     * 查询优惠券分类关联列表
     */
    @RequiresPermissions("sms:relation:list")
    @GetMapping("/list")
    public TableDataInfo list(SmsCouponSpuCategoryRelation smsCouponSpuCategoryRelation)
    {
        startPage();
        List<SmsCouponSpuCategoryRelation> list = smsCouponSpuCategoryRelationService.selectSmsCouponSpuCategoryRelationList(smsCouponSpuCategoryRelation);
        return getDataTable(list);
    }

    /**
     * 导出优惠券分类关联列表
     */
    @RequiresPermissions("sms:relation:export")
    @Log(title = "优惠券分类关联", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SmsCouponSpuCategoryRelation smsCouponSpuCategoryRelation)
    {
        List<SmsCouponSpuCategoryRelation> list = smsCouponSpuCategoryRelationService.selectSmsCouponSpuCategoryRelationList(smsCouponSpuCategoryRelation);
        ExcelUtil<SmsCouponSpuCategoryRelation> util = new ExcelUtil<SmsCouponSpuCategoryRelation>(SmsCouponSpuCategoryRelation.class);
        util.exportExcel(response, list, "优惠券分类关联数据");
    }

    /**
     * 获取优惠券分类关联详细信息
     */
    @RequiresPermissions("sms:relation:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(smsCouponSpuCategoryRelationService.selectSmsCouponSpuCategoryRelationById(id));
    }

    /**
     * 新增优惠券分类关联
     */
    @RequiresPermissions("sms:relation:add")
    @Log(title = "优惠券分类关联", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SmsCouponSpuCategoryRelation smsCouponSpuCategoryRelation)
    {
        return toAjax(smsCouponSpuCategoryRelationService.insertSmsCouponSpuCategoryRelation(smsCouponSpuCategoryRelation));
    }

    /**
     * 修改优惠券分类关联
     */
    @RequiresPermissions("sms:relation:edit")
    @Log(title = "优惠券分类关联", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SmsCouponSpuCategoryRelation smsCouponSpuCategoryRelation)
    {
        return toAjax(smsCouponSpuCategoryRelationService.updateSmsCouponSpuCategoryRelation(smsCouponSpuCategoryRelation));
    }

    /**
     * 删除优惠券分类关联
     */
    @RequiresPermissions("sms:relation:remove")
    @Log(title = "优惠券分类关联", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(smsCouponSpuCategoryRelationService.deleteSmsCouponSpuCategoryRelationByIds(ids));
    }
}
