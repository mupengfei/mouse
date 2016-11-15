package com.mrgan.mouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mrgan.mouse.model.Mouse;
import com.mrgan.mouse.model.dao.MouseDAO;

@Controller
@RequestMapping("/")
public class MouseController {
	@Autowired
	private MouseDAO mouseDAO;

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
			model.addObject("mouseGroupNum",
					mouseDAO.countByGroupId(mouseGroupId));
			model.setViewName("groupInfo");
		}
		return model;
	}

	@RequestMapping("/goAddMouse")
	public ModelAndView goAddMouse(String mouseGroupId, ModelAndView model) {
		model.setViewName("addMouse");
		model.addObject("mouseGroupId", mouseGroupId);
		return model;
	}

	@RequestMapping("/addMouse")
	@ResponseBody
	public String addMouse(Mouse mouse,
			@RequestParam(defaultValue = "false") Boolean forceSave,
			ModelAndView model) {
		List<Mouse> mouses = mouseDAO.findByNoAndGroupId(mouse.getNo(),
				mouse.getGroupId());
		System.out.println(forceSave);
		if (mouses == null || mouses.size() == 0 || forceSave) {
			mouseDAO.delete(mouses);
			mouseDAO.save(mouse);
			return "{\"save\":\"1\"}";
		} else {
			return "{\"save\":\"0\",\"mouse\":{\"no\":\""
					+ mouses.get(0).getNo() + "\",\"tumorVolume\":\""
					+ mouses.get(0).getTumorVolume() + "\"}}";
		}
	}

	@RequestMapping("/goGroupMouse")
	public ModelAndView goGroupMouse(String mouseGroupId, int groupNum,
			ModelAndView model) {
		long count = mouseDAO.countByGroupIdAndGroupNum(mouseGroupId, 0);
		int sumPages = mouseDAO.findGroupNumMaxByGroupId(mouseGroupId);
		if (count != 0 || groupNum != sumPages) {
			System.out.println("Group");
			List<Mouse> mouses = mouseDAO.findByGroupId(mouseGroupId, new Sort(
					Sort.Direction.DESC, "tumorVolume"));
			int numFlag = 1;
			for (int i = 0; i < mouses.size(); i++) {
				if (numFlag > groupNum)
					numFlag = 1;
				if (mouses.get(i).getGroupNum() != numFlag) {
					mouses.get(i).setGroupNum(numFlag);
					mouseDAO.save(mouses.get(i));
				}
				numFlag++;
			}
			System.out.println(mouses);
		}
		model.addObject("mouseGroupId", mouseGroupId);
		model.addObject("page", 1);
		model.setViewName("redirect:/groupPages");
		return model;
	}

	@RequestMapping("/groupPages")
	public ModelAndView groupPages(String mouseGroupId, int page,
			ModelAndView model) {
		List<Mouse> mouses = mouseDAO.findByGroupIdAndGroupNum(mouseGroupId,
				page, new Sort(Sort.Direction.DESC, "tumorVolume"));
		int sumPages = mouseDAO.findGroupNumMaxByGroupId(mouseGroupId);
		model.addObject("mouses", mouses);
		model.addObject("sumPages", sumPages);
		model.addObject("page", page);
		model.addObject("mouseGroupId", mouseGroupId);
		model.setViewName("groupContent");
		return model;
	}
}
