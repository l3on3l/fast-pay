package com.great.fpay.mapper;

import com.great.fpay.dto.UserResponse;
import com.great.fpay.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse toUserResponse(User user);

}
