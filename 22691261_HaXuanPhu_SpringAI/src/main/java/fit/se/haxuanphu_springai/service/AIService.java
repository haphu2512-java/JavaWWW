package fit.se.haxuanphu_springai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AIService {

    private final ChatClient chatClient;

    @Autowired
    public AIService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public String generateProductDescription(String productName, String category, Double price) {
        String template = """
            Tạo mô tả sản phẩm hấp dẫn cho:
            - Tên sản phẩm: {productName}
            - Danh mục: {category}
            - Giá: {price} VNĐ
            
            Mô tả nên ngắn gọn, hấp dẫn và chuyên nghiệp (khoảng 2-3 câu).
            """;

        PromptTemplate promptTemplate = new PromptTemplate(template);
        Prompt prompt = promptTemplate.create(Map.of(
                "productName", productName,
                "category", category != null ? category : "Chưa phân loại",
                "price", String.format("%,.0f", price)
        ));

        return chatClient.prompt(prompt).call().content();
    }

    public String recommendProducts(String customerName, String purchaseHistory) {
        String template = """
            Dựa trên lịch sử mua hàng của khách hàng {customerName}:
            {purchaseHistory}
            
            Hãy gợi ý 3-5 sản phẩm phù hợp mà họ có thể quan tâm.
            Trả lời bằng tiếng Việt, ngắn gọn và cụ thể.
            """;

        PromptTemplate promptTemplate = new PromptTemplate(template);
        Prompt prompt = promptTemplate.create(Map.of(
                "customerName", customerName,
                "purchaseHistory", purchaseHistory
        ));

        return chatClient.prompt(prompt).call().content();
    }

    public String analyzeCustomerFeedback(String comments) {
        String template = """
            Phân tích các nhận xét sau của khách hàng:
            {comments}
            
            Hãy tóm tắt:
            1. Các ý kiến tích cực
            2. Các vấn đề cần cải thiện
            3. Đánh giá tổng quan
            
            Trả lời ngắn gọn bằng tiếng Việt.
            """;

        PromptTemplate promptTemplate = new PromptTemplate(template);
        Prompt prompt = promptTemplate.create(Map.of("comments", comments));

        return chatClient.prompt(prompt).call().content();
    }

    /**
     * Generate marketing content
     */
    public String generateMarketingContent(String productName, String targetAudience) {
        String template = """
            Tạo nội dung quảng cáo cho sản phẩm "{productName}"
            Đối tượng khách hàng: {targetAudience}
            
            Nội dung cần:
            - Tiêu đề hấp dẫn
            - Mô tả ngắn gọn (2-3 câu)
            - Call-to-action mạnh mẽ
            
            Viết bằng tiếng Việt, phong cách chuyên nghiệp nhưng thân thiện.
            """;

        PromptTemplate promptTemplate = new PromptTemplate(template);
        Prompt prompt = promptTemplate.create(Map.of(
                "productName", productName,
                "targetAudience", targetAudience
        ));

        return chatClient.prompt(prompt).call().content();
    }


    public String chat(String userMessage) {
        return chatClient.prompt()
                .user(userMessage)
                .call()
                .content();
    }

    public String generateSalesReportSummary(String salesData) {
        String template = """
            Dựa trên dữ liệu bán hàng sau:
            {salesData}
            
            Hãy tạo báo cáo tóm tắt bao gồm:
            1. Xu hướng bán hàng
            2. Sản phẩm bán chạy nhất
            3. Khuyến nghị kinh doanh
            
            Trả lời bằng tiếng Việt, ngắn gọn và có số liệu.
            """;

        PromptTemplate promptTemplate = new PromptTemplate(template);
        Prompt prompt = promptTemplate.create(Map.of("salesData", salesData));

        return chatClient.prompt(prompt).call().content();
    }
}