package gemography.backendcodingchallenge.trendinglanguages.restcontroller;

import gemography.backendcodingchallenge.trendinglanguages.dto.Language;
import gemography.backendcodingchallenge.trendinglanguages.service.implementations.TrendingLanguageServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TrendingLanguageRestApi {

    private final TrendingLanguageServiceImpl service;

    public TrendingLanguageRestApi(TrendingLanguageServiceImpl service) {
        this.service = service;
    }

    @GetMapping("/trending-languages")
    public ResponseEntity<List<Language>> getTrendingLanguages() {
        List<Language> languages = service.getTrendingLanguages();
        return ResponseEntity.ok().body(languages);
    }
}
