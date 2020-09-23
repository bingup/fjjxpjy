package com.fjjxpjy.dao;

import com.fjjxpjy.pojo.Menu;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;

/**
 * @author fangjj
 * @date 2020/9/22
 * @description
 */
public class MenuDao extends BaseDao {

    public List<Menu> listAll() {
        String sql = "select * from menu";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Menu.class));
    }
}
