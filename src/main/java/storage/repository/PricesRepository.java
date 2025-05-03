package storage.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import storage.entity.Prices;

public interface PricesRepository extends JpaRepository<Prices, Integer> {
	
	Optional<Prices> findById(Integer id);

}
