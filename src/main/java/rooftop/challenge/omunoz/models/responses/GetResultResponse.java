package rooftop.challenge.omunoz.models.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Map;

public record GetResultResponse (@JsonProperty("result") Map<String, Integer> result) implements Serializable
{}
