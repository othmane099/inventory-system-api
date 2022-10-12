package com.ombdev.inventorysystemapi.controller;

import com.ombdev.inventorysystemapi.request.DeleteRequest;
import com.ombdev.inventorysystemapi.request.ShowRequest;
import com.ombdev.inventorysystemapi.request.user.UserRequest;
import com.ombdev.inventorysystemapi.response.DeleteResponse;
import com.ombdev.inventorysystemapi.response.user.UserResponse;
import com.ombdev.inventorysystemapi.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/admin/users")
    public List<UserResponse> index(){
        return userService.index();
    }

    @GetMapping("/admin/users/show")
    public UserResponse show(@RequestBody ShowRequest request){
        return userService.show(request.id());
    }

    @PostMapping("/admin/users/create")
    public UserResponse create(@RequestBody UserRequest request){
        return userService.create(UserRequest.toEntity(request));
    }

    @PutMapping("/admin/users/update")
    public UserResponse update(@RequestBody UserRequest request){
        return userService.update(UserRequest.toEntity(request));
    }

    @DeleteMapping("/admin/users/delete")
    public DeleteResponse delete(@RequestBody DeleteRequest request){
        return userService.delete(request.id());
    }

}
