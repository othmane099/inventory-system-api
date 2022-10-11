package com.ombdev.inventorysystemapi.service;

import com.ombdev.inventorysystemapi.model.User;
import com.ombdev.inventorysystemapi.repository.UserRepository;
import com.ombdev.inventorysystemapi.request.CreateUserRequest;
import com.ombdev.inventorysystemapi.request.DeleteUserRequest;
import com.ombdev.inventorysystemapi.request.ShowUserRequest;
import com.ombdev.inventorysystemapi.request.UpdateUserRequest;
import com.ombdev.inventorysystemapi.response.CreateUserResponse;
import com.ombdev.inventorysystemapi.response.DeleteResponse;
import com.ombdev.inventorysystemapi.response.UserResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public CreateUserResponse createUser(CreateUserRequest request){
        User user = CreateUserRequest.toEntity(request);
        user.setStatus(false);
        return User.toCreateUserResponse(userRepository.save(user));
    }

    public List<UserResponse> getUsers(){

        return userRepository.findAll()
                .stream()
                .map(User::toUserResponse)
                .collect(Collectors.toList());
    }

    public UserResponse updateUser(UpdateUserRequest request) {
        User user = UpdateUserRequest.toEntity(request);
        return User.toUserResponse(userRepository.save(user));
    }

    public DeleteResponse delete(DeleteUserRequest request) {
        userRepository.deleteById(request.id());
        return new DeleteResponse("User deleted successfully :)");
    }

    public UserResponse showUser(ShowUserRequest request) {
        User user = userRepository.findById(request.id()).get();
        return User.toUserResponse(user);
    }
}
