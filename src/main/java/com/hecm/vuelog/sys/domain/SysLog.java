package com.hecm.vuelog.sys.domain;

import java.io.Serializable;
import java.sql.Timestamp;

public class SysLog implements Serializable {
    private static final long serialVersionUID=1L;
    /** 日志主键 */
    private String logId;
    /** 操作模块 */
    private String title;
    /** 业务类型（0其他 1新增 2修改 3删除）*/
    private String businessType;
    /** 浏览器 */
    private String browser;
    /** 操作号 */
    private String operId;
    /**  操作人 */
    private String opername;
    /** 请求url */
    private String operUrl;
    /** 操作地址 */
    private String operIp;
    /** 操作地点 */
    private String operlocation;
    /** 请求方法 */
    private String  method;
    /** 请求参数 */
    private String  operParam;
    /** 操作状态 （0正常 1异常）*/
    private String  status;
    /** 错误信息 */
    private String  errorMsg;
    /** 操作时间 */
    private Timestamp operTime;
    /** 执行时间 */
    private String  execTime;
    /** 创建时间  */
    private Timestamp createTime;


    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getOperId() {
        return operId;
    }

    public void setOperId(String operId) {
        this.operId = operId;
    }

    public String getOpername() {
        return opername;
    }

    public void setOpername(String opername) {
        this.opername = opername;
    }

    public String getOperUrl() {
        return operUrl;
    }

    public void setOperUrl(String operUrl) {
        this.operUrl = operUrl;
    }

    public String getOperIp() {
        return operIp;
    }

    public void setOperIp(String operIp) {
        this.operIp = operIp;
    }

    public String getOperlocation() {
        return operlocation;
    }

    public void setOperlocation(String operlocation) {
        this.operlocation = operlocation;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getOperParam() {
        return operParam;
    }

    public void setOperParam(String operParam) {
        this.operParam = operParam;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Timestamp getOperTime() {
        return operTime;
    }

    public void setOperTime(Timestamp operTime) {
        this.operTime = operTime;
    }


    public String getExecTime() {
        return execTime;
    }

    public void setExecTime(String execTime) {
        this.execTime = execTime;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
