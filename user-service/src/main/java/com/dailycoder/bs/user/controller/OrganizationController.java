package com.dailycoder.bs.user.controller;


import com.dailycoder.bs.user.service.OrganizationService;
import com.dailycoder.bs.user.vo.OrganizationVO;
import com.dailycoder.bs.common.http.header.HeaderGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@CrossOrigin
public class OrganizationController {

    @Autowired
    private HeaderGenerator headerGenerator;

    @Autowired
    private OrganizationService organizationService;

    @PostMapping("/organizations")
    public ResponseEntity<?> create(@RequestBody @Valid OrganizationVO organizationVO, HttpServletRequest request){

        OrganizationVO organization = organizationService.create(organizationVO);

        return new ResponseEntity<>(
                organizationVO,
                headerGenerator.getHeadersForSuccessPostMethod(request, organization.getId()),
                HttpStatus.CREATED);
    }

    @GetMapping("/organizations/{id}")
    public ResponseEntity<OrganizationVO> findOne(@PathVariable("id") String id) {

        OrganizationVO organizationVO = organizationService.get(id);
        return ResponseEntity.ok(organizationVO);

    }

    @DeleteMapping("/organizations/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id) {
        organizationService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/organizations")
    public ResponseEntity<?> update(@RequestBody @Valid OrganizationVO organizationVO) {

        organizationService.update(organizationVO);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/organizations")
    public ResponseEntity<?> findAll(@RequestParam(value = "sort", required = false) String sort,
                                            @RequestParam(value = "page", required = false) Integer page,
                                            @RequestParam(value = "size", required = false) Integer size) {

        Page<OrganizationVO> list = organizationService.findAll(sort, page, size);
        return ResponseEntity.ok(list);

    }
}
