package cn.huang.sm.service;

import cn.huang.sm.dao.StaffDao;
import cn.huang.sm.entity.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: Yaking
 * @Date: 2019-07-11 10:07
 * @Describe:
 */
/*作为一个Service的组件*/
@Service("staffService")
public class StaffServiceImpl implements StaffService {
    /*需要调用持久层*/
    @Autowired
    private StaffDao staffDao;

    public void add(Staff staff) {
        staff.setPassword("123456");
        staff.setWorkTime(new Date());
        staff.setStatus("正常");
        staffDao.insert(staff);
    }

    public void remove(Integer id) {
        staffDao.delete(id);
    }

    public void edit(Staff staff) {
        staffDao.update(staff);
    }

    public Staff get(Integer id) {
        return staffDao.selectById(id);
    }

    public List<Staff> getAll() {
        return staffDao.selectAll();
    }
}
