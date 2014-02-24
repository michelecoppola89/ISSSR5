package com.isssr5.controllers;

import java.io.StringReader;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.springframework.oxm.jaxb.Jaxb1Marshaller;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.isssr5.entities.*;

@Controller
@RequestMapping("/scale")
public class ScaleController {

	@RequestMapping(value="/{scaleType}",method=RequestMethod.POST)
		public @ResponseBody String getScale(@PathVariable String scaleType,@RequestBody String body) {
			Domain domain;
			Source source= new StreamSource(new StringReader(body));
			
			if(scaleType.equals("Ordinal")||scaleType.equals("Nominal")){
				//IntervalDomain dom = (IntervalDomain) Jaxb2Marshaller.
				}
			
			Scale scale = ScaleFactory.getScaleInstance(scaleType, scaleType);
			return scale.getType();
		}

	
}
