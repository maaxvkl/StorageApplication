package storage.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import storage.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>  {

	Optional<User> findByEmail(String username);
		
}
