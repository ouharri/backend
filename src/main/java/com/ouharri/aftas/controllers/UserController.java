package com.ouharri.aftas.controllers;

import com.ouharri.aftas.model.dto.auth.ChangePasswordRequest;
import com.ouharri.aftas.model.entities.User;
import com.ouharri.aftas.services.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * Controller class for handling user-related endpoints.
 *
 * @author Ouharri Outman
 * @version 1.0
 */
@RestController
@RequestMapping("/api/v2/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    /**
     * Retrieves a list of all users.
     *
     * @return ResponseEntity containing the list of users and HTTP status OK.
     */
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = service.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    /**
     * Changes the password of the currently logged-in user.
     *
     * @param request        The change password request.
     * @param connectedUser  The principal representing the currently connected user.
     * @return ResponseEntity with HTTP status OK.
     */
    @PatchMapping
    public ResponseEntity<?> changePassword(
            @RequestBody ChangePasswordRequest request,
            Principal connectedUser
    ) {
        service.changePassword(request, connectedUser);
        return ResponseEntity.ok().build();
    }
}