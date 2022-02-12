package com.dailycoder.bs.user.service.impl;

import com.dailycoder.bs.common.exception.AlreadyExistsException;
import com.dailycoder.bs.common.exception.GenericException;
import com.dailycoder.bs.user.entity.Organization;
import com.dailycoder.bs.user.mapper.OrganizationMapper;
import com.dailycoder.bs.user.repository.OrganizationRepository;
import com.dailycoder.bs.user.service.OrganizationService;
import com.dailycoder.bs.user.vo.OrganizationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    OrganizationRepository organizationRepository;


    @Autowired
    OrganizationMapper organizationMapper;

    @Override
    public OrganizationVO create(OrganizationVO organizationVO) {
        Organization organization = null;
        if (checkIfExists(organizationVO.getCode())) {
            throw new AlreadyExistsException("Organization already exists for this email");
        }
        try {
            organization = organizationMapper.toEntity(organizationVO);
            organization = organizationRepository.save(organization);
        } catch (Exception e) {
            throw new GenericException(e.getMessage());
        }
        return organizationMapper.fromEntity(organization);
    }

    private boolean checkIfExists(String code) {
        return organizationRepository.existsByCode(code);
    }

    @Override
    public OrganizationVO get(String id) {
        Optional<Organization> orgOptional =
                organizationRepository.findById(id);
        Organization organization = orgOptional.orElseThrow(() -> new RuntimeException("Organization doesn't exist with the given Id: " + id));
        return organizationMapper.fromEntity(organization);
    }

    @Override
    public void delete(String id) {
        organizationRepository.deleteById(id);
    }

    @Override
    public OrganizationVO update(OrganizationVO organizationVO) {
        return null;
    }

    @Override
    public Page<OrganizationVO> findAll(String sort, Integer page, Integer size) {
        return null;
    }


}
