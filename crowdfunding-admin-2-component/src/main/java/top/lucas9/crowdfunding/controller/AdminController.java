package top.lucas9.crowdfunding.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import top.lucas9.crowdfunding.constant.Constant;
import top.lucas9.crowdfunding.entity.Admin;
import top.lucas9.crowdfunding.entity.ResultEntity;
import top.lucas9.crowdfunding.service.api.AdminService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AdminController {
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @RequestMapping("/getAdmin")
    public String getAminByPrimaryKey(Model model) {
        Admin admin = adminService.getAdminByPrimaryKey(1);
        System.out.println(admin);
        model.addAttribute("admin", admin);
        return "admin-target";
    }

//    @RequestMapping("/admin/login")
    public String doLogin(@RequestParam("loginAccount") String loginAccount,
                          @RequestParam("userPassword") String userPassword,
                          Model model, HttpSession session) {
        Admin admin = adminService.login(loginAccount, userPassword);
        if (null == admin) {
            model.addAttribute(Constant.ATTR_NAME_MESSAGE, Constant.MESSAGE_LOGIN_FAILED);
            return "admin-login";
        }
        session.setAttribute(Constant.ATTR_NAME_LOGIN_ADMIN, admin);
        return "redirect:/admin/main/page.html";
    }

    @RequestMapping("/admin/logout")
    public String doLogin(HttpSession session) {
        session.invalidate();
        return "redirect:/index.html";
    }

    // 限制权限 @PreAuthorize("hasAnyAuthority('user:get')")
    // 限制角色 @PreAuthorize("hasRole('董事长')")
    @RequestMapping("/admin/queryUser/byKeyword")
    public String queryAdminByKeyWord(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                      @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                      @RequestParam(value = "keyword", defaultValue = "") String keyword,
                                      Model model) {
        PageInfo<Admin> adminPageInfo = adminService.queryAdminByKeyWord(pageNum, pageSize, keyword);
        model.addAttribute(Constant.ATTR_NAME_PAGE_INFO, adminPageInfo);
        return "admin-page";
    }

    @ResponseBody
    @RequestMapping("/admin/batch/remove")
    public ResultEntity<String> batchRemove(@RequestBody List<Integer> adminIdList) {
        try {
            adminService.batchRemoveAdmin(adminIdList);
            return ResultEntity.successWithoutData();
        } catch (Exception e) {
            return ResultEntity.failed(null, e.getMessage());
        }
    }

    @RequestMapping("/admin/add/user")
    public String addUser(Admin admin) {
        try {
            adminService.addUser(admin);
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof DuplicateKeyException) {
                throw new RuntimeException(Constant.MESSAGE_LOGIN_ACCT_ALREADY_IN_USE);
            }
        }
        // 页数给最大值，pageHelper自动修正
        return "redirect:/admin/queryUser/byKeyword.html?pageNum="+ Integer.MAX_VALUE;
    }

    @RequestMapping("/admin/edit/user")
    public String editUser(@RequestParam("adminId") Integer adminId, Model model) {
        Admin admin = adminService.getUserById(adminId);
        model.addAttribute("admin", admin);
        return "admin-edit";
    }

    @RequestMapping("/admin/update")
    public String updateUser(Admin admin, @RequestParam("pageNum") Integer pageNum) {
        try {
            adminService.updateUser(admin);
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof DuplicateKeyException) {
                throw  new RuntimeException(Constant.MESSAGE_LOGIN_ACCT_ALREADY_IN_USE);
            }
        }
        return "redirect:/admin/queryUser/byKeyword.html?pageNum="+ pageNum;
    }
}
