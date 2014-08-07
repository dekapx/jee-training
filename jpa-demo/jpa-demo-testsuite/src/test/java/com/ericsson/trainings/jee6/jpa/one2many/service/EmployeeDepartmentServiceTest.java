package com.ericsson.trainings.jee6.jpa.one2many.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.ericsson.trainings.jee6.jpa.generic.dao.AbstractJpaDao;
import com.ericsson.trainings.jee6.jpa.one2many.dao.DepartmentDao;
import com.ericsson.trainings.jee6.jpa.one2many.dao.EmployeeDao;
import com.ericsson.trainings.jee6.jpa.one2many.dao.impl.DepartmentDaoImpl;
import com.ericsson.trainings.jee6.jpa.one2many.dao.impl.EmployeeDaoImpl;
import com.ericsson.trainings.jee6.jpa.one2many.entity.Department;
import com.ericsson.trainings.jee6.jpa.one2many.entity.Employee;
import com.ericsson.trainings.jee6.jpa.one2many.service.impl.DepartmentServiceBean;
import com.ericsson.trainings.jee6.jpa.one2many.service.impl.EmployeeServiceBean;

@RunWith(Arquillian.class)
public class EmployeeDepartmentServiceTest {
	@Deployment(name = "person-test")
	public static Archive<?> createTestArchive() {
		final WebArchive war = ShrinkWrap.create(WebArchive.class, "person-test.war");

		war.addClass(EmployeeService.class);
		war.addClass(EmployeeServiceBean.class);

		war.addClass(DepartmentService.class);
		war.addClass(DepartmentServiceBean.class);

		war.addClass(AbstractJpaDao.class);
		war.addClass(EmployeeDao.class);
		war.addClass(EmployeeDaoImpl.class);

		war.addClass(DepartmentDao.class);
		war.addClass(DepartmentDaoImpl.class);

		war.addClass(Employee.class);
		war.addClass(Department.class);

		war.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
		war.addAsResource("META-INF/test-mysql-persistence.xml", "META-INF/persistence.xml");
		war.addAsWebInfResource("test-mysql-ds.xml");

		return war;
	}

	@EJB
	private EmployeeService<Employee> employeeService;

	@EJB
	private DepartmentService<Department> departmentService;

	@Test
	@InSequence(1)
	public void test_insert_dept() throws Exception {
		Department department = null;

		department = new Department("Engineering");
		department = departmentService.save(department);

		assertNotNull(department);
		assertNotNull(department.getDeptId());

		department = new Department("Administration");
		department = departmentService.save(department);

		assertNotNull(department);
		assertNotNull(department.getDeptId());

		department = new Department("Human Resource");
		department = departmentService.save(department);

		assertNotNull(department);
		assertNotNull(department.getDeptId());

		department = new Department("Finance");
		department = departmentService.save(department);

		assertNotNull(department);
		assertNotNull(department.getDeptId());
	}

	private Collection<Employee> getEmployees() {
		Collection<Employee> employees = new ArrayList<>();
		employees.add(new Employee("Steve", "Austin", "steve@wwe.com", null));
		employees.add(new Employee("Bruce", "Lee", "bruce@jeetkunedo.com", null));

		return employees;
	}

	@Test
	@InSequence(2)
	public void test_findBy_dept() throws Exception {
		final String deptName = "Administration";
		Department department = departmentService.findByDeptName(deptName);
		assertNotNull(department);
		assertNotNull(department.getDeptId());
		assertEquals(deptName, department.getDeptName());
	}

	@Test
	@InSequence(2)
	public void test_insert_employee_by_dept() throws Exception {
		Department departmentEngg = departmentService.findByDeptName("Engineering");
		employeeService.save(new Employee("Steve", "Austin", "steve@wwe.com", departmentEngg));
		employeeService.save(new Employee("Bruce", "Lee", "bruce@jeetkunedo.com", departmentEngg));

		Department department = departmentService.findByDeptName("Administration");
		employeeService.save(new Employee("Tom", "Alter", "tom@aol.com", department));
		employeeService.save(new Employee("Chris", "Tucker", "chris@msn.com", department));

		Collection<Employee> employees = employeeService.findAll();
		assertNotNull(employees);
		assertTrue(employees.size() == 4);
	}
}
