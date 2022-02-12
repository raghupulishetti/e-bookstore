package com.dailycoder.bs.user.mapper;

import com.dailycoder.bs.user.entity.Organization;
import com.dailycoder.bs.user.vo.OrganizationVO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class OrganizationMapper {

    public OrganizationVO fromEntity(Organization organization) {
        return OrganizationVO.builder().id(organization.getId())
                .name(organization.getName())
                .code(organization.getCode())
                .description(organization.getDescription())
                .build();
    }

    public Organization toEntity(OrganizationVO organizationVO) {
        return Organization.builder()
                .name(organizationVO.getName())
                .code(organizationVO.getCode())
                .description(organizationVO.getDescription())
                .build();
    }

    public Page<OrganizationVO> fromPagedEntity(Page<Organization> entities) {
        return entities.map(this::fromEntity);
    }
}
