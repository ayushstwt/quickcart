package com.tier3Hub.quickCart.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "roles")
@Entity
public class Role {
    @Id
    private Long roleId;
    private String roleName;
}
