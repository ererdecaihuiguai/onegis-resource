package cn.bluethink.onegis.resource.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class UserControllerTest {
	
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp(){
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void testSave() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.post("/api/user/save")
				.param("userName", "李莉")
				.param("nickName", "lily")
				.param("email", "ferwt@qq.com")
				.param("id", "5")
				.param("avatar", "196756aafdff47f1b35dfd5021d36ff7.jpg")
				)
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void testDelete() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.post("/api/user/delete")
				.param("id", "5")
				)
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void testGetById() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/user/getById")
				.param("id", "5")
				)
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void testGetForPage() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/user/getForPage")
				.param("pageSize", "1")
				.param("pageNum", "1")
				.param("userName", "刘晓楠")
				.param("nickName", "阿茵")
				)
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andDo(MockMvcResultHandlers.print());
	}

}
