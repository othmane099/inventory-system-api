package com.ombdev.inventorysystemapi.controller;

import com.ombdev.inventorysystemapi.model.SortBy;
import com.ombdev.inventorysystemapi.request.DeleteRequest;
import com.ombdev.inventorysystemapi.request.ShowRequest;
import com.ombdev.inventorysystemapi.request.user.UserRequest;
import com.ombdev.inventorysystemapi.response.DeleteResponse;
import com.ombdev.inventorysystemapi.response.product.ProductResponse;
import com.ombdev.inventorysystemapi.response.user.UserResponse;
import com.ombdev.inventorysystemapi.service.UserService;
import com.ombdev.inventorysystemapi.utils.Constants;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(Constants.CLIENT_BASE_URL)
public class UserController {

    private final UserService userService;

    @GetMapping("/admin/users")
    public Page<UserResponse> index(@RequestParam String keyword,
                                    @RequestParam int page,
                                    @RequestParam int size,
                                    @RequestParam SortBy sortBy){
        return userService.index(keyword, page, size, sortBy);
    }

    @GetMapping("/admin/users/show")
    public UserResponse show(@RequestBody ShowRequest request){
        return userService.show(request.id());
    }

    @PostMapping("/admin/users/store")
    public UserResponse store(@RequestBody UserRequest request){
        return userService.store(UserRequest.toEntity(request));
    }

    @PutMapping("/admin/users/update")
    public UserResponse update(@RequestBody UserRequest request){
        return userService.update(UserRequest.toEntity(request));
    }

}
