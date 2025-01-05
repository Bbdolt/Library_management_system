package top.cengweiye.library.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.cengweiye.library.interceptor.VisitInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Bean
    public VisitInterceptor visitInterceptor() {
        return new VisitInterceptor();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(visitInterceptor())
                .addPathPatterns("/**") // 拦截页面
                .excludePathPatterns(
                        "/reader/login",
                        "/reader/register",
                        "/login.html",
                        "/register.html",
                        "/**/*.js",
                        "/**/*.css",
                        "/**/*.png",
                        "/**/*.jpg",
                        "/**/*.jpeg"
                ); // 排除登录和注册接口
    }
}
