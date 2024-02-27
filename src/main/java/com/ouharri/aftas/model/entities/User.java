package com.ouharri.aftas.model.entities;

import com.ouharri.aftas.model.enums.Gender;
import com.ouharri.aftas.model.enums.Role;
import com.ouharri.aftas.model.enums.UserStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * Represents a user entity in the system.
 * Extends the {@link AbstractEntity} and implements the {@link UserDetails} interface for Spring Security integration.
 * Includes user-specific attributes like email, password, personal details, and roles.
 *
 * @author <a href="mailto:ouharrioutman@gmail.com">Ouharri Outman</a>
 * @version 2.0
 */
@Getter
@Setter
@Entity
@SuperBuilder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_user")
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends AbstractEntity<UUID> implements UserDetails {

    /**
     * The user's password.
     */
    @NotBlank(message = "password cannot be blank.")
    @Size(min = 8, message = "Password must be at least 8 characters long.")
    private String password;

    /**
     * The path to the user's image.
     */
    private String image;

    /**
     * The user's phone number.
     */
    @Pattern(regexp = "0\\d{9}", message = "Phone number must match the format '0XXXXXXXXX'")
    @Column(unique = true)
    private String phoneNumber;

    /**
     * The user's email address.
     */
    @Email(message = "Email was not provided")
    @Size(max = 80, message = "Email is too long")
    @Column(unique = true)
    private String email;

    /**
     * The user's first name.
     */
    @NotNull(message = "FirstName must be present")
    @Size(min = 1, message = "Firstname cannot be empty")
    @Size(max = 30, message = "Firstname is too long")
    private String firstname;

    /**
     * The user's last name.
     */
    @Size(max = 30, message = "Lastname is too long")
    private String lastname;

    /**
     * The user's gender.
     */
    @Enumerated(EnumType.STRING)
    private Gender gender = Gender.MALE;

    /**
     * The nationality of the user.
     */
    private String nationality;

    /**
     * The birthdate of the user.
     */
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    /**
     * The user authentication status.
     */
    @Enumerated(EnumType.STRING)
    private UserStatus status = UserStatus.OFFLINE;

    /**
     * The user's address.
     */
    @Embedded
    private Address address = new Address();

    /**
     * The user's role.
     */
    @Enumerated(EnumType.STRING)
    private Role role;

    /**
     * The list of tokens associated with the user.
     */
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Token> tokens;

    /**
     * Indicates whether the user's account is enabled.
     * A disabled account cannot be used for authentication.
     * Default value is {@code true}, meaning the account is enabled by default.
     */
    private boolean enabled = true;

    /**
     * Indicates whether the user's account is non-locked.
     * A locked account cannot be used for authentication, typically as a security measure.
     * Default value is {@code true}, meaning the account is non-locked (unlocked) by default.
     */
    private boolean accountNonLocked = true;

    /**
     * Return the authorities granted to the user.
     *
     * @return A collection of granted authorities.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    /**
     * Returns the password used to authenticate the user.
     *
     * @return The user's password.
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Returns the username used to authenticate the user.
     *
     * @return The user's email address.
     */
    @Override
    public String getUsername() {
        return getEmail();
    }

    /**
     * Indicates whether the user's account has expired.
     *
     * @return {@code true} if the user's account is valid (i.e., not expired),
     * {@code false} otherwise.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user's credentials (password) have expired.
     *
     * @return {@code true} if the user's credentials are valid (i.e., not expired),
     * {@code false} otherwise.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

}