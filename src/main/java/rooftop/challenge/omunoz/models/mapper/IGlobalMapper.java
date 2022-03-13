package rooftop.challenge.omunoz.models.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import rooftop.challenge.omunoz.models.entities.HashData;
import rooftop.challenge.omunoz.models.responses.GetResponse;

import java.util.List;


@Mapper(componentModel = "spring")
public interface IGlobalMapper {

    @Mappings({
            @Mapping(target = "hash", source = "hashData")
    })
    GetResponse fromHashData(HashData hashData);

    List<GetResponse> fromListHashData(List<HashData> hashData);
}
