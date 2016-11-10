package com.mrgan.mouse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class MouseController {
	@RequestMapping("/")
	public ModelAndView main(ModelAndView model) {
		model.setViewName("main");
		return model;
	}

	@RequestMapping("/enterGroupId")
	public ModelAndView enterGroupId(@RequestParam String mouseGroupId,
			ModelAndView model) {
		if (mouseGroupId == null || mouseGroupId.trim().length() < 1) {
			model.setViewName("main");
			model.addObject("mouseGroupIdError",
					"Oh snap! Group Id must not null!!!!");
		} else {
			model.addObject("mouseGroupId", mouseGroupId);
			model.addObject("mouseGroupNum", 50);
			model.setViewName("groupInfo");
		}
		return model;
	}

	@RequestMapping("/addMouse")
	public ModelAndView addMouse(String mouseGroupId, ModelAndView model) {
		model.setViewName("addMouse");
		return model;
	}
}
