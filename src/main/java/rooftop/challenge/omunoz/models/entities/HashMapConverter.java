package rooftop.challenge.omunoz.models.entities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;

import javax.persistence.AttributeConverter;
import java.util.Map;

@AllArgsConstructor
public class HashMapConverter implements AttributeConverter<Map<String, Integer>, String> {

    private final ObjectMapper objectMapper;

    @Override
    public String convertToDatabaseColumn(Map<String, Integer> attribute) {
        String jsonInfo = null;
        try {
            jsonInfo = objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonInfo;
    }

    @Override
    public Map<String, Integer> convertToEntityAttribute(String dbData) {
        Map<String , Integer> jsonInfo = null;
        try {
            jsonInfo = objectMapper.readValue(dbData, Map.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonInfo;
    }
}
