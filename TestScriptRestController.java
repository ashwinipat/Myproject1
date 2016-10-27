package com.cybage.scriptmanagement.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.cybage.scriptmanagement.dto.TestScriptDTO;
import com.cybage.scriptmanagement.model.TestScriptModel;
import com.cybage.scriptmanagement.service.TestScriptService;

@RestController
@RequestMapping("/testScript")
public class TestScriptRestController {
	@Autowired
	TestScriptService testScriptService;

	// working
	// fetch single record on id
	@RequestMapping(value = "/getTestScript/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public TestScriptDTO getTestScript(@PathVariable int id) {
		TestScriptModel testScriptModel = testScriptService.getTestScripts(id);
		ModelMapper modelMapper = new ModelMapper();
		TestScriptDTO testScriptDTO = modelMapper.map(testScriptModel, TestScriptDTO.class);
		return testScriptDTO;
	}

	// working
	// add to db json input from body
	@RequestMapping(value = "/addTestScript", method = RequestMethod.POST, headers = "Accept=application/json")
	public void getTestCases(@RequestBody TestScriptDTO testCaseDto) {
		System.out.println("Test Case Id" + testCaseDto.getTest_script_id());
		ModelMapper modelMapper = new ModelMapper();
		TestScriptModel testScriptModel = modelMapper.map(testCaseDto, TestScriptModel.class);
		testScriptService.insertIntoDb(testScriptModel);
	}

	// working
	// delete on id
	@RequestMapping(value = "/deleteTestScript/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public TestScriptDTO deleteTestScript(@PathVariable int id) {
		TestScriptModel testScriptModel = testScriptService.deleteTestScript(id, new TestScriptModel());
		ModelMapper modelMapper = new ModelMapper();
		TestScriptDTO testScriptDTO = modelMapper.map(testScriptModel, TestScriptDTO.class);
		return testScriptDTO;
	}

	// working
	// to view all testScripts in database
	@RequestMapping(value = "/viewAllTestScripts", method = RequestMethod.GET)
	public ResponseEntity<List<TestScriptModel>> listAllUsers() {
		List<TestScriptModel> testScriptModelList = testScriptService.showAll();
		if (testScriptModelList.isEmpty()) {
			return new ResponseEntity<List<TestScriptModel>>(HttpStatus.NO_CONTENT);

		}
		return new ResponseEntity<List<TestScriptModel>>(testScriptModelList, HttpStatus.OK);
	}

	// working
	// hardcoded edit working give json data in body
	@RequestMapping(value = "/edit/updateTestScript", method = RequestMethod.POST)
	public ResponseEntity<List<TestScriptModel>> addStudent(@RequestBody TestScriptModel testScriptModel) {

		TestScriptModel tm1 = testScriptService.updateIntoDB(testScriptModel);

		List<TestScriptModel> testScriptModelList = testScriptService.showAll();
		if (testScriptModelList.isEmpty()) {
			return new ResponseEntity<List<TestScriptModel>>(HttpStatus.NO_CONTENT);

		}
		return new ResponseEntity<List<TestScriptModel>>(testScriptModelList, HttpStatus.OK);
	}

	/*@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	public ResponseEntity<TestScriptModel> updateScript(@PathVariable("id") int id,
			@RequestBody TestScriptModel testScriptModel) {
		System.out.println("Updating User " + id);

		TestScriptModel currentScript = testScriptService.getTestScripts(id);

		if (currentScript == null) {
			System.out.println("User with id " + id + " not found");
			return new ResponseEntity<TestScriptModel>(HttpStatus.NOT_FOUND);
		}

		testScriptService.updateIntoDB(currentScript);
		return new ResponseEntity<TestScriptModel>(currentScript, HttpStatus.OK);
	}*/

}
