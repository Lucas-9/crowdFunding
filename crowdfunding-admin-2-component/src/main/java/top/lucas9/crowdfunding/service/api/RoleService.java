package top.lucas9.crowdfunding.service.api;

import com.github.pagehelper.PageInfo;
import top.lucas9.crowdfunding.entity.Role;

import java.util.List;

public interface RoleService {
    PageInfo<Role> queryRoleByKeyword(Integer pageNum, Integer pageSize, String keyword);

    List<Role> getRoleListByRoleId(List<Integer> roleIdList);

    void batchRemoveRole(List<Integer> roleIdList);
    void addRole(String roleName);

    void updateRole(Role role);

    List<Role> getAssignedRoleList(Integer adminId);

    List<Role> getUnAssignedRoleList(Integer adminId);

    void updateRelationship(Integer adminId, List<Integer> roleIdList);
}
