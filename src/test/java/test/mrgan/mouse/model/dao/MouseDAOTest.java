package test.mrgan.mouse.model.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.mrgan.mouse.Application;
import com.mrgan.mouse.model.dao.MouseDAO;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:/config/application.properties")
public class MouseDAOTest {
	@Autowired
	private MouseDAO mouseDAO;

	@Test
	public void test() {
		mouseDAO.findOne(1L);
		System.out.println(mouseDAO.findGroupNumMaxByGroupId("111"));
	}
}
