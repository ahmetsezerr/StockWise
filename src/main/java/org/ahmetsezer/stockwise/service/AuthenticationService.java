package org.ahmetsezer.stockwise.service;

import org.ahmetsezer.stockwise.dto.request.AuthRequest;
import org.ahmetsezer.stockwise.dto.request.RegisterRequest;
import org.ahmetsezer.stockwise.dto.response.AuthResponse;
import org.ahmetsezer.stockwise.dto.response.RegisterResponse;

public interface AuthenticationService {
    RegisterResponse register(RegisterRequest request);
    AuthResponse authenticate(AuthRequest request);
}
