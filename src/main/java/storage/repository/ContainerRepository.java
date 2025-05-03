package storage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import storage.entity.Container;
import storage.entity.User;

public interface ContainerRepository extends JpaRepository<Container, Integer> {

	List<Container> findByOwnedBy(User user);

}
