package com.rest.springbootemployee.controller;

import com.rest.springbootemployee.controller.dto.CompanyRequest;
import com.rest.springbootemployee.controller.dto.CompanyResponse;
import com.rest.springbootemployee.controller.mapper.CompanyMapper;
import com.rest.springbootemployee.entity.Company;
import com.rest.springbootemployee.service.CompanyService;
import com.rest.springbootemployee.entity.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private CompanyService companyService;
    private CompanyMapper companyMapper;

    public CompanyController(CompanyService companyService, CompanyMapper companyMapper) {
        this.companyService = companyService;
        this.companyMapper = companyMapper;
    }

    @GetMapping
    public List<Company> getAll() {
        return companyService.findAll();
    }

    @GetMapping("/{id}")
    public CompanyResponse getById(@PathVariable String id) {
        Company company = companyService.findById(id);
        return companyMapper.toResponse(company);
    }

    @GetMapping("/{id}/employees")
    public List<Employee> getEmployees(@PathVariable String id) {
        return companyService.getEmployees(id);
    }

    @GetMapping(params = {"page", "pageSize"})
    public List<Company> getByPage(Integer page, Integer pageSize) {
        return companyService.findByPage(page, pageSize);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
//    public Company create(@RequestBody Company company) {
//        return companyService.create(company);
//    }
    public CompanyResponse create(@RequestBody CompanyRequest companyRequest) {
        Company company = companyMapper.toEntity(companyRequest);
        Company savedCompany = companyService.create(company);
        return companyMapper.toResponse(savedCompany);
    }

    @PutMapping("/{id}")
    public CompanyResponse update(@PathVariable String id, @RequestBody CompanyRequest companyRequest) {
        Company company = companyMapper.toEntity(companyRequest);
        Company updatedCompany = companyService.update(id, company);
        return companyMapper.toResponse(updatedCompany);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompany(@PathVariable String id) {
        companyService.delete(id);
    }
}
