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
import com.mall.ums.domain.UmsMemberCollectSpu;
import com.mall.ums.service.IUmsMemberCollectSpuService;
import com.mall.common.core.web.controller.BaseController;
import com.mall.common.core.web.domain.AjaxResult;
import com.mall.common.core.utils.poi.ExcelUtil;
import com.mall.common.core.web.page.TableDataInfo;

/**
 * 会员收藏的商品Controller
 * 
 * @author mall
 * @date 2026-04-11
 */
@RestController
@RequestMapping("/spu")
public class UmsMemberCollectSpuController extends BaseController
{
    @Autowired
    private IUmsMemberCollectSpuService umsMemberCollectSpuService;

    /**
     * 查询会员收藏的商品列表
     */
    @RequiresPermissions("ums:spu:list")
    @GetMapping("/list")
    public TableDataInfo list(UmsMemberCollectSpu umsMemberCollectSpu)
    {
        startPage();
        List<UmsMemberCollectSpu> list = umsMemberCollectSpuService.selectUmsMemberCollectSpuList(umsMemberCollectSpu);
        return getDataTable(list);
    }

    /**
     * 导出会员收藏的商品列表
     */
    @RequiresPermissions("ums:spu:export")
    @Log(title = "会员收藏的商品", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, UmsMemberCollectSpu umsMemberCollectSpu)
    {
        List<UmsMemberCollectSpu> list = umsMemberCollectSpuService.selectUmsMemberCollectSpuList(umsMemberCollectSpu);
        ExcelUtil<UmsMemberCollectSpu> util = new ExcelUtil<UmsMemberCollectSpu>(UmsMemberCollectSpu.class);
        util.exportExcel(response, list, "会员收藏的商品数据");
    }

    /**
     * 获取会员收藏的商品详细信息
     */
    @RequiresPermissions("ums:spu:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(umsMemberCollectSpuService.selectUmsMemberCollectSpuById(id));
    }

    /**
     * 新增会员收藏的商品
     */
    @RequiresPermissions("ums:spu:add")
    @Log(title = "会员收藏的商品", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UmsMemberCollectSpu umsMemberCollectSpu)
    {
        return toAjax(umsMemberCollectSpuService.insertUmsMemberCollectSpu(umsMemberCollectSpu));
    }

    /**
     * 修改会员收藏的商品
     */
    @RequiresPermissions("ums:spu:edit")
    @Log(title = "会员收藏的商品", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UmsMemberCollectSpu umsMemberCollectSpu)
    {
        return toAjax(umsMemberCollectSpuService.updateUmsMemberCollectSpu(umsMemberCollectSpu));
    }

    /**
     * 删除会员收藏的商品
     */
    @RequiresPermissions("ums:spu:remove")
    @Log(title = "会员收藏的商品", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(umsMemberCollectSpuService.deleteUmsMemberCollectSpuByIds(ids));
    }
}
