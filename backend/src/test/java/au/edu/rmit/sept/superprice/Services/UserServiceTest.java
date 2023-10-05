package au.edu.rmit.sept.superprice.Services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.AdditionalAnswers.returnsFirstArg;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import au.edu.rmit.sept.superprice.model.User;
import au.edu.rmit.sept.superprice.repository.UserRepository;
import au.edu.rmit.sept.superprice.service.UserService;

@SpringBootTest
public class UserServiceTest {
    
    UserRepository userRepository;
    UserService userService;

    @BeforeEach
    void init() {
        this.userRepository = mock(UserRepository.class);
        this.userService = new UserService(this.userRepository);
    }

    @Test
    void should_return_all_users() {
        when(this.userRepository.findAll())
            .thenReturn(List.of(new User()));
        
        assertEquals(1, this.userService.getAllUsers().size());
    }

    @Test
    void should_return_empty_list_when_no_users_exist() {
        when(this.userRepository.findAll())
            .thenReturn(new ArrayList<User>());
        
        assertEquals(0, this.userService.getAllUsers().size());
    }

    @Test
    void should_return_user_by_id() {
        when(this.userRepository.findByUserId(1l))
            .thenReturn(new User());
        
        assertNotNull(this.userService.getUserById(1l));
    }

    @Test
    void should_return_null_when_no_user_with_id_exists() {
        when(this.userRepository.findByUserId(11l))
            .thenReturn(null);
        
        assertNull(this.userService.getUserById(11l));
    }

    @Test
    void should_save_or_update_user() {
        User user = new User();
        when(this.userRepository.save(user))
            .then(returnsFirstArg());

        assertEquals(user, this.userService.saveOrUpdateUser(user));
    }

    @Test
    void should_delete_user() {
        this.userService.deleteUser(1l);;

        verify(this.userRepository, times(1))
            .deleteById(1l);
    }

    @Test
    void should_return_users_by_UserName() {
        when(this.userRepository.findByUsername("test"))
            .thenReturn(new User());
        
        assertNotNull(this.userService.getUsersByUsername("test"));
    }

    @Test
    void should_return_empty_list_when_no_user_exists_with_userName() {
        when(this.userRepository.findByUsername("test"))
            .thenReturn(null);
        
        assertNull(this.userService.getUsersByUsername("test"));
    }

    @Test
    void should_return_users_by_Email() {
        when(this.userRepository.findByEmail("test"))
            .thenReturn(new User());
        
        assertNotNull(this.userService.getUsersByEmail("test"));
    }

    @Test
    void should_return_empty_list_when_no_user_exists_with_Email() {
        when(this.userRepository.findByEmail("test"))
            .thenReturn(null);
        
        assertNull(this.userService.getUsersByEmail("test"));
    }

    @Test
    void should_return_users_by_FirstName() {
        when(this.userRepository.findByFirstName("test"))
            .thenReturn(List.of(new User()));
        
        assertEquals(1, this.userService.getUsersByFirstName("test").size());
    }

    @Test
    void should_return_empty_list_when_no_user_exists_with_FirstName() {
        when(this.userRepository.findByFirstName("test"))
            .thenReturn(new ArrayList<User>());
        
        assertEquals(0, this.userService.getUsersByFirstName("test").size());
    }

    @Test
    void should_return_users_by_LastName() {
        when(this.userRepository.findByLastName("test"))
            .thenReturn(List.of(new User()));
        
        assertEquals(1, this.userService.getUsersByLastName("test").size());
    }

    @Test
    void should_return_empty_list_when_no_user_exists_with_LastName() {
        when(this.userRepository.findByLastName("test"))
            .thenReturn(new ArrayList<User>());
        
        assertEquals(0, this.userService.getUsersByLastName("test").size());
    }

    @Test
    void should_return_users_by_Phone() {
        when(this.userRepository.findByPhone("test"))
            .thenReturn(List.of(new User()));
        
        assertEquals(1, this.userService.getUsersByPhone("test").size());
    }

    @Test
    void should_return_empty_list_when_no_user_exists_with_Phone() {
        when(this.userRepository.findByPhone("test"))
            .thenReturn(new ArrayList<User>());
        
        assertEquals(0, this.userService.getUsersByPhone("test").size());
    }

    @Test
    void should_return_users_by_AddressId() {
        when(this.userRepository.findByAddressId(1l))
            .thenReturn(List.of(new User()));
        
        assertEquals(1, this.userService.getUsersByAddressId(1l).size());
    }

    @Test
    void should_return_empty_list_when_no_user_exists_with_AddressId() {
        when(this.userRepository.findByAddressId(1l))
            .thenReturn(new ArrayList<User>());
        
        assertEquals(0, this.userService.getUsersByAddressId(1l).size());
    }

    @Test
    void should_return_UserDetails_object() {
        when(this.userRepository.findByEmail("email"))
            .thenReturn(new User(11l,"test","test","email", null, null, null, null));
        
        assertNotNull(this.userService.loadUserByUsername("email"));
    }
}
