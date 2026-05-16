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
import com.mall.pms.domain.PmsSpuComment;
import com.mall.pms.service.IPmsSpuCommentService;
import com.mall.common.core.web.controller.BaseController;
import com.mall.common.core.web.domain.AjaxResult;
import com.mall.common.core.utils.poi.ExcelUtil;
import com.mall.common.core.web.page.TableDataInfo;

/**
 * 商品评价Controller
 * 
 * @author mall
 * @date 2026-04-11
 */
@RestController
@RequestMapping("/comment")
public class PmsSpuCommentController extends BaseController
{
    @Autowired
    private IPmsSpuCommentService pmsSpuCommentService;

    /**
     * 查询商品评价列表
     */
    @RequiresPermissions("pms:comment:list")
    @GetMapping("/list")
    public TableDataInfo list(PmsSpuComment pmsSpuComment)
    {
        startPage();
        List<PmsSpuComment> list = pmsSpuCommentService.selectPmsSpuCommentList(pmsSpuComment);
        return getDataTable(list);
    }

    /**
     * 导出商品评价列表
     */
    @RequiresPermissions("pms:comment:export")
    @Log(title = "商品评价", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PmsSpuComment pmsSpuComment)
    {
        List<PmsSpuComment> list = pmsSpuCommentService.selectPmsSpuCommentList(pmsSpuComment);
        ExcelUtil<PmsSpuComment> util = new ExcelUtil<PmsSpuComment>(PmsSpuComment.class);
        util.exportExcel(response, list, "商品评价数据");
    }

    /**
     * 获取商品评价详细信息
     */
    @RequiresPermissions("pms:comment:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(pmsSpuCommentService.selectPmsSpuCommentById(id));
    }

    /**
     * 新增商品评价
     */
    @RequiresPermissions("pms:comment:add")
    @Log(title = "商品评价", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PmsSpuComment pmsSpuComment)
    {
        return toAjax(pmsSpuCommentService.insertPmsSpuComment(pmsSpuComment));
    }

    /**
     * 修改商品评价
     */
    @RequiresPermissions("pms:comment:edit")
    @Log(title = "商品评价", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PmsSpuComment pmsSpuComment)
    {
        return toAjax(pmsSpuCommentService.updatePmsSpuComment(pmsSpuComment));
    }

    /**
     * 删除商品评价
     */
    @RequiresPermissions("pms:comment:remove")
    @Log(title = "商品评价", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(pmsSpuCommentService.deletePmsSpuCommentByIds(ids));
    }
}
