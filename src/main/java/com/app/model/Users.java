package com.app.model;

import com.app.model.enums.Role;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Users implements UserDetails, Serializable {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    private String name;
    private String username;
    private String password;

    private boolean fantasy = false;
    private boolean horror = false;
    private boolean detective = false;
    private boolean romance = false;
    private boolean psychology = false;
    private boolean classic = false;

    @Enumerated(EnumType.STRING)
    private Role role;

    public void update(boolean fantasy, boolean horror, boolean detective, boolean romance, boolean psychology, boolean classic) {
        this.fantasy = fantasy;
        this.horror = horror;
        this.detective = detective;
        this.romance = romance;
        this.psychology = psychology;
        this.classic = classic;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Books> books = new ArrayList<>();

    public Users(String name, String username, String password, Role role) {
        this.name = name;
        this.role = role;
        this.username = username;
        this.password = passwordEncoder().encode(password);
        this.books = new ArrayList<>();
    }

    public Users(String username, Role role) {
        this.role = role;
        this.username = username;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(getRole());
    }

    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    public void update(Users update) {
        this.username = update.getUsername();
        this.role = update.getRole();
    }
}
