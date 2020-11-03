package com.jdch.blog3.config;

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
        registry.addViewController("index").setViewName("index");
        registry.addViewController("login").setViewName("login");
        registry.addViewController("user/index").setViewName("user/index");
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
//        LoginHandleInterceptor loginHandleInterceptor = new LoginHandleInterceptor();
//        registry.addInterceptor(loginHandleInterceptor)
//                .addPathPatterns("/userinfo","/updatepwd").excludePathPatterns("/layui/**");
        //添加订单拦截器
//        OrderHandleIntegerceptor orderHandleIntegerceptor = new OrderHandleIntegerceptor();
//        registry.addInterceptor(orderHandleIntegerceptor).addPathPatterns("/car_checkout","/car_accessories");
        //后台登录拦截
//        BackstageLogin2HandleIntegerceptor backstageLogin2HandleIntegerceptor = new BackstageLogin2HandleIntegerceptor();
//        registry.addInterceptor(backstageLogin2HandleIntegerceptor).addPathPatterns("/index2");

    }
}
