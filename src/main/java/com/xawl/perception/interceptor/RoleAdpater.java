package com.xawl.perception.interceptor;

import com.xawl.perception.service.ClientAcodeService;
import com.xawl.perception.service.MobileAcodeService;
import com.xawl.perception.service.MobileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootConfiguration
public class RoleAdpater implements WebMvcConfigurer {
    @Autowired
    private ClientAcodeService clientAcodeService;
    @Autowired
    private MobileAcodeService mobileAcodeService;
    @Autowired
    private MobileService mobileService;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(
                new ClientRoleInterceptor(clientAcodeService))
                .addPathPatterns("/cli/out").addPathPatterns("/cli/putImg");
        registry.addInterceptor(
                new MobileRoleInterceptor(mobileAcodeService, mobileService))
                .addPathPatterns("/mobile/haveImg").addPathPatterns("/mobile/getImg");
    }
}
