package cinema.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cinema.model.Comment;
import cinema.model.Section;
import cinema.model.Topic;
import cinema.service.CommentService;
import cinema.service.SectionService;
import cinema.service.TopicService;

@Controller
@RequestMapping("/forum")
public class ForumController {

	SectionService sectionService;
	
	TopicService topicService;
    
	CommentService commentService;
    
    @Autowired
    public ForumController(SectionService sectionService, TopicService topicService, CommentService commentService) {
        this.sectionService = sectionService;
        this.topicService = topicService;
        this.commentService = commentService;
    }
    
    @RequestMapping
    public String home(Model model, Authentication authentication, Principal principal) {
    	model.addAttribute("sections", sectionService.findAll());
        return "forumHome";
    }
    
    @RequestMapping("/addSection")
    public String addSection(Model model, Authentication authentication, Principal principal) {
        model.addAttribute("section", new Section());
        return "/forum/addSection";
    }
    
    @PostMapping(value = "/createSection")
    public String create(@ModelAttribute("section") @Valid Section section, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "forum/addSection";
        }
        sectionService.save(section);
        return "redirect:/forum";
    }
    
    @PostMapping(value = "/createTopic")
    public String create(@ModelAttribute @Valid Topic topic, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/forum/addTopic";
        }
       
        
        topicService.save(topic);
        return "redirect:/forum";
    }
   
    @RequestMapping("/addTopic/{id}")
    public String addTopic(@PathVariable Integer id, Model model) {
    	Topic topic =  new Topic();
    	
    	topic.setSection(sectionService.findOne(id));
    	
    	model.addAttribute("section", sectionService.findOne(id));
    	model.addAttribute("topic", topic);
        return "/forum/addTopic";
    }
    
    @RequestMapping("/topic/{id}")
    public String topic(@PathVariable Integer id, Model model) {
    	model.addAttribute("topic", topicService.findOne(id));
    	model.addAttribute("comment", new Comment());
        return "/forum/topic";
    }
    
    @RequestMapping("/topic/{id}/createComment")
    public String topic(@PathVariable Integer id, @ModelAttribute @Valid Comment comment, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
        	commentService.save(comment);
        }

        return "/topic/" + id.toString();
    }
}
