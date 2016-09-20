package cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cinema.model.Comment;

public interface CommentRepository extends JpaRepository<Comment,Integer> {

}
