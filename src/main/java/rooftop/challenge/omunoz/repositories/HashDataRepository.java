package rooftop.challenge.omunoz.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rooftop.challenge.omunoz.models.entities.HashData;

import java.util.List;

public interface HashDataRepository  extends JpaRepository<HashData, Long> {

    HashData findByHashDataAndDeleted(String hash, Boolean isDeleted);
    HashData findByIdAndDeleted(Long id, Boolean isDeleted);
    List<HashData> findAllByDeleted (boolean isDeleted);

}
