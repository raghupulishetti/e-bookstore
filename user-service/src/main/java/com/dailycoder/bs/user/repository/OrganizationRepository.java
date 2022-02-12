package com.dailycoder.bs.user.repository;

import com.dailycoder.bs.user.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, String> {

    boolean existsByCode(String code);
}
