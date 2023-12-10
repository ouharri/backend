package com.ouharri.aftas.services.spec;

import com.ouharri.aftas.model.dto.auth.ChangePasswordRequest;
import com.ouharri.aftas.model.entities.User;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface UserService {
    Optional<User> findById(UUID id);

    List<User> getAllUsers();

    void changePassword(ChangePasswordRequest request, Principal connectedUser);
}