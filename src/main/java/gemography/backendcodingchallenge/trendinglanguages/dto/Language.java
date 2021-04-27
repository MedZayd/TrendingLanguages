package gemography.backendcodingchallenge.trendinglanguages.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class Language {
    private String name;
    private int occurrence;
    private List<Repo> repos;
}
