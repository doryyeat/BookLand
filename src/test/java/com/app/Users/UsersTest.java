package com.app.Users;

import com.app.model.Users;
import com.app.model.enums.Role;
import com.app.repo.UsersRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UsersTest {

    @Autowired
    protected UsersRepo usersRepo;

    @Test
    void testUserFindByIdSuccess() {
        Users user = usersRepo.save(new Users("name", "username", "password", Role.CLIENT));

        Users find = usersRepo.findById(user.getId()).orElse(new Users());

        assertEquals(find.getUsername(), user.getUsername());
        assertEquals(find.getRole(), user.getRole());

        usersRepo.deleteById(find.getId());
    }

    @Test
    void testUserFindByIdNotFound() {
        assertNull(usersRepo.findById(0L).orElse(null));
    }

    @Test
    void testUserAddSuccess() {
        Users save = new Users("name", "username", "password", Role.CLIENT);

        Users saved = usersRepo.save(save);

        assertEquals(saved.getUsername(), save.getUsername());
        assertEquals(saved.getRole(), save.getRole());

        usersRepo.deleteById(saved.getId());
    }

    @Test
    void testUserUpdateSuccess() {
        Users old = usersRepo.save(new Users("name", "username", "password", Role.CLIENT));

        Users update = new Users("new username", Role.CLIENT);

        old.update(update);

        Users updated = usersRepo.save(old);

        assertEquals(updated.getUsername(), update.getUsername());
        assertEquals(updated.getRole(), update.getRole());

        usersRepo.deleteById(updated.getId());
    }

    @Test
    void testUserDeleteByIdSuccess() {
        Users user = usersRepo.save(new Users("name", "username", "password", Role.CLIENT));

        Long userId = user.getId();

        assertNotNull(usersRepo.findById(userId).orElse(null));

        usersRepo.deleteById(userId);

        assertNull(usersRepo.findById(userId).orElse(null));
    }
}
