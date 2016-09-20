package cinema.service;

import cinema.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;

import cinema.repository.CommentRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

	CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        super();
        this.commentRepository = commentRepository;
    }

    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    public Comment findOne(Integer id) {
        return commentRepository.findOne(id);
    }

    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    public void delete(Integer id) {
    	commentRepository.delete(id);
    }

}
