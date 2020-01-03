package com.hecm.vuelog.common.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
@Aspect
@Component
public class LogAspect {
    public  static final Logger logger= LoggerFactory.getLogger(LogAspect.class);

}
