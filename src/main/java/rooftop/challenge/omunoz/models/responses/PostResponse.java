package rooftop.challenge.omunoz.models.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PostResponse (@JsonProperty("id") Long id,
                            @JsonProperty("url") String url)
{}
