package nl.bo.techiteasydeel1.repositories;

import nl.bo.techiteasydeel1.models.Television;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TelevisionRepository extends JpaRepository<Television, Long> {

    //List<Television> findAllTelevisionsByBrandEqualsIgnoreCase(String brand);

}
