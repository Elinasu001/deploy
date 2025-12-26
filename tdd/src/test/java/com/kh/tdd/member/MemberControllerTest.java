package com.kh.tdd.member;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import com.kh.tdd.member.MemberController;
import com.kh.tdd.member.model.service.MemberService;


@WebMvcTest(MemberController.class)
@AutoConfigureMockMvc(addFilters = false)
public class MemberControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private MemberService memberservice;
	
	// POSTMAN을 안켜도 여기서 테스트 가능하다.
	@Test
	public void testFindById() throws Exception {
		mvc.perform(get("/api/members").param("userId", "user01").accept(MediaType.APPLICATION_JSON))
		  .andExpect(status().isOk()).andDo(print());
	}
}
