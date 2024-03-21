package com.kog.services;

import com.kog.dto.UserDto;
import com.kog.model.User;

public interface UserService {
   User save(UserDto userDto);
}
