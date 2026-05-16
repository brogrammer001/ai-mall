package com.mall.chatmcp.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SaaLlmConfig {

    @Bean(name = "qwenChatClient")
    public ChatClient qwenChatClient(ChatClient.Builder chatClientBuilder, @Qualifier("mcpAsyncToolCallbacks") ToolCallbackProvider tools) {
        return chatClientBuilder
            .defaultToolCallbacks(tools.getToolCallbacks())
            .build();
    }
}
