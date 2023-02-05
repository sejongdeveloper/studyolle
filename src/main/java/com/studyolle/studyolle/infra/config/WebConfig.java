package com.studyolle.studyolle.infra.config;

import com.studyolle.studyolle.modules.notification.NotificationInterceptor;
import com.studyolle.studyolle.modules.notification.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.StaticResourceLocation;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final NotificationRepository notificationRepository;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> staticResourcePath = Arrays.stream(StaticResourceLocation.values())
                .flatMap(StaticResourceLocation::getPatterns)
                .collect(Collectors.toList());
        staticResourcePath.add("/node_modules/**");

        registry.addInterceptor(new NotificationInterceptor(notificationRepository))
                .excludePathPatterns(staticResourcePath);
    }
}
