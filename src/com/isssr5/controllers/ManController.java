package com.isssr5.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.isssr5.entities.DefaultServicesTable;
import com.isssr5.entities.MacroService;
import com.isssr5.exceptions.NotExistingUserException;
import com.isssr5.service.MacroServiceTransaction;

@Controller
@RequestMapping("/manual")
public class ManController {

	private DefaultServicesTable defaultServicesTable;
	private MacroServiceTransaction macroServiceTransaction;

	@Autowired
	public ManController(MacroServiceTransaction macroServiceTransaction) {
		defaultServicesTable = DefaultServicesTable.getInstance();
		this.macroServiceTransaction = macroServiceTransaction;
	}

	@RequestMapping(value = "/man", method = RequestMethod.GET)
	public String getMan() {
		return "man";

	}

	@RequestMapping(value = "/fileExample", method = RequestMethod.GET)
	public String getFileExample() {
		return "datafile";

	}

	@RequestMapping(value = "/getAllPublicCustomizedMacroService", method = RequestMethod.GET)
	public ModelAndView getAllPublicScales() throws NotExistingUserException {

		List<MacroService> ms_list = macroServiceTransaction
				.findPublicMacroService();
		ModelAndView model = new ModelAndView("list_macroservice");
		model.addObject("list_ms", ms_list);
		return model;
	}

	@RequestMapping(value = "/{user}/getAllPrivateCustomizedMacroService", method = RequestMethod.GET)
	public ModelAndView getAllPrivateScales(@PathVariable String user)
			throws NotExistingUserException {

		List<MacroService> ms_list = macroServiceTransaction
				.findPrivateMacroServiceById(user);
		ModelAndView model = new ModelAndView("list_macroservice");
		model.addObject("list_ms", ms_list);
		return model;
	}

	@RequestMapping(value = "/getAllDefaultElementaryService", method = RequestMethod.GET)
	public ModelAndView getAllDefaultElemService()
			throws NotExistingUserException {

		List<MacroService> ms_list = new ArrayList<MacroService>();
		Iterator<Entry<String, MacroService>> it = defaultServicesTable
				.getTable().entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, MacroService> pairs = (Map.Entry<String, MacroService>) it
					.next();
			ms_list.add((MacroService) pairs.getValue());
		}
		ModelAndView model = new ModelAndView("list_macroservice");
		model.addObject("list_ms", ms_list);
		return model;
	}

	@RequestMapping(value = "/getPublicByKeywords/{key}", method = RequestMethod.GET)
	public ModelAndView getSearchPublicByKeywords(@PathVariable String key)
			throws NotExistingUserException {

		List<MacroService> ms_list = macroServiceTransaction
				.findPublicMacroService();
		for (int i = 0; i < ms_list.size(); i++) {
			if (!ms_list.get(i).getKeywords().contains(key))
				ms_list.remove(ms_list.get(i));
		}
		ModelAndView model = new ModelAndView("list_macroservice");
		model.addObject("list_ms", ms_list);
		return model;
	}

	@RequestMapping(value = "/{user}/getPrivateKeywords/{key}", method = RequestMethod.GET)
	public ModelAndView getSearchPrivateByKeywords(@PathVariable String user,
			@PathVariable String key) throws NotExistingUserException {

		List<MacroService> ms_list = macroServiceTransaction
				.findPrivateMacroServiceById(user);

		for (int i = 0; i < ms_list.size(); i++) {
			if (!ms_list.get(i).getKeywords().contains(key))
				ms_list.remove(ms_list.get(i));
		}

		ModelAndView model = new ModelAndView("list_macroservice");
		model.addObject("list_ms", ms_list);
		return model;
	}

	@RequestMapping(value = "/getDefaultKeyword/{key}", method = RequestMethod.GET)
	public ModelAndView getSearchDefaultByKeyword(@PathVariable String key)
			throws NotExistingUserException {

		List<MacroService> ms_list = new ArrayList<MacroService>();
		Iterator<Entry<String, MacroService>> it = defaultServicesTable
				.getTable().entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, MacroService> pairs = (Map.Entry<String, MacroService>) it
					.next();
			MacroService temp = (MacroService) pairs.getValue();
			if(temp.getKeywords().contains(key))
				ms_list.add(temp);
		}

		ModelAndView model = new ModelAndView("list_macroservice");
		model.addObject("list_ms", ms_list);
		return model;
	}

}
