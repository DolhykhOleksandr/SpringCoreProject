package com.example.springcoredemo.entity;

import com.google.common.base.Objects;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "permission")
public class Permission {

    @SequenceGenerator(
            name = "permission_seq",
            sequenceName = "permission_seq",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "permission_seq"
    )
    @Column(name = "permission_id")
    private Integer permissionId;

    @Column(name = "permission_name")
    private String permissionName;

    @ManyToMany(mappedBy = "permissions", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Role> roles;

    @Override
    public String toString() {
        return "Permission{" +
                "permissionId=" + permissionId +
                ", permissionName='" + permissionName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Permission that = (Permission) o;
        return Objects.equal(permissionId, that.permissionId) && Objects.equal(permissionName, that.permissionName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(permissionId, permissionName);
    }
}