package com.hecm.vuelog.common.aspect;

import com.hecm.vuelog.common.annotation.Log;
import com.hecm.vuelog.common.json.JSON;
import com.hecm.vuelog.common.util.ServletUtils;
import com.hecm.vuelog.common.util.Util;
import com.hecm.vuelog.sys.domain.SysLog;
import com.hecm.vuelog.sys.service.IConstant;
import com.hecm.vuelog.sys.service.ISysLogService;
import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.assertj.core.util.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;

@Aspect
@Component
public class LogAspect {
    public  static final Logger logger= LoggerFactory.getLogger(LogAspect.class);
    @Autowired
    private ISysLogService iSysLogService;

    @Pointcut("@annotation(com.hecm.vuelog.common.annotation.Log)")
    public void logAspect(){
    }

    @Around("logAspect()")
    public Object around(ProceedingJoinPoint point) throws Throwable{
        logger.info("日志记录开始");
        Object ret=null;
        SysLog sysLog=new SysLog();
        String status= IConstant.OPER_STATUS_SUCCESS;
        String errorMsg="";
        Exception ex=null;
        long startTime=System.currentTimeMillis();
        long endTime=System.currentTimeMillis();
        try {
            try {
                ret=point.proceed();
                endTime=System.currentTimeMillis();
            }catch (Exception e){
                endTime=System.currentTimeMillis();
                status=IConstant.OPER_STATUS_SUCCESS;
                //处理异常
                if (e instanceof Base64DecodingException){
                    errorMsg=e.getMessage();
                }else{
                    errorMsg="系统处理过程出错,请联系管理员";
                }
                ex=e;

            }
            Log controllerLog=getAnnotationLog(point);
            if (controllerLog == null){
                if (ex != null){
                    throw  ex;
                }
                return ret;
            }
            sysLog.setStatus(status);
            sysLog.setOperId(Util.getCurrentUser().getId().toString());
            sysLog.setOpername(Util.getCurrentUser().getUsername());
            sysLog.setTitle(controllerLog.title());
            sysLog.setBusinessType(controllerLog.businessType());
            sysLog.setErrorMsg(errorMsg);
            sysLog.setOperUrl(ServletUtils.getRequest().getRequestURI());
            //设置方法名称
            String className = point.getTarget().getClass().getName();
            String methodName = point.getSignature().getName();
            sysLog.setMethod(className + "." + methodName + "()");
            Object[] args = point.getArgs();
            ArrayList<Object> argList = Lists.newArrayList();
            if (args != null && args.length>0){
                for (int i=0; i<args.length;i++){
                    argList.add(args[i]);
                }
            }
            String params = JSON.marshal(argList);
            sysLog.setOperParam(params);
            sysLog.setExecTime((endTime-startTime) + "");
            iSysLogService.insertLog(sysLog);
            if (ex != null){
                throw ex;
            }
            logger.info("日志记录结束");
            return ret;
        }catch (Exception e){
            if (e != null){
                //处理过程出错才抛出，日志记录过程出错不影响
                throw e;
            }
        }
        return ret;
    }

    private Log  getAnnotationLog(JoinPoint joinPoint) throws Exception{
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature=(MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if (method != null){
            return method.getAnnotation(Log.class);
        }
        return null;
    }

}
