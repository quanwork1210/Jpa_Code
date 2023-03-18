package com.jpa.jpaCode.Service.ServiceImpl;

import com.jpa.jpaCode.Dto.UserDto;
import com.jpa.jpaCode.Entity.User;
import com.jpa.jpaCode.model.PagingPattens;

import java.util.List;

public interface UserService {

    UserDto convertEntityToDto(User user);

    List<UserDto> findAll(PagingPattens pagingPattens);
}
