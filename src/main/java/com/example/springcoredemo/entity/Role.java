package com.example.springcoredemo.entity;



import com.example.springcoredemo.entity.ref.PermissionRef;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Set;


@Table("ROLE")
@Data
@AllArgsConstructor
@Builder
public class Role {

    @Id
    @Column("ROLE_ID")
    private Integer roleId;

    @Column("ROLE_NAME")
    private String roleName;

    @MappedCollection(idColumn = "ROLE_ID")
    private Set<PermissionRef> permissions;

    public void addPermission(Permission permission) {
        permissions.add(new PermissionRef(permission.getPermissionId()));
    }

}