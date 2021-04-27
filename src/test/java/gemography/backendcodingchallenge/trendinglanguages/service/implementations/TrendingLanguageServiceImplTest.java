package gemography.backendcodingchallenge.trendinglanguages.service.implementations;

import gemography.backendcodingchallenge.trendinglanguages.dto.GithubResponse;
import gemography.backendcodingchallenge.trendinglanguages.dto.Language;
import gemography.backendcodingchallenge.trendinglanguages.dto.Repo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class TrendingLanguageServiceImplTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private TrendingLanguageServiceImpl service;

    @Test
    public void getTrendingLanguages() {
        LocalDate localDate = LocalDate.now().minus(Period.ofDays(30));
        String url = "https://api.github.com/search/repositories?q=created:>{date}&sort={sort}&order={order}&per_page={per_page}";
        Map<String, String> uriParam = new HashMap<>();
        uriParam.put("date", localDate.toString());
        uriParam.put("sort", "stars");
        uriParam.put("order", "desc");
        uriParam.put("per_page", "100");

        List<Repo> items = new ArrayList<>();
        items.add(Repo.builder().language("C").name("test name").description("test description").gitUrl("git.git.git").id(1L).build());
        items.add(Repo.builder().language("C").name("test name").description("test description").gitUrl("git.git.git").id(1L).build());
        items.add(Repo.builder().language("C#").name("test name").description("test description").gitUrl("git.git.git").id(1L).build());
        items.add(Repo.builder().language("Java").name("test name").description("test description").gitUrl("git.git.git").id(1L).build());
        items.add(Repo.builder().language("Java").name("test name").description("test description").gitUrl("git.git.git").id(1L).build());
        items.add(Repo.builder().language("Java").name("test name").description("test description").gitUrl("git.git.git").id(1L).build());
        items.add(Repo.builder().language("Java").name("test name").description("test description").gitUrl("git.git.git").id(1L).build());
        GithubResponse githubResponse = new GithubResponse();
        githubResponse.setItems(items);

        Mockito.when(restTemplate.getForEntity(url, GithubResponse.class, uriParam))
                .thenReturn(new ResponseEntity<>(githubResponse, HttpStatus.OK));

        List<Language> result = service.getTrendingLanguages();

        Map<String, Language> mapped = result.stream().collect(Collectors.toMap(Language::getName, Function.identity()));

        assertEquals(3, result.size());
        assertEquals(2, mapped.get("C").getOccurrence());
        assertEquals(1, mapped.get("C#").getOccurrence());
        assertEquals(4, mapped.get("Java").getOccurrence());
    }
}