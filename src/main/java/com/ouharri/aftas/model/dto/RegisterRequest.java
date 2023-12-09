package com.ouharri.aftas.model.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
  @NotNull(message = "FirstName must be present")
  @Size(min = 1, message = "Firstname cannot be empty")
  @Size(max = 30, message = "Firstname is too long")
  private String firstname;

  @Size(max = 30, message = "LastName is too long")
  private String lastname;

  @Email(message = "Email was not provided")
  @Size(max = 80, message = "Email is too long")
  @Column(unique = true)
  private String email;

  @NotEmpty(message = "Password cannot be empty")
  @Size(min = 8, message = "Password is too short")
  private String password;
}
