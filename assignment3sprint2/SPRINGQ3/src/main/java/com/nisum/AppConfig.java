package com.nisum;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = "com.nisum")
@EnableAspectJAutoProxy
public class AppConfig {
    // No manual bean declarations needed if using @Component and @Aspect annotations
}
