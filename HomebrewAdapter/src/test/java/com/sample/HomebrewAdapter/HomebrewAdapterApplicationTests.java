package com.sample.HomebrewAdapter;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
class HomebrewAdapterApplicationTests {
	
    @Autowired
    MockMvc mockMvc;

	@Test
	void testHomeBrewAPI() throws Exception {
		String expected = "{\"description\":\"Internet file retriever\",\"version\":\"1.21.3\",\"dependencies\":[\"libidn2\",\"openssl@1.1\"],\"generated_date\":\"2022-06-15\"}";
	    MvcResult andReturn = mockMvc.perform(get("/formula?name=wget")
                .contentType("application/json"))
                .andDo(print())
                .andExpect(status().isOk()).andReturn();
	    assertEquals(expected, andReturn.getResponse().getContentAsString());
	   
	}
	
	@Test
	void testNegativeHomeBrewAPI() throws Exception {
	    MvcResult andReturn = mockMvc.perform(get("/formula?name=wge")
                .contentType("application/json"))
                .andDo(print())
                .andExpect(status().is4xxClientError()).andReturn();
	    assertEquals(HttpStatus.NOT_FOUND.value(), andReturn.getResponse().getStatus());
	   
	}

}
