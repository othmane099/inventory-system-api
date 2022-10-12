package com.ombdev.inventorysystemapi.service;

import com.ombdev.inventorysystemapi.model.User;
import com.ombdev.inventorysystemapi.repository.UserRepository;
import com.ombdev.inventorysystemapi.request.DeleteRequest;
import com.ombdev.inventorysystemapi.request.ShowRequest;
import com.ombdev.inventorysystemapi.request.user.CreateUserRequest;
import com.ombdev.inventorysystemapi.request.user.UpdateUserRequest;
import com.ombdev.inventorysystemapi.response.user.CreateUserResponse;
import com.ombdev.inventorysystemapi.response.DeleteResponse;
import com.ombdev.inventorysystemapi.response.user.UserResponse;
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

    public DeleteResponse delete(DeleteRequest request) {
        userRepository.deleteById(request.id());
        return new DeleteResponse("User deleted successfully :)");
    }

    public UserResponse showUser(ShowRequest request) {
        User user = userRepository.findById(request.id()).get();
        return User.toUserResponse(user);
    }
}
