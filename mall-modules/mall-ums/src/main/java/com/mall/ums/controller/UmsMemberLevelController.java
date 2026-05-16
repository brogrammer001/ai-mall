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
import com.mall.ums.domain.UmsMemberLevel;
import com.mall.ums.service.IUmsMemberLevelService;
import com.mall.common.core.web.controller.BaseController;
import com.mall.common.core.web.domain.AjaxResult;
import com.mall.common.core.utils.poi.ExcelUtil;
import com.mall.common.core.web.page.TableDataInfo;

/**
 * 会员等级Controller
 * 
 * @author mall
 * @date 2026-04-11
 */
@RestController
@RequestMapping("/level")
public class UmsMemberLevelController extends BaseController
{
    @Autowired
    private IUmsMemberLevelService umsMemberLevelService;

    /**
     * 查询会员等级列表
     */
    @RequiresPermissions("ums:level:list")
    @GetMapping("/list")
    public TableDataInfo list(UmsMemberLevel umsMemberLevel)
    {
        startPage();
        List<UmsMemberLevel> list = umsMemberLevelService.selectUmsMemberLevelList(umsMemberLevel);
        return getDataTable(list);
    }

    /**
     * 导出会员等级列表
     */
    @RequiresPermissions("ums:level:export")
    @Log(title = "会员等级", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, UmsMemberLevel umsMemberLevel)
    {
        List<UmsMemberLevel> list = umsMemberLevelService.selectUmsMemberLevelList(umsMemberLevel);
        ExcelUtil<UmsMemberLevel> util = new ExcelUtil<UmsMemberLevel>(UmsMemberLevel.class);
        util.exportExcel(response, list, "会员等级数据");
    }

    /**
     * 获取会员等级详细信息
     */
    @RequiresPermissions("ums:level:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(umsMemberLevelService.selectUmsMemberLevelById(id));
    }

    /**
     * 新增会员等级
     */
    @RequiresPermissions("ums:level:add")
    @Log(title = "会员等级", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UmsMemberLevel umsMemberLevel)
    {
        return toAjax(umsMemberLevelService.insertUmsMemberLevel(umsMemberLevel));
    }

    /**
     * 修改会员等级
     */
    @RequiresPermissions("ums:level:edit")
    @Log(title = "会员等级", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UmsMemberLevel umsMemberLevel)
    {
        return toAjax(umsMemberLevelService.updateUmsMemberLevel(umsMemberLevel));
    }

    /**
     * 删除会员等级
     */
    @RequiresPermissions("ums:level:remove")
    @Log(title = "会员等级", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(umsMemberLevelService.deleteUmsMemberLevelByIds(ids));
    }
}
