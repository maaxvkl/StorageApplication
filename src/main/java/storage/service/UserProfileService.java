package storage.service;

import java.util.List;
import java.util.Optional;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import storage.entity.Container;
import storage.entity.Prices;
import storage.entity.User;
import storage.entity.UserProfile;
import storage.repository.UserProfileRepository;

@Service
public class UserProfileService {
	
	UserProfileRepository userProfileRepository;
	UserService userService;
	PricesService pricesService;
	
	
	UserProfileService(UserProfileRepository userProfileRepository, UserService userService, PricesService pricesService){
		this.userProfileRepository = userProfileRepository;
		this.userService = userService;
		this.pricesService = pricesService;
	}
	
	UserProfile getById(int Id) {
		 return userProfileRepository.findById(Id);
		
	}

	void setData() {
		User currentUser = userService.getCurrentUser();
		UserProfile currentUserProfile = userProfileRepository.findById(currentUser.getUserID()).orElseThrow(()->
				                                                        new UsernameNotFoundException ("no user found"));
		List<Container> userContainers = currentUser.getContainerList();
		Integer totalCost = getTotalCost(userContainers);
		currentUserProfile.setContainersStored(userContainers.size());
		currentUserProfile.setTotalCost(totalCost);
		userProfileRepository.save(currentUserProfile);	
	}
	
	private Integer getTotalCost(List<Container> userContainers){
		Optional<Prices> prices = pricesService.findById(1);
		Integer totalCost = 0;
		for(Container container : userContainers) {
		   Integer currentCost=
					container.getHeigth()*prices.get().getHeightPrice() +
                    container.getLength()*prices.get().getLengthPrice() +
                    container.getWidth()*prices.get().getWidthPrice();
                   totalCost+=currentCost;	
		}
		   return totalCost;
	}

	public UserProfile getCurrentUserProfile() {
		User currentUser = userService.getCurrentUser();
		UserProfile currentUserProfile = userProfileRepository.findById(currentUser.getUserID()).orElseThrow(()->
				                                                        new UsernameNotFoundException ("no user found"));
		return currentUserProfile;
	}

}
