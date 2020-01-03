package com.hecm.vuelog.sys.service;

import com.hecm.vuelog.sys.domain.SysLog;
import com.hecm.vuelog.sys.mapper.ISysLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ISysLogService {
    @Autowired
    private ISysLogMapper iSysLogMapper;
    public void insertLog(SysLog sysLog){
        iSysLogMapper.insertLog(sysLog);
    }
}
