package gemography.backendcodingchallenge.trendinglanguages.restcontroller;

import gemography.backendcodingchallenge.trendinglanguages.service.implementations.TrendingLanguageServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TrendingLanguageRestApi.class)
public class TrendingLanguageRestApiTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TrendingLanguageServiceImpl service;

    @Test
    public void getTrendingLanguages() throws Exception {

        when(service.getTrendingLanguages()).thenReturn(new ArrayList<>());

        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/trending-languages"))
                .andDo(print())
                .andExpect(status().isOk());

        verify(service, times(1)).getTrendingLanguages();
    }
}