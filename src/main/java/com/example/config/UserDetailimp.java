package com.example.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.core.authority.AuthorityUtils;

import com.example.domain.Administrator;

public class UserDetailimp extends User{
    
    private Administrator administrator;

    public UserDetailimp(Administrator administrator){
        this.setName(administrator.getMailAddress());
        this.setPassword(administrator.getPassword());
        this.setRoles(new ArrayList<String>(Arrays.asList("USER")));
        this.administrator = administrator;
    }

    public Administrator getAdministrator(){
        return administrator;
    }
}
