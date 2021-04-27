package gemography.backendcodingchallenge.trendinglanguages.dto;

import lombok.Data;

import java.util.List;

@Data
public class GithubResponse {
    private List<Repo> items;
}
