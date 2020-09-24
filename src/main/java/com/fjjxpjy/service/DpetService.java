package com.fjjxpjy.service;

import com.fjjxpjy.dao.DeptDao;
import com.fjjxpjy.pojo.Dept;

import java.util.List;

/**
 * @author fangjj
 * @date 2020/9/23
 * @description
 */
public class DpetService {
    private DeptDao deptDao = new DeptDao();
    public List<Dept> findAll(){
        return deptDao.findAll();
    }
}
