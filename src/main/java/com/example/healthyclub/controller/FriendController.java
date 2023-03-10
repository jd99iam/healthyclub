package com.example.healthyclub.controller;

import com.example.healthyclub.entity.UserEntity;
import com.example.healthyclub.error.ErrorDTO;
import com.example.healthyclub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/friend")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin
public class FriendController {

    private final UserRepository userRepository;


    //친구를 추가하기 @Pathvariable의 id는 내가 친구를 추가하려는 사람의 id, @Auth의 identifyId는 나 자신
    @PostMapping("/{id}")
    @Transactional
    public ResponseEntity<?> addFriends(@PathVariable Long id, @AuthenticationPrincipal String identifyId) {
        long longId = Long.parseLong(identifyId);
        List<String> friends = userRepository.getUserById(longId).getFriends();
        String stringId = String.valueOf(id);
        UserEntity userById = userRepository.getUserById(id);
        log.info("{}",userById);
        if(userById == null){
            String message = "추가하려는 친구가 존재하지 않습니다.";
            return ResponseEntity.badRequest().body(new ErrorDTO(message));
        }
        friends.add(stringId);

        return ResponseEntity.ok().body(friends);
    }

    //친구를 삭제하기
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deleteFriends(@PathVariable Long id, @AuthenticationPrincipal String identifyId) {
        long longId = Long.parseLong(identifyId);
        List<String> friends = userRepository.getUserById(longId).getFriends();
        String stringId = String.valueOf(id);
        UserEntity userById = userRepository.getUserById(id);
        log.info("{}",userById);
        if(userById == null){
            String message = "추가하려는 친구가 존재하지 않습니다.";
            return ResponseEntity.badRequest().body(new ErrorDTO(message));
        }
        friends.remove(stringId);

        return ResponseEntity.ok().body(friends);

    }

    //나의 모든 친구 목록들을 보여주기
    @GetMapping("/show")
    @Transactional
    public ResponseEntity<?> deleteFriends(@AuthenticationPrincipal String identifyId) {
        long longId = Long.parseLong(identifyId);
        List<String> friends = userRepository.getUserById(longId).getFriends();

        return ResponseEntity.ok().body(friends);

    }


}