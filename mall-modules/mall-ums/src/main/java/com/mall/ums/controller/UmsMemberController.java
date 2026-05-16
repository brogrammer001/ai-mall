package com.mall.ums.controller;

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
import com.mall.ums.domain.UmsMember;
import com.mall.ums.service.IUmsMemberService;
import com.mall.common.core.web.controller.BaseController;
import com.mall.common.core.web.domain.AjaxResult;
import com.mall.common.core.utils.poi.ExcelUtil;
import com.mall.common.core.web.page.TableDataInfo;

/**
 * 会员Controller
 * 
 * @author mall
 * @date 2026-04-11
 */
@RestController
@RequestMapping("/member")
public class UmsMemberController extends BaseController
{
    @Autowired
    private IUmsMemberService umsMemberService;

    /**
     * 查询会员列表
     */
    @RequiresPermissions("ums:member:list")
    @GetMapping("/list")
    public TableDataInfo list(UmsMember umsMember)
    {
        startPage();
        List<UmsMember> list = umsMemberService.selectUmsMemberList(umsMember);
        return getDataTable(list);
    }

    /**
     * 导出会员列表
     */
    @RequiresPermissions("ums:member:export")
    @Log(title = "会员", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, UmsMember umsMember)
    {
        List<UmsMember> list = umsMemberService.selectUmsMemberList(umsMember);
        ExcelUtil<UmsMember> util = new ExcelUtil<UmsMember>(UmsMember.class);
        util.exportExcel(response, list, "会员数据");
    }

    /**
     * 获取会员详细信息
     */
    @RequiresPermissions("ums:member:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(umsMemberService.selectUmsMemberById(id));
    }

    /**
     * 新增会员
     */
    @RequiresPermissions("ums:member:add")
    @Log(title = "会员", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UmsMember umsMember)
    {
        return toAjax(umsMemberService.insertUmsMember(umsMember));
    }

    /**
     * 修改会员
     */
    @RequiresPermissions("ums:member:edit")
    @Log(title = "会员", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UmsMember umsMember)
    {
        return toAjax(umsMemberService.updateUmsMember(umsMember));
    }

    /**
     * 删除会员
     */
    @RequiresPermissions("ums:member:remove")
    @Log(title = "会员", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(umsMemberService.deleteUmsMemberByIds(ids));
    }
}
