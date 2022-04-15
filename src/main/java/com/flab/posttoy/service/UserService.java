package com.flab.posttoy.service;

import com.flab.posttoy.domain.Post;
import com.flab.posttoy.domain.User;
import com.flab.posttoy.exception.NotFoundUserException;
import com.flab.posttoy.repository.post.PostRepository;
import com.flab.posttoy.repository.user.UserMemoryRepository;
import com.flab.posttoy.repository.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public UserService(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    /**
     * 회원가입
     */
    public User signUp(User user){
        return userRepository.save(user);
    }

    /**
     * 로그인
     */
    public long signIn(String username) {
        User user = userRepository.findByName(username).orElseThrow(()-> new NotFoundUserException("회원 정보가 존재하지 않습니다."));
        return user.getId();
    }

    /**
     * 본인 게시글 조회
     */
    public List<Post> getMyPostList(){
        return new ArrayList<Post>();
    }

}
