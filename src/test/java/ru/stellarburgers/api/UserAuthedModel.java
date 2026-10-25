package ru.stellarburgers.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserAuthedModel {

    private boolean success;
    private String accessToken;
    private String refreshToken;
    private UserEmailName user;
}
