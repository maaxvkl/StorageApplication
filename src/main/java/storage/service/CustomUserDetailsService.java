package storage.service;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import storage.entity.User;
import storage.repository.UserRepository;

//user service
@Service
public class CustomUserDetailsService implements UserDetailsService {

	private UserRepository userRepository;
	
	
	public CustomUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}


	 @Override
     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		    Optional<User> user = userRepository.findByEmail(username);
		      if (user.isEmpty()) {
		          throw new UsernameNotFoundException("no user found" + username);
		          } else {
		       //     validateLoginAttempt(user);
		      //      user.setLastLoginDateDisplay(user.getLastLoginDate());
		      //      user.setLastLoginDate(new Date());
		              userRepository.save(user.get());
		      //      UserPrincipal userPrincipal = new UserPrincipal(user);
		      //      LOGGER.info(FOUND_USER_BY_USERNAME + username);
		              return new CustomUserDetails(user.get());
		          }
	}

}