package com.juandavyc.bakery.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;


import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    public String lastname;
    @Column(nullable = false)
    public String firstname;
    @Column(unique = true,nullable = false)
    private String email;
    //@Column(name = "is_enabled")
    private boolean isEnabled;

    //@Column(name = "account_No_Expired")
    private boolean accountNoExpired;

    // @Column(name = "account_No_Locked")
    private boolean accountNoLocked;

    //@Column(name = "credential_No_Expired")
    private boolean credentialNoExpired;

    // embed
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    public Set<RoleEntity> roles;


    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        UserEntity that = (UserEntity) object;
        return Objects.equals(id, that.id) && Objects.equals(username, that.username) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email);
    }

//    INSERT INTO public.permissions (name)
//    VALUES
//            ('CREATE'),
//    ('READ'),
//            ('UPDATE'),
//            ('DELETE');
//
//    INSERT INTO public.roles (name)
//    VALUES
//            ('ADMIN'),
//    ('USER'),
//            ('GUEST');
//
//
//    INSERT INTO public.role_permission (role_id, permission_id)
//    VALUES
//            (1, 1), -- ADMIN - CREATE
//            (1, 2), -- ADMIN - READ
//            (1, 3), -- ADMIN - UPDATE
//            (1, 4); -- ADMIN - DELETE
//
//    INSERT INTO public.role_permission (role_id, permission_id)
//    VALUES
//            (2, 2), -- USER - READ
//            (2, 3); -- USER - UPDATE
//
//    INSERT INTO public.role_permission (role_id, permission_id)
//    VALUES
//            (3, 2); -- GUEST - READ
//
//
//    INSERT INTO public.users (
//            username, password, lastname, firstname,email, is_enabled, account_no_expired, account_no_locked, credential_no_expired
//            ) VALUES
//    ('admin', 'admin123', 'Admin', 'Super','1@1.com', true, true, true, true),
//            ('juan', 'juan123', 'Yara', 'Juan','2@1.com', true, true, true, true),
//            ('guest', 'guest123', 'Invitado', 'Anon','3@1.com', true, true, true, true);
}
