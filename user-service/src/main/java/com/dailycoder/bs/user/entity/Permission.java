package com.dailycoder.bs.user.entity;

import com.dailycoder.bs.common.entity.DateAudit;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "PERMISSION")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Permission  extends DateAudit {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "ID", updatable = false, nullable = false)
    private String id;

    @Column(name = "NAME", nullable = false, length = 50)
    private String name;

    @Column(name = "CODE", nullable = false, length = 50)
    private String code;

    @Column(name = "DESCRIPTION", nullable = false, length = 250)
    private String description;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "permissions")
    @JsonIgnore
    private Set<Role> roles = new HashSet<>();
}
