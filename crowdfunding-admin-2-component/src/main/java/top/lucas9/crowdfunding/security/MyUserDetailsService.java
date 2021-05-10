package top.lucas9.crowdfunding.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import top.lucas9.crowdfunding.entity.Admin;
import top.lucas9.crowdfunding.entity.AdminExample;
import top.lucas9.crowdfunding.entity.Auth;
import top.lucas9.crowdfunding.entity.Role;
import top.lucas9.crowdfunding.mapper.AdminMapper;
import top.lucas9.crowdfunding.mapper.AuthMapper;
import top.lucas9.crowdfunding.mapper.RoleMapper;
import top.lucas9.crowdfunding.utils.CheckEffective;

import java.util.ArrayList;
import java.util.List;
@Service
public class MyUserDetailsService implements UserDetailsService {
    private AdminMapper adminMapper;
    private RoleMapper roleMapper;
    private AuthMapper authMapper;
    @Autowired
    public MyUserDetailsService(AdminMapper adminMapper, RoleMapper roleMapper, AuthMapper authMapper) {
        this.adminMapper = adminMapper;
        this.roleMapper = roleMapper;
        this.authMapper = authMapper;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AdminExample adminExample = new AdminExample();
        adminExample.createCriteria().andLoginAccountEqualTo(username);
        List<Admin> adminList = adminMapper.selectByExample(adminExample);
        if (!CheckEffective.collectionEffective(adminList)) {
            return null;
        }
        Admin admin = adminList.get(0);
        Integer adminId = admin.getId();
        List<Role> roleList = roleMapper.getAssignedRoleList(adminId);
        List<Auth> authList = authMapper.selectAuthListByAdminId(adminId);
        ArrayList<GrantedAuthority> grantedAuthorityArrayList = new ArrayList<>();
        for (Role role : roleList) {
            String roleName = "ROLE_" + role.getName();
            grantedAuthorityArrayList.add(new SimpleGrantedAuthority(roleName));
        }
        for (Auth auth : authList) {
            String authName = auth.getName();
            if (!CheckEffective.stringEffective(authName)) {
                continue;
            }
            grantedAuthorityArrayList.add(new SimpleGrantedAuthority(authName));
        }
        return new MySecurityAdmin(admin, grantedAuthorityArrayList);
    }
}
