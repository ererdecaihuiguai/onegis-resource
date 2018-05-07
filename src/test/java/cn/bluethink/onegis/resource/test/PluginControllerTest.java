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
public class PluginControllerTest {

	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp(){
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	
	@Test
	public void testSave() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.post("/api/plugin/save")
				.param("name", "关联用户测试")
				.param("tags", "测试1")
				.param("userId", "5")
				.param("version", "1.1.0")
				.param("icon", "19c21e5f57874f56aea71c2d37f5f964.jpg")
				.param("id", "5")
				.param("description", "编辑成功")
				)
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void testDelete() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.post("/api/plugin/delete")
				.param("id", "5")
				)
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void testGetById() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/plugin/getById")
				.param("id", "5")
				)
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void testGetForPage() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/plugin/getForPage")
				.param("pageSize", "1")
				.param("pageNum", "1")
				.param("name", "用户插件")
				.param("tags", "插件测试")
				)
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andDo(MockMvcResultHandlers.print());
	}

}
