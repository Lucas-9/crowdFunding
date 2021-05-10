package top.lucas9.crowdfunding.service.api;

import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import top.lucas9.crowdfunding.entity.Admin;

import java.util.List;
public interface AdminService {
    Admin getAdminByPrimaryKey(Integer id);

    Admin login(String loginAccount, String userPassword);

    PageInfo<Admin> queryAdminByKeyWord(Integer pageNum, Integer pageSize,String keyword);

    void batchRemoveAdmin(List<Integer> adminIdList);

    void addUser(Admin admin);

    Admin getUserById(Integer adminId);

    void updateUser(Admin admin);
}
