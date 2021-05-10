package top.lucas9.crowdfunding.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.lucas9.crowdfunding.entity.Admin;
import top.lucas9.crowdfunding.entity.AdminExample;
import top.lucas9.crowdfunding.mapper.AdminMapper;
import top.lucas9.crowdfunding.service.api.AdminService;
import top.lucas9.crowdfunding.utils.CheckEffective;
import top.lucas9.crowdfunding.utils.Encrypt;

import java.util.List;
import java.util.Objects;

@Service
public class AdminServiceImpl implements AdminService {

    private AdminMapper adminMapper;

    @Autowired
    public AdminServiceImpl(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    @Override
    public Admin getAdminByPrimaryKey(Integer id) {
        return adminMapper.selectByPrimaryKey(id);
    }

    @Override
    public Admin login(String loginAccount, String userPassword) {
        if (!CheckEffective.stringEffective(loginAccount) || !CheckEffective.stringEffective(userPassword)) {
            return null;
        }
        AdminExample adminExample = new AdminExample();
        adminExample.createCriteria().andLoginAccountEqualTo(loginAccount);
        List<Admin> adminList = adminMapper.selectByExample(adminExample);
        if (!CheckEffective.collectionEffective(adminList)) {
            return null;
        }
        Admin admin = adminList.get(0);
        if (null == admin) {
            return null;
        }
        String pwdFromDB = admin.getUserPassword();
        String pwdFromBrowser = Encrypt.md5(userPassword);
        if (Objects.equals(pwdFromDB, pwdFromBrowser)) {
            return admin;
        }
        return null;
    }

    @Override
    public PageInfo<Admin> queryAdminByKeyWord(Integer pageNum, Integer pageSize, String keyword) {
        PageHelper.startPage(pageNum, pageSize);
        List<Admin> adminList = adminMapper.queryAdminByKeyWord(keyword);
        return new PageInfo<Admin>(adminList);
    }

    @Override
    public void batchRemoveAdmin(List<Integer> adminIdList) {
        // QBC：Query By Criteria
        AdminExample adminExample = new AdminExample();
        // Criteria对象可以帮助我们封装查询条件,通过使用Criteria对象，可以把Java代码转换成SQL语句中WHERE子句里面的具体查询条件
        AdminExample.Criteria criteria = adminExample.createCriteria();
        // 针对要查询的字段封装具体的查询条件
        criteria.andIdIn(adminIdList);
        // 执行具体操作时把封装了查询条件的Example对象传入
        adminMapper.deleteByExample(adminExample);
    }

    @Override
    public void addUser(Admin admin) {
        String pwd = admin.getUserPassword();
        admin.setUserPassword(Encrypt.md5(pwd));
        adminMapper.insert(admin);
        return;
    }

    @Override
    public Admin getUserById(Integer adminId) {
       return adminMapper.selectByPrimaryKey(adminId);
    }

    @Override
    public void updateUser(Admin admin) {
        String pwd = admin.getUserPassword();
        admin.setUserPassword(Encrypt.md5(pwd));
        adminMapper.updateByPrimaryKey(admin);
        return;
    }
}
