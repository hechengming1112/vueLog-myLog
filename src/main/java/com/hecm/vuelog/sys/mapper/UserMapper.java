package com.hecm.vuelog.sys.mapper;

import com.hecm.vuelog.sys.domain.Role;
import com.hecm.vuelog.sys.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
   public User loadUserByUsername(@Param("username") String username);
   List<User> getUserByNickname(@Param("nickname") String nickname);
   int deleteUserById(Long uid);
   User getUserById(@Param("id") Long id);
   int updateUserEnabled(@Param("enabled") Boolean enabled, @Param("uid") Long uid);
   List<Role> getAllRole();
   int deleteUserRolesByUid(Long id);

   int setUserRoles(@Param("rids") Long[] rids, @Param("id") Long id);
   int updateUserEmail(@Param("email") String email, @Param("id") Long id);
}
