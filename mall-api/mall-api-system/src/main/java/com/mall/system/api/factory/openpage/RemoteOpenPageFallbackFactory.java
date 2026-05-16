package com.mall.system.api.factory.openpage;

import com.mall.common.core.domain.R;
import com.mall.system.api.feign.openpage.RemoteOpenPageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 文件服务降级处理
 * 
 * @author mall
 */
@Component
public class RemoteOpenPageFallbackFactory implements FallbackFactory<RemoteOpenPageService>
{
    private static final Logger log = LoggerFactory.getLogger(RemoteOpenPageFallbackFactory.class);

    @Override
    public RemoteOpenPageService create(Throwable throwable)
    {
        log.error("菜单服务调用失败:{}", throwable.getMessage());
        return menu -> R.fail("查询菜单失败:" + throwable.getMessage());
    }
}
