package org.phuong.seller.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SellerService {

    private final ChatClient chatClient;

    public SellerService(ChatClient.Builder modelBuilder, VectorStore vectorStore, SellerTool sellerTool,
        ChatMemory chatMemory, @Value("classpath:supports/system-prompt.txt") String systemPrompt) {

        MessageChatMemoryAdvisor promptChatMemoryAdvisor = MessageChatMemoryAdvisor.builder(chatMemory).build();
        QuestionAnswerAdvisor questionAnswerAdvisor = QuestionAnswerAdvisor.builder(vectorStore).build();

        this.chatClient = modelBuilder
            .defaultSystem(systemPrompt)
            .defaultAdvisors(promptChatMemoryAdvisor, questionAnswerAdvisor)
            .defaultTools(sellerTool)
            .build();
    }

    public String generateAnswer(String chatId, String question) {
        String responseText = chatClient
            .prompt()
            .user(question)
            .advisors(a -> a.param(ChatMemory.CONVERSATION_ID, chatId))
            .call()
            .content();

        System.out.println(responseText);

        return responseText;
    }
}