package com.indir.async.app.service;

import com.indir.async.app.annotation.LogExecutionTime;
import com.indir.async.app.dto.UserDto;
import com.indir.async.app.entity.User;
import com.indir.async.app.exception.error.BadRequestException;
import com.indir.async.app.exception.error.RestApiError;
import com.indir.async.app.mapper.UserMapper;
import com.indir.async.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private String HEADERS[] = {"firstName", "lastName", "email"};

    @LogExecutionTime
    public void importUsersFromCsv(MultipartFile file) throws IOException {
        log.info("Running on thread {}", Thread.currentThread().getName());
        if (!file.getOriginalFilename().contains(".csv"))
            throw new BadRequestException(RestApiError.INVALID_FILE_EXTENSION);

        final BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
        insertUsers(reader);
    }

    @LogExecutionTime
    @Async("taskExecutor2")
    public CompletableFuture<List<UserDto>> getUsers() {
        log.info("Running on thread {}", Thread.currentThread().getName());
        List<UserDto> userDtos = userRepository.findAll().stream().map(UserMapper::toDto).collect(Collectors.toList());
        return CompletableFuture.completedFuture(userDtos);
    }

    @Async("taskExecutor1")
    void insertUsers(BufferedReader reader) throws IOException {
        log.info("Running on thread {}", Thread.currentThread().getName());
        List<User> users = new ArrayList<>();
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader(HEADERS).parse(reader);

        for (CSVRecord record : records) {
            User user = new User();
            user.setEmail(record.get("email"));
            user.setFirstName(record.get("firstName"));
            user.setLastName(record.get("lastName"));
            users.add(user);
        }
        userRepository.saveAll(users);
    }

}
