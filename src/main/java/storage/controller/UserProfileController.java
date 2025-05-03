package storage.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import storage.entity.UserProfile;
import storage.service.UserProfileService;

@RestController
@RequestMapping("store")
@CrossOrigin(origins = "http://localhost:4200")
public class UserProfileController {
	
	UserProfileService userProfileService;
	
	
	public UserProfileController(UserProfileService userProfileService) {
		this.userProfileService = userProfileService;
	}
	
	@GetMapping("/getCurrentUserProfile") 
	 public ResponseEntity<UserProfile> getCurrentUserProfile(){
		UserProfile currentUserProfile = userProfileService.getCurrentUserProfile();
		return new ResponseEntity<>(currentUserProfile,HttpStatus.OK);
		
	}		 
}