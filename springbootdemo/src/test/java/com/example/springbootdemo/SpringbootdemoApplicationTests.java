package com.example.springbootdemo;

import com.example.springbootdemo.dao.DepartmentDao;
import com.example.springbootdemo.pojo.Department;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;

@SpringBootTest
class SpringbootdemoApplicationTests {

	@Test
	void contextLoads() {
		Collection<Department> department = new DepartmentDao().getDepartment();
		for (Department department1 : department) {
			System.out.println(department1);
		}
	}


}
