package com.una;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import jakarta.validation.constraints.NotBlank;

@SpringBootApplication
public class UnaApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(UnaApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(UnaApplication.class, args);

        System.out.println("Perfect");
    }
}

// 這是 Spring Boot 最核心的註解，通常用於主類上，它是一個複合註解，包含了三個重要註解：

// @Configuration：標示此類為配置類

// @EnableAutoConfiguration：啟用自動化配置機制

// @ComponentScan：掃描當前包及其子包中的組件

// @Component：通用組件註解，將類註冊為 Spring 管理的 Bean。

// @Service： 標示業務邏輯層組件，是 @Component 的專用形式。

// @Repository：標示資料訪問層組件，除擁有 @Component 功能外，還能將資料庫異常轉換為 Spring 的資料存取例外。

// @Controller：標示 MVC 控制器類別。

// @RestController：結合了 @Controller 與 @ResponseBody。

// @GetMapping、@PostMapping、@PutMapping、@DeleteMapping

// @PathVariable：綁定 URL 路徑參數
// @RequestParam：綁定查詢參數

// @Valid 用於觸發資料驗證。當 Spring 偵測到時，會自動驗證物件是否符合約束條件。
// @NotNull、@Size、@Email 等註解用於定義驗證規則
// @NotBlank
// @ExceptionHandler：定義異常處理方法
// @ControllerAdvice：全局異常處理
// @RestControllerAdvice：結合 @ControllerAdvice 與 @ResponseBody，用於 RESTful
// 服務的全局異常處理

// @RequestMapping：通用請求映射
// @RequestParam：綁定請求參數到方法參數
// @RequestBody：將 HTTP 請求體綁定到方法參數（通常為 JSON 或 XML）。
// @ResponseBody：將方法返回值寫入 HTTP 回應體，而非作為視圖名稱。
// @Autowired：自動注入 Bean
// @Qualifier：與 @Autowired 一起使用，指定要注入的 Bean 名稱
// @Value：注入屬性值
// @Configuration：標示配置類
// @Bean：定義 Bean
// @ComponentScan：指定掃描包
// @EnableAutoConfiguration：啟用自動配置
// @RestControllerAdvice：全局異常處理
// @Transactional：定義事務範圍
// @EnableScheduling：啟用排程任務
// @Scheduled：定義排程任務
// @Cacheable、@CachePut、@CacheEvict：緩存管理
// @EnableCaching：啟用緩存
// @SessionAttributes：將模型屬性存儲在 HTTP Session 中
// @ModelAttribute：綁定模型屬性
// @CrossOrigin：處理跨域請求
// @Async：啟用異步方法
// @EnableAsync：啟用異步處理
