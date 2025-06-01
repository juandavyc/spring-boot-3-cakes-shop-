package com.juandavyc.bakery.entity;
import com.juandavyc.bakery.entity.enums.PermissionEnum;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "permissions")

@Setter
@Getter
@ToString

@NoArgsConstructor
@AllArgsConstructor

@Builder
public class PermissionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true, insertable = false, updatable = false)
    public PermissionEnum name;
}

