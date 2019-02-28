package sampleApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sampleApp.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

	public List<Post> findByUserId(int userId);

}
