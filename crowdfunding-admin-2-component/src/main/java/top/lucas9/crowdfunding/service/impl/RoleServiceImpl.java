package top.lucas9.crowdfunding.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.lucas9.crowdfunding.entity.Role;
import top.lucas9.crowdfunding.entity.RoleExample;
import top.lucas9.crowdfunding.mapper.RoleMapper;
import top.lucas9.crowdfunding.service.api.RoleService;
import top.lucas9.crowdfunding.utils.CheckEffective;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleMapper roleMapper;
    @Autowired
    RoleServiceImpl(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }
    @Override
    public PageInfo<Role> queryRoleByKeyword(Integer pageNum, Integer pageSize, String keyword) {
        PageHelper.startPage(pageNum, pageSize);
        List<Role> roles = roleMapper.queryRoleByKeyword(keyword);
        return new PageInfo<>(roles);
    }

    @Override
    public List<Role> getRoleListByRoleId(List<Integer> roleIdList) {
        RoleExample roleExample = new RoleExample();
        roleExample.createCriteria().andIdIn(roleIdList);
        return roleMapper.selectByExample(roleExample);
    }

    @Override
    public void batchRemoveRole(List<Integer> roleIdList) {
        RoleExample roleExample = new RoleExample();
        roleExample.createCriteria().andIdIn(roleIdList);
        roleMapper.deleteByExample(roleExample);
    }

    public void addRole(String roleName) {
        roleMapper.insert(new Role(null, roleName));
        return;
    }

    @Override
    public void updateRole(Role role) {
        roleMapper.updateByPrimaryKey(role);
        return;
    }

    @Override
    public List<Role> getAssignedRoleList(Integer adminId) {
        return roleMapper.getAssignedRoleList(adminId);
    }

    @Override
    public List<Role> getUnAssignedRoleList(Integer adminId) {
        return roleMapper.getUnAssignedRoleList(adminId);
    }

    @Override
    public void updateRelationship(Integer adminId, List<Integer> roleIdList) {
        // 1.删除全部旧数据
        roleMapper.deleteOldAdminRelationship(adminId);
        // 2.保存全部新数据
        if(CheckEffective.collectionEffective(roleIdList)) {
            roleMapper.insertNewAdminRelationship(adminId, roleIdList);
        }
    }
}
