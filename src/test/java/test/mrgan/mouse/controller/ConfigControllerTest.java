package test.mrgan.mouse.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URL;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.mrgan.mouse.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:/config/application.properties")
public class ConfigControllerTest {
	private URL base;
	@LocalServerPort
	private String port;

	@Autowired
	private MockMvc mvc;

	@Autowired
	private TestRestTemplate restTemplate;

	@Before
	public void setUp() throws Exception {
		this.base = new URL("http://localhost:" + port + "/config");
	}

	@Test
	public void getHello() throws Exception {
		ResponseEntity<String> response = restTemplate.getForEntity(
				base.toString(), String.class);
		System.out.println(response.getBody());
		// assertThat(
		// response.getBody(),
		// equalTo("hello 192.222.222.110:root  192.222.222.110/root:中文密码"));
	}

	@Test
	public void getConfig() throws Exception {
		mvc.perform(
				MockMvcRequestBuilders.get("/config").accept(
						MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(
						content()
								.string(equalTo("hello 192.222.222.110:root  192.222.222.110/root:中文密码")));
		System.out.println(content());
	}
}
