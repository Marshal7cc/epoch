package com.marshal.epoch.auth.service.impl;


import com.marshal.epoch.auth.component.EpochUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.*;

/**
 * @auth: Marshal
 * @date: 2018/11/12
 * @desc: Spring Security UserService
 */
@Component
public class EpochUserDetailService implements UserDetailsService , Serializable {

    private static final String VALID_SQL = "select * from sys_user where user_name = :username";

    @Autowired
    private transient NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Map<String, String> param = new HashMap<>();
        param.put("username", username);

        List<Map<String, Object>> userInfoList = jdbcTemplate.queryForList(VALID_SQL, param);
        if (userInfoList.size() == 0) {
            throw new UsernameNotFoundException("user not found:" + username);
        }

        Map<String, Object> userInfo = userInfoList.get(0);

        //授权列表
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        //todo:角色授权
//        for(String role:user.getRoleCode()){
//            authorities.add(new SimpleGrantedAuthority(role));
//        }

        UserDetails userDetails = new EpochUserDetails((Long)userInfo.get("user_id"), (String)userInfo.get("user_name"),
                (String)userInfo.get("password"), true, true, true, true, authorities) {
        };
        return userDetails;
    }
}
