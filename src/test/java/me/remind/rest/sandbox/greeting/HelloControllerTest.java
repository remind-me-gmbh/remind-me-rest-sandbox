package me.remind.rest.sandbox.greeting;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.remind.rest.sandbox.greeting.config.GreetingConfig;
import me.remind.rest.sandbox.controller.HelloController;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(HelloController.class)
public class HelloControllerTest {
    private static final String HELLO_WORLD = "Hello World!";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private GreetingConfig.GreetingProperties greetingProperties;

    @Test
    public void getHelloWorld() throws Exception {
        when(greetingProperties.getMessage()).thenReturn(HELLO_WORLD);

        mvc.perform(MockMvcRequestBuilders.get("/hello").accept(MediaType.APPLICATION_JSON))
           .andDo(MockMvcResultHandlers.print())
           .andExpect(MockMvcResultMatchers.status().isOk())
           .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
           .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.is(HELLO_WORLD)));
    }

}
