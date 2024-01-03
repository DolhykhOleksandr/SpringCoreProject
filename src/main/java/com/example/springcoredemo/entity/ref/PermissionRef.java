package com.example.springcoredemo.entity.ref;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("ROLE_PERMISSION")
@Data
@AllArgsConstructor
public class PermissionRef {

    @Column("PERMISSION_ID")
    private Integer permissionId;

}