package com.mobile.app.ui.controller;

import java.util.HashMap;
import java.util.Map;

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

import com.mobile.app.UI.modal.request.UserDetailsRequestModal;
import com.mobile.app.UI.modal.response.UserRest;

@RestController
@RequestMapping("/users")
public class UserController {
	Map<String, UserRest> users;

	@GetMapping
	public String getUsers(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "limit", defaultValue = "20") int limit) {
		return "Hey page " + page + " limit is 0= " + limit;
	}

	@GetMapping(path = "/{userId}")
	public ResponseEntity<UserRest> getUser(@PathVariable String userId) {
		if (users.containsKey(userId)) {
			return new ResponseEntity<UserRest>(users.get(userId), HttpStatus.OK);
		}
		return new ResponseEntity<UserRest>(HttpStatus.NO_CONTENT);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserRest> createUser(@RequestBody UserDetailsRequestModal userRequest) {
		UserRest user = new UserRest();
		user.setFirstName(userRequest.getFirstName());
		user.setEmail(userRequest.getEmail());
		user.setUserId(userRequest.getUserId());

		if (users == null)
			users = new HashMap<>();
		users.put(user.getUserId(), user);
		return new ResponseEntity<UserRest>(user, HttpStatus.OK);
	}

	@PutMapping
	public String updateUser() {
		return "User Updated";
	}

	@DeleteMapping
	public String DeleteUser() {
		return "User Deleted";
	}

}
