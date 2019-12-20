package com.hecm.vuelog.sys.mapper;

import com.hecm.vuelog.sys.domain.Role;

import java.util.List;

public interface RolesMapper {
    List<Role> getRolesByUid(Long uid);
}
