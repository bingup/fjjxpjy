package com.fjjxpjy.dao;

import com.fjjxpjy.pojo.Meeting;
import com.fjjxpjy.pojo.User;
import com.fjjxpjy.utils.Page;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;

/**
 * @author fangjj
 * @date 2020/9/25
 * @description
 */
public class MeetingDao extends BaseDao {

    public void add(Meeting meeting) {
        String sql = "insert into meeting(dept_id,title,content,publish_date,start_time," +
                "end_time,status,make_user) values (?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql,
                meeting.getDeptId(),
                meeting.getTitle(),
                meeting.getContent(),
                meeting.getPublishDate(),
                meeting.getStartTime(),
                meeting.getEndTime(),
                meeting.getStatus(),
                meeting.getMakeUser());
    }

    public List<Meeting> findAll(String title, Page<Meeting> page) {

        String sql = "SELECT " +
                " m.*, " +
                " d.NAME AS DeptName  " +
                "FROM " +
                " meeting m " +
                " LEFT JOIN dept d ON d.id = m.dept_id  " +
                "WHERE " +
                " m.title LIKE ?  " +
                " LIMIT ?,?";

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Meeting.class), "%" + title + "%",
                (page.getPageCurrent() - 1) * page.getSize(), page.getSize());

    }

    public Integer count(String title) {
        String sql = "select count(*) ROW_COUNT FROM  meeting  WHERE title LIKE ?";
        try {
            return jdbcTemplate.queryForObject(sql, Integer.class, "%" + title + "%");
        } catch (DataAccessException e) {
            return null;
        }
    }

    public List<Meeting> queryAll() {

        String sql = "select  * from meeting";

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Meeting.class));

    }

    public void updateStatusById(Meeting meeting) {
        String sql = "update meeting set status = ? where id = ?";
        jdbcTemplate.update(sql,meeting.getStatus(),meeting.getId());
    }
}
