package com.ouharri.aftas.entities;

import com.ouharri.aftas.enums.Gender;
import com.ouharri.aftas.enums.Role;
import com.ouharri.aftas.enums.Sex;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_user")
public class User extends AbstractEntity implements UserDetails {

  private String password;

  private String image;

  @Pattern(regexp = "0\\d{9}", message = "Phone number must match the format '0XXXXXXXXX'")
  @Column(unique = true)
  private String phoneNumber;

  @Email(message = "Email was not provided")
  @Size(max = 80, message = "Email is too long")
  @Column(unique = true)
  private String email;

  @NotNull(message = "FirstName must be present")
  @Size(min = 1, message = "Firstname cannot be empty")
  @Size(max = 30, message = "Firstname is too long")
  private String firstname;

  @Size(max = 30, message = "Firstname is too long")
  private String lastname;

  @Enumerated(EnumType.STRING)
  @JdbcTypeCode(SqlTypes.NAMED_ENUM)
  private Gender gender;

  @Enumerated(EnumType.STRING)
  @JdbcTypeCode(SqlTypes.NAMED_ENUM)
  private Sex sex;

  @Embedded
  private Address address = new Address();

  @Enumerated(EnumType.STRING)
  @JdbcTypeCode(SqlTypes.NAMED_ENUM)
  private Role role;

  @OneToMany(mappedBy = "user")
  private List<Token> tokens;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return role.getAuthorities();
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return getEmail();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
