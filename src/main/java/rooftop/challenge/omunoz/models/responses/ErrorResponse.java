package rooftop.challenge.omunoz.models.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ErrorResponse (@JsonProperty("error") Boolean error,
                             @JsonProperty("message") String message,
                             @JsonProperty("code") Integer code)
{}
