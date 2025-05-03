package storage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import storage.entity.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, Integer> {
	
	UserProfile findById(int Id);

}
