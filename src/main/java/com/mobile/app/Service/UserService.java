package com.mobile.app.Service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mobile.app.UI.modal.request.UserDetailsRequestModal;
import com.mobile.app.UI.modal.response.UserRest;

@Service
public class UserService implements IUserService {
	Map<String, UserRest> users;

	@Override
	public UserRest createUser(UserDetailsRequestModal userRequest) {
		UserRest user = new UserRest();
		user.setFirstName(userRequest.getFirstName());
		user.setEmail(userRequest.getEmail());
		user.setUserId(userRequest.getUserId());

		if (users == null)
			users = new HashMap<>();
		users.put(user.getUserId(), user);
		return user;
	}

	@Override
	public UserRest updateUser(UserDetailsRequestModal userRequest, String userId) {
		if (users.containsKey(userId)) {
			UserRest  userupdate = users.get(userId);
			userupdate.setFirstName(userRequest.getFirstName());
			userupdate.setEmail(userRequest.getEmail());
			userupdate.setUserId(userRequest.getUserId());
			userupdate.setLastName(userRequest.getLastName());			
			return userupdate;
		}else{
			return null;
		}
		
	}

	@Override
	public String DeleteUser(String userId) {
		if (users.containsKey(userId)) {
			UserRest u = users.get(userId);
			users.remove(userId);
			return "User with id: "+u.getUserId()+" and FirstName "+u.getFirstName();
		}
		return null;
	}

	@Override
	public UserRest getUser(String userId) {
		if (users.containsKey(userId)) {
			return users.get(userId);
		}
		return null;
	}
	


}
