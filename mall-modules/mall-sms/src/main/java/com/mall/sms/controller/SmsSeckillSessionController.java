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
import com.mall.sms.domain.SmsSeckillSession;
import com.mall.sms.service.ISmsSeckillSessionService;
import com.mall.common.core.web.controller.BaseController;
import com.mall.common.core.web.domain.AjaxResult;
import com.mall.common.core.utils.poi.ExcelUtil;
import com.mall.common.core.web.page.TableDataInfo;

/**
 * 秒杀活动场次Controller
 * 
 * @author mall
 * @date 2026-04-11
 */
@RestController
@RequestMapping("/session")
public class SmsSeckillSessionController extends BaseController
{
    @Autowired
    private ISmsSeckillSessionService smsSeckillSessionService;

    /**
     * 查询秒杀活动场次列表
     */
    @RequiresPermissions("sms:session:list")
    @GetMapping("/list")
    public TableDataInfo list(SmsSeckillSession smsSeckillSession)
    {
        startPage();
        List<SmsSeckillSession> list = smsSeckillSessionService.selectSmsSeckillSessionList(smsSeckillSession);
        return getDataTable(list);
    }

    /**
     * 导出秒杀活动场次列表
     */
    @RequiresPermissions("sms:session:export")
    @Log(title = "秒杀活动场次", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SmsSeckillSession smsSeckillSession)
    {
        List<SmsSeckillSession> list = smsSeckillSessionService.selectSmsSeckillSessionList(smsSeckillSession);
        ExcelUtil<SmsSeckillSession> util = new ExcelUtil<SmsSeckillSession>(SmsSeckillSession.class);
        util.exportExcel(response, list, "秒杀活动场次数据");
    }

    /**
     * 获取秒杀活动场次详细信息
     */
    @RequiresPermissions("sms:session:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(smsSeckillSessionService.selectSmsSeckillSessionById(id));
    }

    /**
     * 新增秒杀活动场次
     */
    @RequiresPermissions("sms:session:add")
    @Log(title = "秒杀活动场次", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SmsSeckillSession smsSeckillSession)
    {
        return toAjax(smsSeckillSessionService.insertSmsSeckillSession(smsSeckillSession));
    }

    /**
     * 修改秒杀活动场次
     */
    @RequiresPermissions("sms:session:edit")
    @Log(title = "秒杀活动场次", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SmsSeckillSession smsSeckillSession)
    {
        return toAjax(smsSeckillSessionService.updateSmsSeckillSession(smsSeckillSession));
    }

    /**
     * 删除秒杀活动场次
     */
    @RequiresPermissions("sms:session:remove")
    @Log(title = "秒杀活动场次", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(smsSeckillSessionService.deleteSmsSeckillSessionByIds(ids));
    }
}
