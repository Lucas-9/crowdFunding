package top.lucas9.crowdfunding.service.api;

import top.lucas9.crowdfunding.entity.Menu;

import java.util.List;

public interface MenuService {
    List<Menu> getMenuList();

    void updateMenu(Menu menu);

    Menu getMenuById(Integer menuId);

    void addMenu(Menu menu);

    void delMenuById(Integer menuId);
}
