package com.hecm.vuelog.common.annotation;

import com.hecm.vuelog.sys.service.IConstant;

import java.lang.annotation.*;

/**
 * @author 和诚明
 * @date 2020/01/02
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {
    /** 模块*/
     String title() default "";
     String businessType() default IConstant.BUSINESS_TYPE_OTHER;
     String symbol() default "none";
     String remark() default "default";
     String service() default "sysLogServiceImpl";

}
