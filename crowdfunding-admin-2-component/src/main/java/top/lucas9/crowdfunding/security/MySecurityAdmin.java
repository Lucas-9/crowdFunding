package top.lucas9.crowdfunding.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import top.lucas9.crowdfunding.entity.Admin;

import java.util.Collection;

public class MySecurityAdmin extends User {
    private Admin originalAdmin;

    public MySecurityAdmin(Admin admin, Collection<? extends GrantedAuthority> authorities) {
        super(admin.getUserName(), admin.getUserPassword(), authorities);
        this.originalAdmin = admin;
    }

    public Admin getOriginalAdmin() {
        return this.originalAdmin;
    }
}
