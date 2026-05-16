package com.mall.chatmcp.controller;

import com.mall.chatmcp.config.RagConfig;
import jakarta.annotation.Resource;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {

    @Resource(name = "qwenChatClient")
    private ChatClient qwenChatClient;

    @Resource
    private RagConfig ragConfig;

    @Value("${ai.system.prompt}")
    private String aiSystemPrompt;

    @Value("${ai.rag.top-k:3}")
    private int topK;

    @PostMapping(value = "/send", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> send(@RequestParam String question) {
        // 1. 从向量库检索相关知识片段
        List<String> relevantDocs = ragConfig.retrieveContext(question, topK);

        // 2. 构建 SystemMessage，将检索到的知识作为上下文
        String systemPrompt;
        if (relevantDocs.isEmpty()) {
            systemPrompt = aiSystemPrompt;
        } else {
            systemPrompt = aiSystemPrompt + "\n\n【参考知识】\n" + String.join("\n---\n", relevantDocs);
        }

        SystemMessage systemMessage = new SystemMessage(systemPrompt);
        UserMessage userMessage = new UserMessage(question);

        Prompt prompt = new Prompt(List.of(systemMessage, userMessage));

        return qwenChatClient.prompt(prompt)
            .stream()
            .content();
    }
}
