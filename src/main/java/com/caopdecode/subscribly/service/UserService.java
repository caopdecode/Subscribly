package com.caopdecode.subscribly.service;

import com.caopdecode.subscribly.dto.LoginDTO;
import com.caopdecode.subscribly.dto.UserDTO;
import com.caopdecode.subscribly.dto.UserResponse;

public interface UserService {
    void register(UserDTO user);
    UserResponse login(LoginDTO loginDTO);
}
