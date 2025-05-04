package storage.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import storage.entity.User;
import storage.entity.UserProfile;

import java.util.Date;
import static storage.constant.EmailConstant.*;

@Service
public class EmailService {
	
	UserService userService;
	UserProfileService userProfileService;
	JavaMailSender javaMailSender;
	
	EmailService(UserService userService, JavaMailSender javaMailSender, UserProfileService userProfileService){
		this.userService = userService;
		this.userProfileService = userProfileService;
		this.javaMailSender = javaMailSender;
		
	}
	
	  public void sendAddEmail() {
	        SimpleMailMessage message = createAddEmail();
	        javaMailSender.send(message);
	    }
	  
	  public void sendDeleteEmail(){
		    SimpleMailMessage message = createDeleteEmail();
	        javaMailSender.send(message);	    
	    }

	  private SimpleMailMessage createAddEmail(){
		    User currentUser = userService.getCurrentUser();
		    UserProfile currentUserProfile = userProfileService.getById(currentUser.getUserID());
		    Integer totalCost = currentUserProfile.getTotalCost();
		    Integer containersStored = currentUserProfile.getContainersStored();
	        SimpleMailMessage message = new SimpleMailMessage();
	        message.setFrom(FROM_EMAIL);
	        message.setTo(currentUser.getEmail());
	        message.setSubject("Container added");
	        message.setText("Hello " + currentUser.getEmail() + " you successfully added an container" +
	                        " your total cost are : "+totalCost + 
	                        " you have " + containersStored + " containers stored");
	        message.setSentDate(new Date());
	        return message;
	    }
	    
	  private SimpleMailMessage createDeleteEmail(){
		    User currentUser = userService.getCurrentUser();
		    UserProfile currentUserProfile = userProfileService.getById(currentUser.getUserID());
		    Integer totalCost = currentUserProfile.getTotalCost();
		    Integer containersStored = currentUserProfile.getContainersStored();
	        SimpleMailMessage message = new SimpleMailMessage();
	        message.setFrom(FROM_EMAIL);
	        message.setTo(currentUser.getEmail());;
	        message.setSubject("Container deleted");
	        message.setText("Hello " + currentUser.getEmail() + " you successfully deleted an container" +
                            " your total cost are : " +totalCost + 
                            " you have " + containersStored + " containers stored");
	        message.setSentDate(new Date());
	        return message;
	    }
   }
