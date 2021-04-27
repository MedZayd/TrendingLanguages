package gemography.backendcodingchallenge.trendinglanguages.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Repo {
    private Long id;
    private String name;
    private String fullName;
    private String htmlUrl;
    private String gitUrl;
    private String description;
    private String language;
}
