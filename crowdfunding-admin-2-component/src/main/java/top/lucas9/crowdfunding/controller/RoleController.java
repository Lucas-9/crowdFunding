package top.lucas9.crowdfunding.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.lucas9.crowdfunding.constant.Constant;
import top.lucas9.crowdfunding.entity.ResultEntity;
import top.lucas9.crowdfunding.entity.Role;
import top.lucas9.crowdfunding.service.api.RoleService;

import java.util.List;

@RestController
public class RoleController {
    private RoleService service;

    @Autowired
    RoleController(RoleService service) {
        this.service = service;
    }

    // 限制角色 @PreAuthorize("hasRole('董事长')")
    // 限制权限 @PreAuthorize("hasAnyAuthority('role:get')")
    @RequestMapping("/role/queryRole/byKeyword")
    public ResultEntity<PageInfo<Role>> queryRoleByKeyword(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "keyword", defaultValue = "") String keyword) {
        PageInfo<Role> rolePageInfo = service.queryRoleByKeyword(pageNum, pageSize, keyword);
        return ResultEntity.successWithData(rolePageInfo);
    }

    @RequestMapping("/role/getList/byRoleId")
    public ResultEntity<List<Role>> getRoleListByRoleId(@RequestBody List<Integer> roleIdList) {
        List<Role> roleList = service.getRoleListByRoleId(roleIdList);
        return ResultEntity.successWithData(roleList);
    }

    @RequestMapping("/role/batch/remove")
    public ResultEntity<String> batchRemoveRole(@RequestBody List<Integer> roleIdList) {
        service.batchRemoveRole(roleIdList);
        return ResultEntity.successWithoutData();
    }

    @RequestMapping("/role/add/role")
    public ResultEntity<String> addRole(@RequestParam("roleName") String roleName) {
        try {
            service.addRole(roleName);
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof DuplicateKeyException) {
                return ResultEntity.failed(null,Constant.MESSAGE_LOGIN_ACCT_ALREADY_IN_USE);
            }
        }
        return ResultEntity.successWithoutData();
    }
    @RequestMapping("/role/update/role")
    public ResultEntity<String> updateRole(Role role) {
        service.updateRole(role);
        return ResultEntity.successWithoutData();
    }
}
