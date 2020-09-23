package com.fjjxpjy.service;

import com.fjjxpjy.dao.MenuDao;
import com.fjjxpjy.pojo.Menu;

import java.util.List;

/**
 * @author fangjj
 * @date 2020/9/22
 * @description
 */
public class MenuService {
    private MenuDao menuDao =new MenuDao();
    public List<Menu> listAll() {
        return menuDao.listAll();
    }
}
