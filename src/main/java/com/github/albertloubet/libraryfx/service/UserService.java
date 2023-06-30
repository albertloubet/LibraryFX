package com.github.albertloubet.libraryfx.service;

import com.github.albertloubet.libraryfx.enumerator.FileEnum;
import com.github.albertloubet.libraryfx.model.dto.UserRememberDTO;
import com.github.albertloubet.libraryfx.repository.UserRepository;
import com.github.albertloubet.libraryfx.util.SessionUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import lombok.SneakyThrows;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.Executors;

public class UserService {

    private UserRepository userRepository;

    public UserService() {
        userRepository = new UserRepository();
    }

    public void authenticate(String username, String password) {
        var user = userRepository.findUser(username, password);
        SessionUtil.setUserLogged(user);
    }

    @SneakyThrows
    public void recoverUserSession() {
        var rememberFile = new File(FileEnum.REMEMBER.getName());

        if (rememberFile.exists()) {
            var reader = new JsonReader(new FileReader(FileEnum.REMEMBER.getName()));
            var userRememberDTO = (UserRememberDTO) new Gson().fromJson(reader, new TypeToken<UserRememberDTO>() { }.getType());
            authenticate(userRememberDTO.getUsername(), userRememberDTO.getPassword());
        }
    }

    public void saveUserSession(String password) {
        Executors.newSingleThreadExecutor().execute(() -> {
            var user = UserRememberDTO.builder()
                    .username(SessionUtil.getUsernameLogged())
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
