package sampleApp.service;

//import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sampleApp.model.Post;
import sampleApp.repository.PostRepository;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;

	public void create(Post post) {
		postRepository.save(post);
	}

}
