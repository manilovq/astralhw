package ru.example.astralhw.service.domain;

import ru.example.astralhw.domain.User;

public interface UserService {

    void save(User user);
    User findByUsername(String username);
    User addNoteToUser(String username, String noteText);
}
