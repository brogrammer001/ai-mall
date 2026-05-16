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
import com.mall.pms.domain.PmsSpuInfoDesc;
import com.mall.pms.service.IPmsSpuInfoDescService;
import com.mall.common.core.web.controller.BaseController;
import com.mall.common.core.web.domain.AjaxResult;
import com.mall.common.core.utils.poi.ExcelUtil;
import com.mall.common.core.web.page.TableDataInfo;

/**
 * spu信息介绍Controller
 * 
 * @author mall
 * @date 2026-04-11
 */
@RestController
@RequestMapping("/desc")
public class PmsSpuInfoDescController extends BaseController
{
    @Autowired
    private IPmsSpuInfoDescService pmsSpuInfoDescService;

    /**
     * 查询spu信息介绍列表
     */
    @RequiresPermissions("pms:desc:list")
    @GetMapping("/list")
    public TableDataInfo list(PmsSpuInfoDesc pmsSpuInfoDesc)
    {
        startPage();
        List<PmsSpuInfoDesc> list = pmsSpuInfoDescService.selectPmsSpuInfoDescList(pmsSpuInfoDesc);
        return getDataTable(list);
    }

    /**
     * 导出spu信息介绍列表
     */
    @RequiresPermissions("pms:desc:export")
    @Log(title = "spu信息介绍", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PmsSpuInfoDesc pmsSpuInfoDesc)
    {
        List<PmsSpuInfoDesc> list = pmsSpuInfoDescService.selectPmsSpuInfoDescList(pmsSpuInfoDesc);
        ExcelUtil<PmsSpuInfoDesc> util = new ExcelUtil<PmsSpuInfoDesc>(PmsSpuInfoDesc.class);
        util.exportExcel(response, list, "spu信息介绍数据");
    }

    /**
     * 获取spu信息介绍详细信息
     */
    @RequiresPermissions("pms:desc:query")
    @GetMapping(value = "/{spuId}")
    public AjaxResult getInfo(@PathVariable("spuId") Long spuId)
    {
        return success(pmsSpuInfoDescService.selectPmsSpuInfoDescBySpuId(spuId));
    }

    /**
     * 新增spu信息介绍
     */
    @RequiresPermissions("pms:desc:add")
    @Log(title = "spu信息介绍", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PmsSpuInfoDesc pmsSpuInfoDesc)
    {
        return toAjax(pmsSpuInfoDescService.insertPmsSpuInfoDesc(pmsSpuInfoDesc));
    }

    /**
     * 修改spu信息介绍
     */
    @RequiresPermissions("pms:desc:edit")
    @Log(title = "spu信息介绍", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PmsSpuInfoDesc pmsSpuInfoDesc)
    {
        return toAjax(pmsSpuInfoDescService.updatePmsSpuInfoDesc(pmsSpuInfoDesc));
    }

    /**
     * 删除spu信息介绍
     */
    @RequiresPermissions("pms:desc:remove")
    @Log(title = "spu信息介绍", businessType = BusinessType.DELETE)
	@DeleteMapping("/{spuIds}")
    public AjaxResult remove(@PathVariable Long[] spuIds)
    {
        return toAjax(pmsSpuInfoDescService.deletePmsSpuInfoDescBySpuIds(spuIds));
    }
}
