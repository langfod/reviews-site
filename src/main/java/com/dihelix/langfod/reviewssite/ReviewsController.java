package com.dihelix.langfod.reviewssite;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReviewsController {

	@Resource
	ReviewRepository reviewRepository;

	@RequestMapping("/")
	public String redirect(Model model) {
		return "redirect:/reviews";
	}

	@RequestMapping("/review")
	public String reviews(@RequestParam(value = "id", required = false) Long inputid, Model model) {
		model.addAttribute("id", inputid);
		model.addAttribute("tagList", reviewRepository.findAllTags());
		model.addAttribute("review", reviewRepository.findOne(inputid));
		return "review";
	}

	@RequestMapping("/reviews")
	public String reviews(@RequestParam(value = "reviewtag", required = false) String reviewtag, Model model) {

		model.addAttribute("tagList", reviewRepository.findAllTags());

		if (reviewtag != null) {
			model.addAttribute("selectedTag", reviewtag);
			model.addAttribute("reviews", reviewRepository.findByTag(reviewtag));
		} else {
			model.addAttribute("reviews", reviewRepository.findAll());
		}
		return "reviews";
	}
}