package com.caopdecode.subscribly.serviceimpl;

import com.caopdecode.subscribly.dto.LoginDTO;
import com.caopdecode.subscribly.dto.UserDTO;
import com.caopdecode.subscribly.dto.UserResponse;
import com.caopdecode.subscribly.model.User;
import com.caopdecode.subscribly.repository.UserRepository;
import com.caopdecode.subscribly.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void register(UserDTO userDTO){
        if(userRepository.findByEmail(userDTO.getEmail()).isPresent()){
            throw new IllegalArgumentException("Email already exists");
        }

        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());

        String hashedPassword = passwordEncoder.encode(userDTO.getPassword());
        user.setPassword(hashedPassword);

        user.setRole("USER");

        userRepository.save(user);
    }

    @Override
    public UserResponse login(LoginDTO loginDTO){
        User user = userRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if(!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())){
            throw new IllegalArgumentException("Invalid Credentials");
        }

        return new UserResponse(user.getName(), user.getEmail(), user.getRole());
    }
}
