package com.jpa.jpaCode.Service.ServiceImpl;

import com.jpa.jpaCode.Dto.UserDto;
import com.jpa.jpaCode.Entity.User;
import com.jpa.jpaCode.Repository.UserRepository;
import com.jpa.jpaCode.model.PagingPattens;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDto convertEntityToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public List<UserDto> findAll(PagingPattens paging) {
        Pageable pageReq
                = PageRequest.of(paging.getIndex(), paging.getSize(), Sort.by(paging.getSortOrders()));
        var pageUser = userRepository.findAll(pageReq);
        return pageUser.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }
}
