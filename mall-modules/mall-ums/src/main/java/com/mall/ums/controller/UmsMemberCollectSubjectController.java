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
import com.mall.ums.domain.UmsMemberCollectSubject;
import com.mall.ums.service.IUmsMemberCollectSubjectService;
import com.mall.common.core.web.controller.BaseController;
import com.mall.common.core.web.domain.AjaxResult;
import com.mall.common.core.utils.poi.ExcelUtil;
import com.mall.common.core.web.page.TableDataInfo;

/**
 * 会员收藏的专题活动Controller
 * 
 * @author mall
 * @date 2026-04-11
 */
@RestController
@RequestMapping("/subject")
public class UmsMemberCollectSubjectController extends BaseController
{
    @Autowired
    private IUmsMemberCollectSubjectService umsMemberCollectSubjectService;

    /**
     * 查询会员收藏的专题活动列表
     */
    @RequiresPermissions("ums:subject:list")
    @GetMapping("/list")
    public TableDataInfo list(UmsMemberCollectSubject umsMemberCollectSubject)
    {
        startPage();
        List<UmsMemberCollectSubject> list = umsMemberCollectSubjectService.selectUmsMemberCollectSubjectList(umsMemberCollectSubject);
        return getDataTable(list);
    }

    /**
     * 导出会员收藏的专题活动列表
     */
    @RequiresPermissions("ums:subject:export")
    @Log(title = "会员收藏的专题活动", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, UmsMemberCollectSubject umsMemberCollectSubject)
    {
        List<UmsMemberCollectSubject> list = umsMemberCollectSubjectService.selectUmsMemberCollectSubjectList(umsMemberCollectSubject);
        ExcelUtil<UmsMemberCollectSubject> util = new ExcelUtil<UmsMemberCollectSubject>(UmsMemberCollectSubject.class);
        util.exportExcel(response, list, "会员收藏的专题活动数据");
    }

    /**
     * 获取会员收藏的专题活动详细信息
     */
    @RequiresPermissions("ums:subject:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(umsMemberCollectSubjectService.selectUmsMemberCollectSubjectById(id));
    }

    /**
     * 新增会员收藏的专题活动
     */
    @RequiresPermissions("ums:subject:add")
    @Log(title = "会员收藏的专题活动", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UmsMemberCollectSubject umsMemberCollectSubject)
    {
        return toAjax(umsMemberCollectSubjectService.insertUmsMemberCollectSubject(umsMemberCollectSubject));
    }

    /**
     * 修改会员收藏的专题活动
     */
    @RequiresPermissions("ums:subject:edit")
    @Log(title = "会员收藏的专题活动", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UmsMemberCollectSubject umsMemberCollectSubject)
    {
        return toAjax(umsMemberCollectSubjectService.updateUmsMemberCollectSubject(umsMemberCollectSubject));
    }

    /**
     * 删除会员收藏的专题活动
     */
    @RequiresPermissions("ums:subject:remove")
    @Log(title = "会员收藏的专题活动", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(umsMemberCollectSubjectService.deleteUmsMemberCollectSubjectByIds(ids));
    }
}
