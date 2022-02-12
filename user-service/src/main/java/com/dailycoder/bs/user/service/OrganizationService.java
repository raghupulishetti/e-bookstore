package com.dailycoder.bs.user.service;

import com.dailycoder.bs.user.vo.OrganizationVO;
import org.springframework.data.domain.Page;

public interface OrganizationService {
    OrganizationVO create(OrganizationVO organizationVO);

    OrganizationVO get(String id);

    void delete(String id);

    OrganizationVO update(OrganizationVO organizationVO);

    Page<OrganizationVO> findAll(String sort, Integer page, Integer size);
}
