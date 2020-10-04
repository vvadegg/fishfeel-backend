package ru.fishfeel.security.jwt;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public class JwtUser implements UserDetails {
    @JsonIgnore
    @Getter
    private final Long id;
    @Getter
    private final String firstName;
    @Getter
    private final String lastName;
    private final String password;
    @Getter
    private final String email;
    private final boolean enabled;
    @JsonIgnore
    @Getter
    private final Date lastPasswordResetDate;
    private final Collection<? extends GrantedAuthority> authorities;

    public JwtUser(Long id, String firstName, String lastName, String password, String email,
                   boolean enabled, Date lastPasswordResetDate, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.enabled = enabled;
        this.lastPasswordResetDate = lastPasswordResetDate;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
//        todo check!
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
//        todo check!
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
