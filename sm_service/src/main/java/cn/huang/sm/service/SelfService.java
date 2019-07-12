package cn.huang.sm.service;

import cn.huang.sm.entity.Staff;

/**
 * @Author: Yaking
 * @Date: 2019-07-12 0:10
 * @Describe:
 */
public interface SelfService {
    Staff login(String account, String password);

    void changePassword(Integer id, String password);
}
