package com.ericsson.trainings.jee6.jpa.many2many.service;

import static org.junit.Assert.assertNotNull;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.trainings.jee6.jpa.generic.dao.AbstractJpaDao;
import com.ericsson.trainings.jee6.jpa.many2many.dao.CourseDao;
import com.ericsson.trainings.jee6.jpa.many2many.dao.StudentDao;
import com.ericsson.trainings.jee6.jpa.many2many.dao.impl.CourseDaoImpl;
import com.ericsson.trainings.jee6.jpa.many2many.dao.impl.StudentDaoImpl;
import com.ericsson.trainings.jee6.jpa.many2many.entity.Course;
import com.ericsson.trainings.jee6.jpa.many2many.entity.Student;
import com.ericsson.trainings.jee6.jpa.many2many.service.impl.CourseServiceBean;
import com.ericsson.trainings.jee6.jpa.many2many.service.impl.StudentServiceBean;

@RunWith(Arquillian.class)
public class StudentCourseServiceTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(StudentCourseServiceTest.class);

	@Deployment(name = "student-course-test")
	public static Archive<?> createTestArchive() {
		final WebArchive war = ShrinkWrap.create(WebArchive.class, "student-course-test.war");

		war.addClass(StudentService.class);
		war.addClass(StudentServiceBean.class);

		war.addClass(CourseService.class);
		war.addClass(CourseServiceBean.class);

		war.addClass(AbstractJpaDao.class);
		war.addClass(StudentDao.class);
		war.addClass(StudentDaoImpl.class);

		war.addClass(CourseDao.class);
		war.addClass(CourseDaoImpl.class);

		war.addClass(Student.class);
		war.addClass(Course.class);

		war.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
		war.addAsResource("META-INF/test-mysql-persistence.xml", "META-INF/persistence.xml");
		war.addAsWebInfResource("test-mysql-ds.xml");

		return war;
	}

	@EJB
	private StudentService<Student> studentService;

	@EJB
	private CourseService<Course> courseService;

	@Test
	@InSequence(1)
	public void test_insert_student() throws Exception {
		LOGGER.info("executing test_insert_student...");

		Student student = null;

		student = studentService.save(new Student("Steve", "Austin", "steve@wwe.com"));
		assertNotNull(student);
		assertNotNull(student.getStudentId());
		LOGGER.info("inserted new student with is [{}]", student.getStudentId());

		student = studentService.save(new Student("Bruce", "Lee", "bruce@jeetkunedo.com"));
		assertNotNull(student);
		assertNotNull(student.getStudentId());
		LOGGER.info("inserted new student with is [{}]", student.getStudentId());

		student = studentService.save(new Student("Tom", "Alter", "tom@aol.com"));
		assertNotNull(student);
		assertNotNull(student.getStudentId());
		LOGGER.info("inserted new student with is [{}]", student.getStudentId());

		student = studentService.save(new Student("Chris", "Tucker", "chris@msn.com"));
		assertNotNull(student);
		assertNotNull(student.getStudentId());
		LOGGER.info("inserted new student with is [{}]", student.getStudentId());
	}

	@Test
	@InSequence(2)
	public void test_insert_course() throws Exception {
		LOGGER.info("executing test_insert_course...");

		Course course = null;

		course = courseService.save(new Course("Computer Science"));
		assertNotNull(course);
		assertNotNull(course.getCourseId());
		LOGGER.info("inserted new course with is [{}]", course.getCourseId());

		course = courseService.save(new Course("Electrinics & Telecommunications"));
		assertNotNull(course);
		assertNotNull(course.getCourseId());
		LOGGER.info("inserted new course with is [{}]", course.getCourseId());
	}

	@Test
	@InSequence(3)
	public void test_enroll_student_for_course() throws Exception {
		LOGGER.info("executing test_enroll_student_for_course...");

		Course course = courseService.findByCourseName("Computer Science");
		LOGGER.info("load the cource by name[{}]", course.getCourseName());

		Student student = new Student("Test", "Student", "test@msn.com");
		student = studentService.save(student);
		LOGGER.info("inserted new student with ID [{}]", student.getStudentId());

		student.getCourses().add(course);
		student = studentService.update(student);
		LOGGER.info("update student record with course");
	}

}
