package com.mobile.app.Service;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.mobile.app.UI.modal.request.UserDetailsRequestModal;
import com.mobile.app.UI.modal.response.UserRest;

public interface IUserService {
	public UserRest createUser(@RequestBody UserDetailsRequestModal userRequest);

	public UserRest updateUser(@RequestBody UserDetailsRequestModal userRequest, @PathVariable String userId);

	public String DeleteUser(@PathVariable String userId);

	public UserRest getUser(@PathVariable String userId);

}
