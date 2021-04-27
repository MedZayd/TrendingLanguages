package gemography.backendcodingchallenge.trendinglanguages.service.interfaces;

import gemography.backendcodingchallenge.trendinglanguages.dto.Language;

import java.util.List;

public interface TrendingLanguageService {
    List<Language> getTrendingLanguages();
}
