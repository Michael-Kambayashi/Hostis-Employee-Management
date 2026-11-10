package com.gabriel.emplms.repository;
import com.gabriel.emplms.entity.EmployeeData;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeDataRepository extends CrudRepository<EmployeeData,Integer> {
    List<EmployeeData> findAllByDepartmentId(int departmentId);
}