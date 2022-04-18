package com.flab.posttoy.service;

import com.flab.posttoy.domain.Post;
import com.flab.posttoy.domain.User;
import com.flab.posttoy.exception.DuplicatedUserException;
import com.flab.posttoy.exception.NotFoundUserException;
import com.flab.posttoy.repository.post.PostRepository;
import com.flab.posttoy.repository.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        validDuplicatedUser(user);
        return userRepository.save(user);
    }

    /**
     * 로그인
     */
    public long signIn(String username) {
        User user = userRepository.findByName(username).orElseThrow(()-> new NotFoundUserException("회원 정보가 존재하지 않습니다."));
        return user.getId();
    }

    private void validDuplicatedUser(User user){
        userRepository.findByName(user.getUsername()).ifPresent(u->{
            throw new DuplicatedUserException("이미 존재하는 회원입니다.");
        });
    }

}
