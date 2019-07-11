package cn.huang.sm.service;

import cn.huang.sm.entity.Staff;

import java.util.List;

/**
 * @Author: Yaking
 * @Date: 2019-07-11 10:04
 * @Describe:
 */
public interface StaffService {
    void add(Staff staff);

    void remove(Integer id);

    void edit(Staff staff);

    Staff get(Integer id);

    List<Staff> getAll();
}
