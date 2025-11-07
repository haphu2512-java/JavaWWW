package fit.se.haxuanphu_springai.controller;

import fit.se.haxuanphu_springai.entity.Product;
import fit.se.haxuanphu_springai.entity.Order;
import fit.se.haxuanphu_springai.service.AIService;
import fit.se.haxuanphu_springai.service.ProductService;
import fit.se.haxuanphu_springai.service.OrderService;
import fit.se.haxuanphu_springai.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/ai")
public class AIController {

    @Autowired
    private AIService aiService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderService orderService;

    /**
     * AI Dashboard
     */
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    public String aiDashboard(Model model) {
        model.addAttribute("pageTitle", "AI Assistant");
        return "ai/dashboard";
    }

    /**
     * Generate product description
     */
    @PostMapping("/product/description")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseBody
    public String generateProductDescription(@RequestParam Integer productId) {
        Product product = productService.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        String categoryName = product.getCategory() != null ?
                product.getCategory().getName() : null;

        return aiService.generateProductDescription(
                product.getName(),
                categoryName,
                product.getPrice().doubleValue()
        );
    }

    /**
     * Get product recommendations for customer
     */
    @GetMapping("/customer/{customerId}/recommendations")
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    public String getRecommendations(@PathVariable Integer customerId, Model model) {
        var customer = customerService.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        List<Order> orders = orderService.findByCustomer(customer);

        String purchaseHistory = orders.stream()
                .flatMap(order -> order.getOrderLines().stream())
                .map(line -> line.getProduct().getName())
                .distinct()
                .collect(Collectors.joining(", "));

        String recommendations = aiService.recommendProducts(
                customer.getName(),
                purchaseHistory.isEmpty() ? "Chưa có lịch sử mua hàng" : purchaseHistory
        );

        model.addAttribute("customer", customer);
        model.addAttribute("recommendations", recommendations);
        return "ai/recommendations";
    }

    /**
     * AI Chat Interface
     */
    @GetMapping("/chat")
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    public String chatInterface(Model model) {
        return "ai/chat";
    }

    /**
     * AI Chat API
     */
    @PostMapping("/chat")
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    @ResponseBody
    public String chat(@RequestParam String message) {
        return aiService.chat(message);
    }

    /**
     * Generate marketing content
     */
    @GetMapping("/marketing/{productId}")
    @PreAuthorize("hasRole('ADMIN')")
    public String generateMarketing(@PathVariable Integer productId, Model model) {
        Product product = productService.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        String marketingContent = aiService.generateMarketingContent(
                product.getName(),
                "Khách hàng trẻ tuổi, năng động"
        );

        model.addAttribute("product", product);
        model.addAttribute("marketingContent", marketingContent);
        return "ai/marketing";
    }

    /**
     * Analyze feedback
     */
    @PostMapping("/analyze/feedback")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseBody
    public String analyzeFeedback(@RequestParam Integer productId) {
        Product product = productService.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        String comments = product.getComments().stream()
                .map(comment -> comment.getText())
                .collect(Collectors.joining("\n"));

        if (comments.isEmpty()) {
            return "Chưa có nhận xét nào để phân tích.";
        }

        return aiService.analyzeCustomerFeedback(comments);
    }

    /**
     * Generate sales report
     */
    @GetMapping("/report/sales")
    @PreAuthorize("hasRole('ADMIN')")
    public String generateSalesReport(Model model) {
        List<Order> orders = orderService.findAll();

        String salesData = orders.stream()
                .flatMap(order -> order.getOrderLines().stream())
                .collect(Collectors.groupingBy(
                        line -> line.getProduct().getName(),
                        Collectors.summingInt(line -> line.getAmount())
                ))
                .entrySet().stream()
                .map(entry -> entry.getKey() + ": " + entry.getValue() + " sản phẩm")
                .collect(Collectors.joining("\n"));

        String reportSummary = aiService.generateSalesReportSummary(
                salesData.isEmpty() ? "Chưa có dữ liệu bán hàng" : salesData
        );

        model.addAttribute("reportSummary", reportSummary);
        return "ai/sales-report";
    }
}