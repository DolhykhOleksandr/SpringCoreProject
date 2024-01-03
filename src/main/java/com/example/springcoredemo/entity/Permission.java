package com.example.springcoredemo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("PERMISSION")
@Data
@AllArgsConstructor
@Builder
public class Permission {

    @Id
    @Column("PERMISSION_ID")
    private Integer permissionId;

    @Column("PERMISSION_NAME")
    private String permissionName;

}