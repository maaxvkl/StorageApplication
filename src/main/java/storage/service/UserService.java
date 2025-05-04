package storage.service;

import java.util.Date;
import java.util.Optional;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import storage.entity.User;
import storage.entity.UserProfile;
import storage.repository.PricesRepository;
import storage.repository.UserProfileRepository;
import storage.repository.UserRepository;
import static storage.enumeration.Role.ROLE_ADMIN;


@Service
public class UserService {
	
	PricesRepository pricesRepository;
	UserProfileRepository userProfileRepository;
	UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	
	public UserService(UserRepository userRepository,PricesRepository pricesRepository, PasswordEncoder passwordEncoder, UserProfileRepository userProfileRepository) {
		super();
		this.userRepository = userRepository;
		this.userProfileRepository = userProfileRepository;
		this.pricesRepository = pricesRepository;
		this.passwordEncoder = passwordEncoder;
	}

	
	public Optional<User> findUserByEmail(String email) {
		 return userRepository.findByEmail(email);
		
	}
	
	public User register(User user) {
		Optional<User> checkUser = userRepository.findByEmail(user.getEmail());
		 if(!checkUser.isPresent()){
			User saveUser = new User();
			UserProfile saveUserProfile = new UserProfile();
			saveUser.setActive(true);
			saveUser.setEmail(user.getEmail());
			saveUser.setPassword(passwordEncoder.encode(user.getPassword()));
			saveUser.setRegistrationDate(new Date());
			saveUser.setRole(ROLE_ADMIN.name());
			saveUser.setAuthorities(ROLE_ADMIN.getAuthorities());
			saveUserProfile.setUser(saveUser);
			userProfileRepository.save(saveUserProfile);
			userRepository.save(saveUser);
			return saveUser;
		}
		    return null;
	}
	
	public User getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
         if(!(authentication instanceof AnonymousAuthenticationToken)){
           String username = authentication.getName();
           User user = userRepository.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("no user found"));
           return user; 
	    }
           return null;
	 }
	
	
}

