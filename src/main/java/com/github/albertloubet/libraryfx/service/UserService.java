package com.github.albertloubet.libraryfx.service;

import com.github.albertloubet.libraryfx.enumerator.FileEnum;
import com.github.albertloubet.libraryfx.manager.SessionManager;
import com.github.albertloubet.libraryfx.model.dto.UserRemember;
import com.github.albertloubet.libraryfx.repository.UserRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import lombok.SneakyThrows;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.Executors;

public class UserService {

    private UserRepository userRepository;

    public UserService() {
        userRepository = new UserRepository();
    }

    public void authenticate(String username, String password) {
        var user = userRepository.findUser(username, password);
        SessionManager.setUserLogged(user);
    }

    @SneakyThrows
    public void recoverUserSession() {
        var rememberFile = new File(FileEnum.REMEMBER.getName());

        if (rememberFile.exists()) {
            var reader = new JsonReader(new FileReader(FileEnum.REMEMBER.getName()));
            var userRemember = (UserRemember) new Gson().fromJson(reader, new TypeToken<UserRemember>() { }.getType());
            authenticate(userRemember.username(), userRemember.password());
        }
    }

    public void saveUserSession(String password) {
        Executors.newSingleThreadExecutor().execute(() -> {
            var user = UserRemember.builder()
                    .username(SessionManager.getUsernameLogged())
                    .password(password)
                    .build();
            try (var writer = new FileWriter(FileEnum.REMEMBER.getName())) {
                var gson = new GsonBuilder().create();
                gson.toJson(user, writer);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
