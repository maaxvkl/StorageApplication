package storage.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import storage.entity.Container;
import storage.service.ContainerService;
import storage.service.UserService;

@RestController
@RequestMapping("store")
public class ContainerController {
	
	ContainerService containerService;
	UserService userService;
	
	ContainerController(ContainerService containerService, UserService userService){
		this.containerService = containerService;
		this.userService = userService;
	}
	
	@PostMapping("/setContainer")
	public ResponseEntity<Container> setContainer(@RequestBody Container container) {
		Container savedContainer = containerService.addContainer(container);
		return new ResponseEntity<>(savedContainer, HttpStatus.OK);
	}
	
	@GetMapping("/getUserContainers")
	public ResponseEntity<List<Container>> getUserContainers(){
		 List<Container> containers = containerService.getUserContainers();
		 return new ResponseEntity<>(containers,HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteContainer/{id}")
	public ResponseEntity<String>deleteContainer(@PathVariable int id) {
		containerService.deleteContainer(id);
		return ResponseEntity.ok("User delete successfully");
	}
}
