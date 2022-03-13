package rooftop.challenge.omunoz.models.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedHashMap;

public record GetResponse (@JsonProperty("id") Long id,
                           @JsonProperty("hash") String hash,
                           @JsonProperty("chars") Integer chars,
                           @JsonProperty("result") LinkedHashMap<String, Integer> result)
{}
