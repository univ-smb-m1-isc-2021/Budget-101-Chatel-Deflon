package budget.adapters;

import budget.application.UserService;
import budget.persistence.user.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {


    @Autowired
    private MockMvc mockMvc;
    private User user;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService service;

    @BeforeEach
    public void setUp() {
        user = new User(1L, "userTest", "passUserTest", "userTest@test.com");
    }

    @AfterEach
    public void tearDown() {
        user = null;
    }

//    @Test
//    public void greetingShouldReturnMessageFromService() throws Exception {
//        when(service.create(any())).thenReturn(user);
//        this.mockMvc.perform((RequestBuilder) post("/register").queryParam("username", "userTest", "password", "passUserTest", "email", "userTest@test.com")).andDo(print()).andExpect(status().isOk())
//                .andExpect(content().string(containsString("true")));
//    }

}

