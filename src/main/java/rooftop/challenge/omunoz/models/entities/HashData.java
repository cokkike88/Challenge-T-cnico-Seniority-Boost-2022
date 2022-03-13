package rooftop.challenge.omunoz.models.entities;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import javax.persistence.*;
import java.util.Map;

@Entity
@Table
@Data
public class HashData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 2000)
    private String hashData;
    @Column(nullable = false)
    private Integer chars;
    @Convert(converter = HashMapConverter.class)
    private Map<String, Integer> result;
    @Column(nullable = false)
    private Boolean deleted = false;

    private static final ObjectMapper objectMapper = new ObjectMapper();

}
