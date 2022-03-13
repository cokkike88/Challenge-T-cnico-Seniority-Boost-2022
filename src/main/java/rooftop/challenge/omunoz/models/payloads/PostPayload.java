package rooftop.challenge.omunoz.models.payloads;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class PostPayload implements Serializable
{
    @JsonProperty("text")
    private String text;
    @JsonProperty("chars")
    private Integer chars;
}
