package gemography.backendcodingchallenge.trendinglanguages.service.implementations;

import gemography.backendcodingchallenge.trendinglanguages.dto.GithubResponse;
import gemography.backendcodingchallenge.trendinglanguages.dto.Language;
import gemography.backendcodingchallenge.trendinglanguages.dto.Repo;
import gemography.backendcodingchallenge.trendinglanguages.service.interfaces.TrendingLanguageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TrendingLanguageServiceImpl implements TrendingLanguageService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<Language> getTrendingLanguages() {
        log.info("Requesting trending languages for the past 30 days.");
        try {
            LocalDate localDate = LocalDate.now().minus(Period.ofDays(30));
            String url = "https://api.github.com/search/repositories?q=created:>{date}&sort={sort}&order={order}&per_page={per_page}";
            Map<String, String> uriParam = new HashMap<>();
            uriParam.put("date", localDate.toString());
            uriParam.put("sort", "stars");
            uriParam.put("order", "desc");
            uriParam.put("per_page", "100");
            ResponseEntity<GithubResponse> responseEntity = restTemplate.getForEntity(url, GithubResponse.class, uriParam);
            GithubResponse body = responseEntity.getBody();
            if (body != null) {
                log.info("Formatting items to List of Language");
                List<Language> languages = new ArrayList<>();
                body.getItems()
                        .stream()
                        .filter(repo -> repo.getLanguage() != null)
                        .collect(Collectors.groupingBy(Repo::getLanguage, Collectors.toList()))
                        .forEach((l, repos) -> languages.add(
                                Language.builder()
                                        .name(l)
                                        .repos(repos)
                                        .occurrence(repos.size())
                                        .build()
                        ));
                log.info("Resolving languages - Elements found: {}", languages.size());
                return languages;
            }
        } catch (Exception e) {
            log.info("Error occurred while requesting GitHub API");
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
