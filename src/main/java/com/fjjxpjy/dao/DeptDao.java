package com.fjjxpjy.dao;

import com.fjjxpjy.pojo.Dept;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;

/**
 * @author fangjj
 * @date 2020/9/23
 * @description
 */
public class DeptDao extends BaseDao {

    public List<Dept> findAll(){
        String sql = "select  * from dept ";

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Dept.class));
    }
}
