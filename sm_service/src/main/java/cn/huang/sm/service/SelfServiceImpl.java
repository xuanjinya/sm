package cn.huang.sm.service;

import cn.huang.sm.dao.SelfDao;
import cn.huang.sm.dao.StaffDao;
import cn.huang.sm.entity.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Yaking
 * @Date: 2019-07-12 0:13
 * @Describe:
 */
@Service("selfService")
public class SelfServiceImpl implements SelfService {
    @Autowired
    private SelfDao selfDao;
    @Autowired
    private StaffDao staffDao;

    public Staff login(String account, String password) {
        Staff staff = selfDao.selectByAccount(account);
        if (staff == null) {
            return null;
        }
        if (staff.getPassword().equals(password)) {
            return staff;
        }
        return null;
    }

    public void changePassword(Integer id, String password) {
        Staff staff = staffDao.selectById(id);
        staff.setPassword(password);
        staffDao.update(staff);
    }
}
