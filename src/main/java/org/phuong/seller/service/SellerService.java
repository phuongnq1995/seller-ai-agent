package org.phuong.seller.service;

import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY;
import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_RETRIEVE_SIZE_KEY;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.PromptChatMemoryAdvisor;
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

        PromptChatMemoryAdvisor promptChatMemoryAdvisor = new PromptChatMemoryAdvisor(chatMemory);
        QuestionAnswerAdvisor questionAnswerAdvisor = new QuestionAnswerAdvisor(vectorStore);

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
            .advisors(a -> a
                .param(CHAT_MEMORY_CONVERSATION_ID_KEY, chatId)
                .param(CHAT_MEMORY_RETRIEVE_SIZE_KEY, 100))
            .call()
            .content();

        System.out.println(responseText);

        return responseText;
    }
}