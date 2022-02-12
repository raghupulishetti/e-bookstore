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
@Table(name = "ORGANIZATION")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Organization extends DateAudit {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "ORG_ID", updatable = false, nullable = false)
    private String id;

    @Column(name = "ORG_NAME", nullable = false, length = 50)
    private String name;

    @Column(name = "CODE", nullable = false, length = 50)
    private String code;

    @Column(name = "DESCRIPTION", nullable = false, length = 250)
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "organization")
    @JsonIgnore
    private Set<User> users = new HashSet<>(0);

}
