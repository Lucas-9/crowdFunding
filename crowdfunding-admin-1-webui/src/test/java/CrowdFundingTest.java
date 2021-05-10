//import com.github.pagehelper.PageInfo;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import top.lucas9.crowdfunding.entity.Admin;
//import top.lucas9.crowdfunding.entity.Role;
//import top.lucas9.crowdfunding.mapper.AdminMapper;
//import top.lucas9.crowdfunding.mapper.RoleMapper;
//import top.lucas9.crowdfunding.service.api.AdminService;
//import top.lucas9.crowdfunding.utils.Encrypt;
//
//import javax.sql.DataSource;
//import java.sql.Connection;
//import java.sql.SQLException;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations= {"classpath:spring-persist-mybatis.xml","classpath:spring-persist-tx.xml"})
//public class CrowdFundingTest {
//    @Autowired
//    private DataSource dataSource;
//    @Autowired
//    private RoleMapper roleMapper;
//    @Autowired
//    private AdminMapper adminMapper;
//
//    @Test
//    public void testConnection() {
//        Connection connection = null;
//        try {
//            connection = dataSource.getConnection();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        System.out.println(connection);
//    }
//
//
//    @Test
//    public void testMD5() {
//        String s = "123456";
//        String res = Encrypt.md5(s);
//        System.out.println(res);
//        // 1EA0CD9394AB95BAEB650E752FF088E3
//    }
//
//    @Test
//    public void batchSaveAdmin() {
//        for (int i = 0; i < 50; i++) {
//            adminMapper.insert(new Admin(null, "p" + i, Encrypt.md5("123456"), "p" + 1, "1@qq.com", null));
//        }
////        for(int i = 0; i < 50; i++) {
////            adminMapper.insert(new Admin(null, "loginAccount"+i, "1111111", "userName"+i, "email"+i+"@qq.com", null));
////        }
//    }
//    @Test
//    public void batchSaveRole() {
//        for (int i = 0; i < 50; i++) {
//            roleMapper.insert(new Role(i + 1, "key" + i));
//        }
//    }
//
//}
