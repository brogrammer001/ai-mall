package com.mall.pms.controller;

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
import com.mall.pms.domain.PmsCommentReplay;
import com.mall.pms.service.IPmsCommentReplayService;
import com.mall.common.core.web.controller.BaseController;
import com.mall.common.core.web.domain.AjaxResult;
import com.mall.common.core.utils.poi.ExcelUtil;
import com.mall.common.core.web.page.TableDataInfo;

/**
 * 商品评价回复关系Controller
 * 
 * @author mall
 * @date 2026-04-11
 */
@RestController
@RequestMapping("/replay")
public class PmsCommentReplayController extends BaseController
{
    @Autowired
    private IPmsCommentReplayService pmsCommentReplayService;

    /**
     * 查询商品评价回复关系列表
     */
    @RequiresPermissions("pms:replay:list")
    @GetMapping("/list")
    public TableDataInfo list(PmsCommentReplay pmsCommentReplay)
    {
        startPage();
        List<PmsCommentReplay> list = pmsCommentReplayService.selectPmsCommentReplayList(pmsCommentReplay);
        return getDataTable(list);
    }

    /**
     * 导出商品评价回复关系列表
     */
    @RequiresPermissions("pms:replay:export")
    @Log(title = "商品评价回复关系", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PmsCommentReplay pmsCommentReplay)
    {
        List<PmsCommentReplay> list = pmsCommentReplayService.selectPmsCommentReplayList(pmsCommentReplay);
        ExcelUtil<PmsCommentReplay> util = new ExcelUtil<PmsCommentReplay>(PmsCommentReplay.class);
        util.exportExcel(response, list, "商品评价回复关系数据");
    }

    /**
     * 获取商品评价回复关系详细信息
     */
    @RequiresPermissions("pms:replay:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(pmsCommentReplayService.selectPmsCommentReplayById(id));
    }

    /**
     * 新增商品评价回复关系
     */
    @RequiresPermissions("pms:replay:add")
    @Log(title = "商品评价回复关系", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PmsCommentReplay pmsCommentReplay)
    {
        return toAjax(pmsCommentReplayService.insertPmsCommentReplay(pmsCommentReplay));
    }

    /**
     * 修改商品评价回复关系
     */
    @RequiresPermissions("pms:replay:edit")
    @Log(title = "商品评价回复关系", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PmsCommentReplay pmsCommentReplay)
    {
        return toAjax(pmsCommentReplayService.updatePmsCommentReplay(pmsCommentReplay));
    }

    /**
     * 删除商品评价回复关系
     */
    @RequiresPermissions("pms:replay:remove")
    @Log(title = "商品评价回复关系", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(pmsCommentReplayService.deletePmsCommentReplayByIds(ids));
    }
}
