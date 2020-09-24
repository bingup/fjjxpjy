package com.fjjxpjy.service;

import com.fjjxpjy.dao.UserDao;
import com.fjjxpjy.pojo.User;
import com.fjjxpjy.utils.DateUtil;
import com.fjjxpjy.utils.Page;

import java.util.List;

/**
 * @author fangjj
 * @date 2020/9/22
 * @description
 */
public class UserService {
    private UserDao userDao = new UserDao();

    public User checkUser(String username, String password) {
        return userDao.checkUser(username, password);
    }

    public List<User> findAll(String username, Page page) {
        return userDao.findAll(username, page);
    }

    public Integer count(String username) {
        return userDao.count(username);
    }

    public void delete(Integer id) {
        userDao.delete(id);
    }

    public User findUser(Integer id) {
        return userDao.findUser(id);
    }

    public  void update(User user){
        userDao.update(user);
    }
    public void add(User user){
        user.setLoginTime(DateUtil.getNow());
        user.setRegisterTime(DateUtil.getNow());
        userDao.add(user);
    }

    public void updateUrl(Integer id, String picUrl) {
        userDao.updateUrl(id,picUrl);
    }
}
