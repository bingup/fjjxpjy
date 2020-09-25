package com.fjjxpjy.dao;

import com.fjjxpjy.pojo.User;
import com.fjjxpjy.utils.Page;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;

/**
 * @author fangjj
 * @date 2020/9/22
 * @description
 */
public class UserDao extends BaseDao {

    public User checkUser(String username, String password) {
        String sql = "select * from user  where username = ? and password = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username, password);
        } catch (DataAccessException e) {
            return null;

        }
    }

    public List<User> findAll(String username, Page page) {
        String sql = "SELECT " +
                "u.username username, " +
                "u.id id, " +
                "d.id deptId, " +
                "u.email email, " +
                "u.real_name realName, " +
                "u.age age, " +
                "u.gender gender, " +
                "u.is_secret isSecret, " +
                "d.NAME deptName, " +
                "u.login_time loginTime  " +
                "FROM " +
                "USER u " +
                "LEFT JOIN dept d ON u.dept_id = d.id " +
                "where u.username like ? " +
                "limit ?,?";

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), "%" + username + "%",
                (page.getPageCurrent() - 1) * page.getSize(), page.getSize());
    }


    public Integer count(String username) {
        String sql = "select count(*) ROW_COUNT FROM  user  WHERE username LIKE ?";
        try {
            return jdbcTemplate.queryForObject(sql, Integer.class, "%" + username + "%");
        } catch (DataAccessException e) {
            return null;
        }

    }

    public void delete(Integer id) {
        String sql = "delete  from  user where  id = ?";
        jdbcTemplate.update(sql, id);
    }


    public User findUser(Integer id) {
        String sql = "SELECT " +
                "u.username username, " +
                "u.id id, " +
                "u.dept_id deptId, " +
                "u.email email, " +
                "u.real_name realName, " +
                "u.qq_openid qqOpenid, "+
                "u.wx_openid wxOpenid, "+
                "u.age age, " +
                "u.gender gender, " +
                "u.is_secret isSecret, " +
                "u.login_time loginTime,  " +
                "u.phone phone, "+
                "u.desc1 desc1, "+
                "u.register_time registerTime "+
                "FROM  " +
                "USER u  " +
                "where  id =?";
        try {
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), id );
        } catch (DataAccessException e) {
            return null;
        }
    }

    public  void update(User user){
        String sql ="UPDATE USER  " +
                "SET  " +
                "username=?, " +
                "dept_id=?, " +
                "email=?, " +
                "real_name=?, " +
                "qq_openid=?, " +
                "wx_openid=?, " +
                "age=?, " +
                "gender = ?, " +
                "is_secret =?, " +
                "login_time=?, " +
                "phone=?, " +
                "register_time=? " +
                "WHERE " +
                "id = ?";

        jdbcTemplate.update(sql,
                user.getUsername(),
                user.getDeptId(),
                user.getEmail(),
                user.getRealName(),
                user.getQqOpenid(),
                user.getWxOpenid(),
                user.getAge(),
                user.getGender(),
                user.getIsSecret(),
                user.getLoginTime(),
                user.getPhone(),
                user.getRegisterTime(),
                user.getId());
    }

    public void add(User user){
        String sql="INSERT INTO USER ( " +
                "id, " +
                "username, " +
                "PASSWORD, " +
                "email, " +
                "qq_openid, " +
                "wx_openid, " +
                "real_name, " +
                "age, " +
                "phone, " +
                "gender, " +
                "desc1, " +
                "register_time, " +
                "login_time, " +
                "is_secret, " +
                "dept_id  " +
                ") " +
                "VALUES " +
                " ( " +
                "NULL,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql,
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getQqOpenid(),
                user.getWxOpenid(),
                user.getRealName(),
                user.getAge(),
                user.getPhone(),
                user.getGender(),
                user.getDesc1(),
                user.getRegisterTime(),
                user.getLoginTime(),
                user.getIsSecret(),
                user.getDeptId());
    }


    public void updateUrl(Integer id, String picUrl) {
        String sql= "update user set pic=? where id=?";
        jdbcTemplate.update(sql,picUrl,id);
    }


    public List<User> getUserByDeptId(Integer deptId) {
        String sql= "SELECT * from user where dept_id= ?";

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class),deptId);
    }
}
