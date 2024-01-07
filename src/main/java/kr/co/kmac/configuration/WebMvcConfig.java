package kr.co.kmac.configuration;

import kr.co.kmac.common.interceptor.AuthorizationInterceptor;
import kr.co.kmac.common.interceptor.CommonInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * 
 * @ClassName  WebMvcConfig.java
 * @Description Web mvc 설정 클래스
 * @author mjkim
 * @since 2023. 9. 18.
 *
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private CommonInterceptor commonInterceptor;

    @Autowired
    private AuthorizationInterceptor authorizationInterceptor;
    
    /**
     * 인터셉터 추가
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 기본 공통 interceptor : 모든 url 대상
        /*registry.addInterceptor(commonInterceptor)
                .addPathPatterns("/**")
        ;*/

        // 권한 관련 interceptor
        registry.addInterceptor(authorizationInterceptor)
                //.addPathPatterns("/")
                .addPathPatterns("/**/system/**")
                .addPathPatterns("/**/bbs/**")
                .addPathPatterns("/**/voc/**")
                .addPathPatterns("/**/statistics/**")
                
                .excludePathPatterns("/**/common/**")
                .excludePathPatterns("/kmacvoc/**/")
                .excludePathPatterns("/")
                .excludePathPatterns("/logout")
                .excludePathPatterns("/**/*.js")
                ;

    }
}
