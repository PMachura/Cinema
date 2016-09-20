package cinema.controller;

import java.security.Principal;
import java.util.Date;
import java.util.List;

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
import cinema.model.User;
import cinema.service.CommentService;
import cinema.service.SectionService;
import cinema.service.TopicService;
import cinema.service.UserService;

@Controller
@RequestMapping("/forum")
public class ForumController {

	SectionService sectionService;
	
	TopicService topicService;
    
	CommentService commentService;
    
	UserService userService;
	
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
    
    @RequestMapping("/deleteSection/{id}")
    public String deleteSection(@PathVariable Integer id, Model model, Authentication authentication, Principal principal) {
    	Section section = sectionService.findOne(id);
    	for(Topic topic : section.getTopics()){
        	for(Comment comment : topic.getComments()){
        		commentService.delete(comment.getId());
        	}
    		topicService.delete(topic.getId());
    	}
    	sectionService.delete(id);
    	System.out.println(id);
    	model.addAttribute("sections", sectionService.findAll());
        return "forumHome";
    }
    
    @RequestMapping("/deleteTopic/{id}")
    public String deleteTopic(@PathVariable Integer id, Model model, Authentication authentication, Principal principal) {
    	Topic topic = topicService.findOne(id);
        for(Comment comment : topic.getComments()){
        	commentService.delete(comment.getId());
        }
    	topicService.delete(topic.getId());

    	model.addAttribute("sections", sectionService.findAll());
        return "forumHome";
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
    public String create(@ModelAttribute @Valid Topic topic, BindingResult bindingResult, Authentication authentication) {
        if (bindingResult.hasErrors()) {
            return "/forum/addTopic";
        }
       
        topic.setCreateDate(new Date());
        User user =  userService.findByEmail(authentication.getName());
        topic.setUser(user);
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
    
    @PostMapping("/topic/createComment/{id}")
    public String createComment(@PathVariable Integer id, @ModelAttribute @Valid Comment comment, Model model, BindingResult bindingResult, Authentication authentication) {
        if (!bindingResult.hasErrors()) {
        	Topic topic = topicService.findOne(id);
        	List<Comment> comments= topic.getComments();
        	comments.add(comment);
        	comment.setId(null);
        	topic.setComments(comments);
        	comment.setTopic(topic);
        	
            topic.setCreateDate(new Date());
            User user =  userService.findByEmail(authentication.getName());
            topic.setUser(user);
        	topicService.save(topic);
        	
        	comment.setCreateDate(new Date());

            comment.setUser(user);
        	commentService.save(comment);
            
        }

        return "redirect:/forum/topic/" + id;
    }
}
