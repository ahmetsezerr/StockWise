package org.ahmetsezer.stockwise.service.impl;

import lombok.RequiredArgsConstructor;
import org.ahmetsezer.stockwise.dto.request.AuthRequest;
import org.ahmetsezer.stockwise.dto.request.RegisterRequest;
import org.ahmetsezer.stockwise.dto.response.AuthResponse;
import org.ahmetsezer.stockwise.dto.response.RegisterResponse;
import org.ahmetsezer.stockwise.entity.Role;
import org.ahmetsezer.stockwise.entity.User;
import org.ahmetsezer.stockwise.exception.BusinessException;
import org.ahmetsezer.stockwise.exception.EmailAlreadyExistsException;
import org.ahmetsezer.stockwise.exception.ResourceNotFoundException;
import org.ahmetsezer.stockwise.repository.UserRepository;
import org.ahmetsezer.stockwise.security.JwtService;
import org.ahmetsezer.stockwise.service.AuthenticationService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public AuthResponse authenticate(AuthRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User not found with email: " + request.getEmail()));

        if (!user.getActive()) {
            throw new BusinessException("User account is inactive");
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BusinessException("Invalid email or password");
        }

        String authToken = jwtService.generateToken(user);

        AuthResponse response = new AuthResponse();
        response.setToken(authToken);
        response.setRole(user.getRole().name());
        response.setMessage("Login successful");

        return response;
    }

    @Override
    public RegisterResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException(
                    "This email is already in use: " + request.getEmail()
            );
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);
        user.setActive(true);

        User savedUser = userRepository.save(user);

        RegisterResponse response = new RegisterResponse();
        response.setMessage("User registered successfully");
        response.setRole(savedUser.getRole().name());

        return response;
    }
}