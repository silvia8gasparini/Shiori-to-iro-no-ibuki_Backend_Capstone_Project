package it.epicode.finalproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.epicode.finalproject.enumeration.Role;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name = "app_user")
public class User implements UserDetails {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String surname;

    @Column(unique = true)
    private String email;

    @JsonIgnore
    private String password;
    private String avatarUrl;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(mappedBy = "user")
    private DigitalCard digitalCard;

    @OneToMany(mappedBy = "user")
    private List<Purchase> purchases;

    @OneToOne(mappedBy = "user")
    private Cart cart;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Borrow> borrows;

    @OneToMany(mappedBy = "user")
    private List<Reservation> reservations;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
}
