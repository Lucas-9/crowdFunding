package top.lucas9.crowdfunding.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.lucas9.crowdfunding.entity.Menu;
import top.lucas9.crowdfunding.entity.ResultEntity;
import top.lucas9.crowdfunding.service.api.MenuService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MenuController {

    private MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @RequestMapping("/menu/get/menuList")
    public ResultEntity<Menu> getMenuList() {
        Menu rootMenu = null;
        List<Menu> menuList = menuService.getMenuList();
        Map<Integer, Menu> menuMap = new HashMap<>();
        for (Menu menu : menuList) {
            Integer id = menu.getId();
            menuMap.put(id, menu);
        }
        for (Menu menu : menuList) {
            Integer pid = menu.getPid();
            if (null == pid) {
                rootMenu = menu;
                continue;
            }
            Menu fatherRoot = menuMap.get(pid);
            fatherRoot.getChildren().add(menu);
        }
        return ResultEntity.successWithData(rootMenu);
    }

    @RequestMapping("/menu/update/menu")
    public ResultEntity<String> updateMenu(Menu menu) {
        menuService.updateMenu(menu);
        return ResultEntity.successWithoutData();
    }

    @RequestMapping("/menu/getMenu/byId")
    public ResultEntity<Menu> getMenuById(@RequestParam("menuId") Integer menuId) {
        Menu menu = menuService.getMenuById(menuId);
        return ResultEntity.successWithData(menu);
    }

    @RequestMapping("/menu/add/menu")
    public ResultEntity<String> addMenu(Menu menu) {

        menuService.addMenu(menu);
        return ResultEntity.successWithoutData();
    }
    @RequestMapping("/menu/delMenu/byId")
    public ResultEntity<String> delMenuById(@RequestParam("menuId") Integer menuId) {
        menuService.delMenuById(menuId);
        return ResultEntity.successWithoutData();
    }
}
