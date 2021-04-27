# TrendingLanguages
Backend Coding Challenge - Gemography

### Technical Specs

A REST microservice that list the languages used by the 100 trending public repos on GitHub.
For every language, we calculate the attributes below 👇:
  * Number of repos using this language
  * The list of repos using the language

#### API
    URL: "GET /api/trending-languages"

#### RESPONSE
    ```javascript
    [
        {
            "name": "C#",
            "occurrence": 3,
            "repos": [
                {
                    "id": 357384834,
                    "name": "D2R-Offline",
                    "fullName": null,
                    "htmlUrl": null,
                    "gitUrl": null,
                    "description": "Diablo II: Resurrected, Offline-mode patcher",
                    "language": "C#"
                }
            ]
        }
    ]
    ```
  