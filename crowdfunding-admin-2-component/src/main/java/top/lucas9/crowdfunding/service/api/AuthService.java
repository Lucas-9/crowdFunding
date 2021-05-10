package top.lucas9.crowdfunding.service.api;

import top.lucas9.crowdfunding.entity.Auth;

import java.util.List;
import java.util.Map;

public interface AuthService {
    void updateRelationShipBetweenRoleAndAuth(Map<String,List<Integer>> assignDataMap);

    List<Integer> getAssignedAuthIdList(Integer roleId);

    List<Auth> getAllAuth();
}
