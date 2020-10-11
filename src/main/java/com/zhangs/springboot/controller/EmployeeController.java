package com.zhangs.springboot.controller;

import com.zhangs.springboot.dao.DepartmentDao;
import com.zhangs.springboot.dao.EmployeeDao;
import com.zhangs.springboot.entities.Department;
import com.zhangs.springboot.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

/**
 * @author ZhangS
 * @create 2020-09-12-9:07
 */

@Controller
public class EmployeeController {

	@Autowired
	EmployeeDao employeeDao;

	@Autowired
	DepartmentDao departmentDao;

	//	查询所有员工返回列表页面
	@GetMapping("/emps")
	public String list(Model model) {
//		获取所有员工
		Collection<Employee> employees = employeeDao.getAll();

		//放在请求域中
		model.addAttribute("emps", employees);

//		thymeleaf默认就会拼串 classpath:/templates/xxxx.html
		return "emp/list";
	}


	//	来到添加页面
	@GetMapping("emp")
	public String toAddPage(Model model) {
//		来到添加页面，查出所有的部门，在页面显示
		Collection<Department> departments = departmentDao.getDepartments();
		model.addAttribute("depts", departments);
		return "emp/add";
	}



		//员工添加功能
	//springMVC自动将请求参数和入参对象的属性进行一一绑定,要求请求参数的名字和Javabean入参的对象里面的属性名是一样的
	@PostMapping("/emp")
	public String addEmp(Employee employee) {
//		来到员工列表页面
//		保存新增的员工
		employeeDao.save(employee);
		//redirect表示重定向到一个地址   /代表当前项目路径
		//forward表示转发到一个地址
		return "redirect:/emps";
	}



	//来到修改页面，查出当前员工，在页面回显
	@GetMapping("/emp/{id}")
	public String toEditPage(@PathVariable("id") Integer id,Model model){
//		查出当前员工
		Employee employee = employeeDao.get(id);
//		保存到model中
		model.addAttribute("emp",employee);
//		来到修改页面，查出所有的部门，在页面显示
		Collection<Department> departments = departmentDao.getDepartments();
		model.addAttribute("depts", departments);

//		回到膝盖页面(add是一个修改添加双功能的页面)
		return "emp/add";
	}


//	员工修改,需要把修改的员工id传过来，避免将id也更新了
	@PutMapping("/emp")
	public String updateEmployee(Employee employee){
		employeeDao.save(employee);
		return "redirect:/emps";
	}

//	员工删除
	@DeleteMapping("/emp/{id}")
	public String deleteEmployee(@PathVariable("id") Integer id){
//		根据id删除
		employeeDao.delete(id);
//		返回至员工列表
		return "redirect:/emps";

	}


}
