package com.jpa.jpaCode.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jpa.jpaCode.Constants.Constants;
import com.jpa.jpaCode.Dto.UserDto;
import com.jpa.jpaCode.Entity.User;
import com.jpa.jpaCode.Repository.UserRepository;
import com.jpa.jpaCode.Service.ServiceImpl.UserService;
import com.jpa.jpaCode.model.GeneralApiResponse;
import com.jpa.jpaCode.model.PagingPattens;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RequestMapping("/users")
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;


    @GetMapping("/{id}")
    public GeneralApiResponse<Object> findById(@PathVariable Long id) {
        var userOtp = userRepository.findById(id);
        if (userOtp.isPresent()) {
            return new GeneralApiResponse<>(HttpStatus.OK.value(), Constants.SUCCESS, userOtp.get());
        }
        return new GeneralApiResponse<>(HttpStatus.NOT_FOUND.value(), Constants.FAILED);
    }

    @PostMapping("/search")
    public GeneralApiResponse<Object> findAll(
           @RequestBody PagingPattens paging
    ) {
        return new GeneralApiResponse<>(HttpStatus.OK.value(),
                Constants.SUCCESS, userService.findAll(paging));
    }

//
//    @QueryMapping
//    Iterable<User> findAll() {
//        return userRepository.findAll();
//    }


    @PostMapping
    public GeneralApiResponse<Object> save(@RequestBody UserDto userDto) {
        User user = new User();

        user.setEmail(userDto.getEmail());
        user.setJoined_at(new Date());
        userRepository.save(user);

        return new GeneralApiResponse<>(HttpStatus.OK.value(), Constants.SUCCESS);
    }


}
