package ru.example.astralhw.service.domain;

import ru.example.astralhw.domain.Note;
import ru.example.astralhw.repository.domain.RoleRepository;
import ru.example.astralhw.repository.domain.UserRepository;
import ru.example.astralhw.domain.Role;
import ru.example.astralhw.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        System.out.println("was: " + user.getPassword());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        System.out.println("became: " + user.getPassword());
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.getOne(1L));
        user.setRoles(roles);
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User addNoteToUser(String username, String noteText) {
        User user = findByUsername(username);
        Note note = new Note(noteText);
        List<Note> userNotes = user.getNotes();
        userNotes.add(note);
        return userRepository.save(user);
    }
}
