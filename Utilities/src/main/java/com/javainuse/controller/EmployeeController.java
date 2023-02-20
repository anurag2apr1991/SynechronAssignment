package com.javainuse.controller;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.javainuse.dto.DetailsJsonDto;
import com.javainuse.dto.JSONComparisonOutput;
import com.javainuse.entity.Details;
import com.javainuse.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService service;

	@GetMapping("/employees")
	public String firstPage() {
		return "Hello World";
	}

	@GetMapping("/employees/all")
	public ResponseEntity<List<Details>> getAllDetails() {
		System.out.println(service.getAllDetails());
		return ResponseEntity.ok(service.getAllDetails());
	}

	@PostMapping(value = "/employees/upload", consumes = { "multipart/form-data" })
	public ResponseEntity<List<JSONComparisonOutput>> uploadJsonFile(@RequestPart("file") MultipartFile file) {
		try {
			Reader reader = new InputStreamReader(file.getInputStream());
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(reader);
			JSONArray jsonArray = (JSONArray) obj;

			List<DetailsJsonDto> input = new ArrayList<>();
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonObject = (JSONObject) jsonArray.get(i);
				DetailsJsonDto dto = new DetailsJsonDto();
				Long id = (Long) jsonObject.get("id");
				dto.setId(id.intValue());
				dto.setName((String) jsonObject.get("name"));
				dto.setGender((String) jsonObject.get("gender"));
				dto.setCity((String) jsonObject.get("city"));
				dto.setPincode((String) jsonObject.get("pincode"));

				input.add(dto);
			}

			List<Details> dbDetails = service.getAllDetails();

			List<JSONComparisonOutput> outputList = new ArrayList<>();
			for (DetailsJsonDto dto : input) {
				Optional<Details> matchedRecord = dbDetails.stream().filter(data -> data.getId().equals(dto.getId()))
						.findAny();
				if (matchedRecord.isPresent()) {
					Details detail = matchedRecord.get();
					JSONComparisonOutput output = new JSONComparisonOutput(dto.getId(),
							dto.getName().equals(detail.getName()), dto.getGender().equals(detail.getGender()),
							dto.getCity().equals(detail.getCity()), dto.getPincode().equals(detail.getPincode()));
					outputList.add(output);
				} else {
					JSONComparisonOutput output = new JSONComparisonOutput(dto.getId(), false, false, false, false);
					outputList.add(output);
				}
			}

			return ResponseEntity.ok(outputList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

}