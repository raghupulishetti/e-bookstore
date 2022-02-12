package com.dailycoder.bs.user.entity;

import com.dailycoder.bs.common.entity.DateAudit;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "USER")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User extends DateAudit {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "USER_ID", updatable = false, nullable = false)
    private String id;

    @Column(name = "USER_NAME", nullable = false, length = 50)
    private String userName;

    @Column(name = "PASSWORD", nullable = false, length = 50)
    private String password;

    @Column(name = "EMAIL", nullable = false, length = 100)
    private String email;

    @Column(name = "ENABLED", nullable = false)
    private boolean enabled;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORG_ID", nullable = false)
    Organization organization;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = CascadeType.DETACH)
    @JoinTable(name = "USER_ROLE",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    Set<Role> roles = new HashSet<>(0);

}
