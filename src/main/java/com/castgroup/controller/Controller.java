package com.castgroup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.castgroup.service.DiffService;
import com.castgroup.utils.Constants;
import static com.castgroup.utils.Constants.DiffType.LEFT;
import static com.castgroup.utils.Constants.DiffType.RIGHT;;

/**
 * PersonController is a class that handle all the requests.
 */
@RequestMapping("/v1")
@RestController
public class Controller {
	@Autowired
	DiffService diffService;

	@RequestMapping(value=Constants.DIFF_LEFT, method = RequestMethod.POST, consumes = "application/json")
	public void saveLeft(@PathVariable Integer id, @RequestBody String payload) {
		diffService.save(payload, id, LEFT);
	}
	
	@RequestMapping(value=Constants.DIFF_RIGHT, method = RequestMethod.POST, consumes = "application/json")
	public void saveRight(@PathVariable Integer id, @RequestBody String payload) {
		diffService.save(payload, id, RIGHT);
	}
	
	@RequestMapping(value=Constants.DIFF_RESULT, method = RequestMethod.GET, produces = "application/json")
	public String getResult(@PathVariable Integer id) {
		return diffService.getDiff(id);
	}
}
