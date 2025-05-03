package storage.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import storage.entity.User;
import storage.service.ContainerService;
import storage.service.CustomUserDetails;
import storage.service.UserService;
import storage.utility.JwtTokenProvider;
import static storage.constant.SecurityConstant.JWT_TOKEN_HEADER;
import java.util.Optional;

@RestController
@RequestMapping("store")
public class UserController {
	
	UserService userService;
	AuthenticationManager authManager;
	JwtTokenProvider jwtTokenProvider;
	ContainerService containerService;
	
	public UserController(UserService userService, AuthenticationManager authManager,
			JwtTokenProvider jwtTokenProvider, ContainerService containerService) {
		super();
		this.userService = userService;
		this.authManager = authManager;
		this.jwtTokenProvider = jwtTokenProvider;
		this.containerService = containerService;
	}

	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody User user){
	    User savedUser = userService.register(user);
		 if(savedUser != null)
		    return ResponseEntity.ok(savedUser);
		 else {
			return new ResponseEntity<>(null,HttpStatus.BAD_GATEWAY);
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<User> login(@RequestBody User user){
		authenticate(user.getEmail(), user.getPassword());
	    Optional<User> userLogin = userService.findUserByEmail(user.getEmail());
	    CustomUserDetails customUser = new CustomUserDetails(userLogin.get());
	    HttpHeaders jwtHeader = getJwtHeader(customUser);
	    return new ResponseEntity<>(userLogin.get(),jwtHeader,HttpStatus.OK);
	}
	
	private HttpHeaders getJwtHeader(CustomUserDetails customUser){
		HttpHeaders headers = new HttpHeaders();
		headers.add(JWT_TOKEN_HEADER, jwtTokenProvider.generateJwtToken(customUser));
		return headers;
		
	}
	
	private void authenticate(String email, String password){
		authManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
	}
	
}

