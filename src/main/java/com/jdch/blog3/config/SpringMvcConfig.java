package com.jdch.blog3.config;

import com.jdch.blog3.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * description:
 *
 * @author Zoisitesky
 * @date 2020-11-01 19:28
 */
@Configuration
@EnableWebMvc
public class SpringMvcConfig implements WebMvcConfigurer {

    /*
     * @Description: 设置templates页面访问路径
     * @Param: [registry]
     * @return: void
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("index.html").setViewName("index");
        registry.addViewController("login.html").setViewName("login");
        registry.addViewController("user/index.html").setViewName("user/index");
        registry.addViewController("user/settings.html").setViewName("user/settings");
        registry.addViewController("user/password.html").setViewName("user/password");
    }

    /*
     * @Description: 设置静态资源访问路径
     * @Param: [registry]
     * @return: void
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/");
    }

    /*
     * @Description: 设置拦截器
     * @Param: [registry]
     * @return: void
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加登录拦截器
        LoginInterceptor loginInterceptor = new LoginInterceptor();
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/user/**").excludePathPatterns("/layui/**");
        //添加订单拦截器
//        OrderHandleIntegerceptor orderHandleIntegerceptor = new OrderHandleIntegerceptor();
//        registry.addInterceptor(orderHandleIntegerceptor).addPathPatterns("/car_checkout","/car_accessories");
        //后台登录拦截
//        BackstageLogin2HandleIntegerceptor backstageLogin2HandleIntegerceptor = new BackstageLogin2HandleIntegerceptor();
//        registry.addInterceptor(backstageLogin2HandleIntegerceptor).addPathPatterns("/index2");

    }
}
