package cn.huang.sm.dao;

import cn.huang.sm.entity.Staff;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Yaking
 * @Date: 2019-07-11 9:10
 * @Describe:
 */
@Repository("staffDao")
public interface StaffDao {
    void insert(Staff staff);

    void delete(Integer id);

    void update(Staff staff);

    Staff selectById(Integer id);

    List<Staff> selectAll();
}
