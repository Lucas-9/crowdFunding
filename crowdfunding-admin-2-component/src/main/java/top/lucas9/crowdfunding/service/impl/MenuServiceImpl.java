package top.lucas9.crowdfunding.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.lucas9.crowdfunding.entity.Menu;
import top.lucas9.crowdfunding.entity.MenuExample;
import top.lucas9.crowdfunding.mapper.MenuMapper;
import top.lucas9.crowdfunding.service.api.MenuService;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
    private MenuMapper menuMapper;
    @Autowired
    public MenuServiceImpl(MenuMapper menuMapper) {
        this.menuMapper = menuMapper;
    }

    @Override
    public List<Menu> getMenuList() {
        return menuMapper.selectByExample(new MenuExample());
    }

    @Override
    public void updateMenu(Menu menu) {
        menuMapper.updateByPrimaryKey(menu);
    }

    @Override
    public Menu getMenuById(Integer menuId) {
        return menuMapper.selectByPrimaryKey(menuId);
    }

    @Override
    public void addMenu(Menu menu) {
        menuMapper.insert(menu);
    }

    @Override
    public void delMenuById(Integer menuId) {
        menuMapper.deleteByPrimaryKey(menuId);
    }
}
