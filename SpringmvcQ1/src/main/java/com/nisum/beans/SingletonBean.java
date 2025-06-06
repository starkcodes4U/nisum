package com.nisum.beans;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")  // or omit since singleton is default
public class SingletonBean {
    public String getScope() {
        return "Singleton";
    }
}
