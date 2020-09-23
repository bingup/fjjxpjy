package com.fjjxpjy.dao;

import com.fjjxpjy.utils.DbUtil;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author fangjj
 * @date 2020/9/22
 * @description
 */
public class BaseDao {
   public JdbcTemplate jdbcTemplate = new JdbcTemplate(DbUtil.getDataSource());
}
