package cn.huang.sm.service;

import cn.huang.sm.dao.DepartmentDao;
import cn.huang.sm.entity.Department;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("departmentService")
public class DepartmentServiceImpl implements  DepartmentService {

    @Resource
    private DepartmentDao departmentDao;

    public void add(Department department) {
        departmentDao.insert(department);
    }

    public void remove(Integer id) {
        departmentDao.delete(id);
    }

    public void edit(Department department) {
        departmentDao.update(department);
    }

    public Department get(Integer id) {
        return departmentDao.selectById(id);
    }

    public List<Department> getAll() {
        return departmentDao.selectAll();
    }
}
