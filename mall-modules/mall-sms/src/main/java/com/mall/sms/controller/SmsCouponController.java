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
import com.mall.sms.domain.SmsCoupon;
import com.mall.sms.service.ISmsCouponService;
import com.mall.common.core.web.controller.BaseController;
import com.mall.common.core.web.domain.AjaxResult;
import com.mall.common.core.utils.poi.ExcelUtil;
import com.mall.common.core.web.page.TableDataInfo;

/**
 * 优惠券信息Controller
 * 
 * @author mall
 * @date 2026-04-11
 */
@RestController
@RequestMapping("/coupon")
public class SmsCouponController extends BaseController
{
    @Autowired
    private ISmsCouponService smsCouponService;

    /**
     * 查询优惠券信息列表
     */
    @RequiresPermissions("sms:coupon:list")
    @GetMapping("/list")
    public TableDataInfo list(SmsCoupon smsCoupon)
    {
        startPage();
        List<SmsCoupon> list = smsCouponService.selectSmsCouponList(smsCoupon);
        return getDataTable(list);
    }

    /**
     * 导出优惠券信息列表
     */
    @RequiresPermissions("sms:coupon:export")
    @Log(title = "优惠券信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SmsCoupon smsCoupon)
    {
        List<SmsCoupon> list = smsCouponService.selectSmsCouponList(smsCoupon);
        ExcelUtil<SmsCoupon> util = new ExcelUtil<SmsCoupon>(SmsCoupon.class);
        util.exportExcel(response, list, "优惠券信息数据");
    }

    /**
     * 获取优惠券信息详细信息
     */
    @RequiresPermissions("sms:coupon:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(smsCouponService.selectSmsCouponById(id));
    }

    /**
     * 新增优惠券信息
     */
    @RequiresPermissions("sms:coupon:add")
    @Log(title = "优惠券信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SmsCoupon smsCoupon)
    {
        return toAjax(smsCouponService.insertSmsCoupon(smsCoupon));
    }

    /**
     * 修改优惠券信息
     */
    @RequiresPermissions("sms:coupon:edit")
    @Log(title = "优惠券信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SmsCoupon smsCoupon)
    {
        return toAjax(smsCouponService.updateSmsCoupon(smsCoupon));
    }

    /**
     * 删除优惠券信息
     */
    @RequiresPermissions("sms:coupon:remove")
    @Log(title = "优惠券信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(smsCouponService.deleteSmsCouponByIds(ids));
    }
}
