package top.lucas9.crowdfunding.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.lucas9.crowdfunding.entity.Auth;
import top.lucas9.crowdfunding.entity.AuthExample;
import top.lucas9.crowdfunding.mapper.AuthMapper;
import top.lucas9.crowdfunding.service.api.AuthService;
import top.lucas9.crowdfunding.utils.CheckEffective;

import java.util.List;
import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {
    private AuthMapper authMapper;
    @Autowired
    public AuthServiceImpl(AuthMapper authMapper) {
        this.authMapper = authMapper;
    }

    @Override
    public List<Auth> getAllAuth() {
        return authMapper.selectByExample(new AuthExample());
    }
    @Override
    public List<Integer> getAssignedAuthIdList(Integer roleId) {
        return authMapper.selectAssignedAuthIdList(roleId);
    }

    // Integer roleId, List<Integer> authIdList
    @Override
    public void updateRelationShipBetweenRoleAndAuth(Map<String, List<Integer>> assignDataMap) {
        // 1.获取两部分List数据
        List<Integer> roleIdList = assignDataMap.get("roleIdList");
        List<Integer> authIdList = assignDataMap.get("authIdList");
        // 2.取出roleId
        Integer roleId = roleIdList.get(0);
        // 3.删除旧数据
        authMapper.deleteOldRelationship(roleId);
        // 4.保存新数据
        if(CheckEffective.collectionEffective(authIdList)) {
            authMapper.insertNewRelationship(roleId, authIdList);
        }
    }
}
