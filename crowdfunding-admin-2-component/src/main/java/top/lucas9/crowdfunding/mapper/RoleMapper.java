package top.lucas9.crowdfunding.mapper;

import org.apache.ibatis.annotations.Param;
import top.lucas9.crowdfunding.entity.Role;
import top.lucas9.crowdfunding.entity.RoleExample;

import java.util.List;

public interface RoleMapper {
    int countByExample(RoleExample example);

    int deleteByExample(RoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    List<Role> selectByExample(RoleExample example);

    List<Role> queryRoleByKeyword(@Param("keyword") String keyword);

    Role selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> getAssignedRoleList(Integer adminId);

    List<Role> getUnAssignedRoleList(Integer adminId);

    void deleteOldAdminRelationship(Integer adminId);

    void insertNewAdminRelationship(@Param("adminId") Integer adminId, @Param("roleIdList") List<Integer> roleIdList);

}