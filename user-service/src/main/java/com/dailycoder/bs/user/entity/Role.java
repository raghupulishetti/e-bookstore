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
@Table(name = "ROLE")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Role extends DateAudit {

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

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = CascadeType.DETACH)
    @JoinTable(name = "ROLE_PERMISSION",
            joinColumns = {@JoinColumn(name = "role_id")},
            inverseJoinColumns = {@JoinColumn(name = "permission_id")})
    Set<Role> permissions = new HashSet<>(0);

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "roles")
    @JsonIgnore
    private Set<User> users = new HashSet<>();
}
