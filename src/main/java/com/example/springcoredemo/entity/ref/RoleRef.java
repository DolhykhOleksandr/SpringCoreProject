package com.example.springcoredemo.entity.ref;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("USER_ROLE")
@Data
@AllArgsConstructor
public class RoleRef {

    @Column("ROLE_ID")
    private Integer roleId;

}