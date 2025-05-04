package storage.service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;
import storage.entity.Container;
import storage.entity.User;
import storage.repository.ContainerRepository;
import storage.repository.UserRepository;

@Service
public class ContainerService {
	
	ContainerRepository containerRepository;
	UserRepository userRepository;
	UserService userService;
	EmailService emailService;
	UserProfileService userProfileService;

	
	public ContainerService(ContainerRepository containerRepository, UserRepository userRepository,
			                UserService userService, EmailService emailService, UserProfileService userProfileService){
		this.containerRepository = containerRepository;
		this.userRepository = userRepository;
		this.userService = userService;
		this.emailService = emailService;
		this.userProfileService = userProfileService;
		
	}
	
	public Container addContainer(Container container){
		    LocalDate localDate = LocalDate.now();
		    User currentUser = userService.getCurrentUser();
            Container savedContainer = new Container();
            savedContainer.setHeigth(container.getHeigth());
            savedContainer.setLength(container.getLength());
            savedContainer.setWidth(container.getWidth());
            savedContainer.setStorageDate(localDate);
            savedContainer.setOwnedBy(currentUser);
            savedContainer = containerRepository.save(savedContainer);
            userProfileService.setData();
		    emailService.sendAddEmail();
            return savedContainer;
	}
	
	public void deleteContainer(int id){
		    containerRepository.deleteById(id);
		    userProfileService.setData();
		    emailService.sendDeleteEmail();	  
	}

	public List<Container> getUserContainers(){
		    User currentUser = userService.getCurrentUser();
            return containerRepository.findByOwnedBy(currentUser);             
	}
}
	



