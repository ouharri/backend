package com.ouharri.aftas.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for handling application-specific requests.
 * This controller provides endpoints for various application functionalities, including obtaining CSRF tokens.
 * Uses Spring's CSRF protection mechanisms to secure the application against cross-site request forgery attacks.
 *
 * @author <a href="mailto:ouharrioutman@gmail.com">Ouharri Outman</a>
 * @version 1.0
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/app")
public class AppController {

    @GetMapping("/csrf/token")
    public CsrfToken csrf(CsrfToken token) {
        return token;
    }
}
