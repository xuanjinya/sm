package cn.huang.sm.dao;

import cn.huang.sm.entity.Staff;
import org.springframework.stereotype.Repository;

/**
 * @Author: Yaking
 * @Date: 2019-07-11 23:42
 * @Describe:
 */
@Repository("selfDao")
public interface SelfDao {
    Staff selectByAccount(String account);
}
