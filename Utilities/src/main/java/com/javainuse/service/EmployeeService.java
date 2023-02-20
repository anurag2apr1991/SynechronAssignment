package com.javainuse.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javainuse.entity.Details;
import com.javainuse.repo.EmployeeRepo;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepo repo;

	public List<Details> getAllDetails() {
		Iterable<Details> iterable = repo.findAll();
		Iterator<Details> iterator = iterable.iterator();
		List<Details> details = new ArrayList<>();
		while (iterator.hasNext()) {
			details.add(iterator.next());
		}
		return details;
	}

}
