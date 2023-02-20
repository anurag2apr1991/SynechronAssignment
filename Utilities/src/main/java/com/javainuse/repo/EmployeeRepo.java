package com.javainuse.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.javainuse.entity.Details;

@Repository
public interface EmployeeRepo extends CrudRepository<Details, Integer> {

}
