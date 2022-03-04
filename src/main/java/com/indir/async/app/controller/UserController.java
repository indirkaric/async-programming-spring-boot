package com.indir.async.app.controller;

import com.indir.async.app.dto.UserDto;
import com.indir.async.app.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/csv")
    @ApiOperation(value = "Import users with csv file")
    public ResponseEntity<Void> saveUsers(@Valid @RequestBody MultipartFile file) throws IOException {
        userService.importUsersFromCsv(file);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(produces = "application/json")
    @ApiOperation(value = "Get all users")
    public CompletableFuture<ResponseEntity> getUsers() {
        return  userService.getUsers().thenApply(ResponseEntity::ok);
    }
}
