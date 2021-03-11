package com.example.mysecurity.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        String sql = "select * from admin where name='"+s+"';";
        System.out.println(s);
        List<Map<String, Object>> resultset =  jdbcTemplate.queryForList(sql);
        System.out.println("zheshi userdetail");
        if(resultset.size()==0){
            System.out.println("出错了");
            throw new UsernameNotFoundException("User '"+s+"' not found");}
        else{
            HashSet<GrantedAuthority> authorities = new HashSet<>();
            authorities.add(new SimpleGrantedAuthority("common"));
            System.out.println(s);
            return new User(s, (String) resultset.get(0).get("password"),authorities);
        }

    }
}
