package com.mobile.app.ui.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mobile.app.Service.IUserService;
import com.mobile.app.UI.modal.request.UserDetailsRequestModal;
import com.mobile.app.UI.modal.response.UserRest;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	IUserService userCreateService;

	Map<String, UserRest> users;

	@GetMapping
	public String getUsers(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "limit", defaultValue = "20") int limit) {
		return "Hey page " + page + " limit is 0= " + limit;
	}

	@GetMapping(path = "/{userId}")
	public ResponseEntity<UserRest> getUser(@PathVariable String userId) {

		return (userCreateService.getUser(userId) == null ? (new ResponseEntity<UserRest>(HttpStatus.NO_CONTENT))
				: (new ResponseEntity<UserRest>(userCreateService.getUser(userId), HttpStatus.OK)));
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserRest> createUser(@RequestBody UserDetailsRequestModal userRequest) {
		return userCreateService.createUser(userRequest) == null ? new ResponseEntity<UserRest>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<UserRest>(userCreateService.createUser(userRequest), HttpStatus.OK);
	}

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, path = "/{userId}")
	public ResponseEntity<UserRest> updateUser(@RequestBody UserDetailsRequestModal userRequest,
			@PathVariable String userId) {
		return userCreateService.updateUser(userRequest, userId) == null
				? new ResponseEntity<UserRest>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<UserRest>(userCreateService.updateUser(userRequest, userId), HttpStatus.OK);

	}

	@DeleteMapping(path = "/{userId}")
	public String DeleteUser(@PathVariable String userId) {
		return userCreateService.DeleteUser(userId) == null ? "User doesn't exist"
				: userCreateService.DeleteUser(userId);
	}

}
