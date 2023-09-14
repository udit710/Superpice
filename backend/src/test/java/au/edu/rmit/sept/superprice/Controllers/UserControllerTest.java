package au.edu.rmit.sept.superprice.Controllers;

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

import au.edu.rmit.sept.superprice.service.UserService;
import au.edu.rmit.sept.superprice.web.UserController;

@SpringBootTest
public class UserControllerTest{
        
        UserController userController;
        UserService userService;
    
        @BeforeEach
        void initializeObjects() {
            this.userService = mock(UserService.class);
            this.userController = new UserController(this.userService);
        }
    
        @Test
        void should_return_all_users() {
            when(this.userService.getAllUsers())
                .thenReturn(List.of(new User(1l, "test", "test", "test", "test", "test", "test", 2l),
                                    new User(2l, "test", "test", "test", "test", "test", "test", 3l)));
    
            assertEquals(2, this.userController.getAllUsers().size());
        }
    
        @Test
        void should_return_empty_list_when_no_users() {
            when(this.userService.getAllUsers()).thenReturn(new ArrayList<User>());
    
            assertEquals(0, this.userController.getAllUsers().size());
        }
    
        @Test
        void should_return_user_for_a_single_user_id() {
            when(this.userService.getUserById(1l))
                .thenReturn(
                    new User(1l, "test", "test", "test", "test", "test", "test", 2l)
                );
            
            assertEquals(new User(1l, "test", "test", "test", "test", "test", "test", 2l), this.userController.getUserById(1l));
        }

        @Test
        void should_return_null_when_user_not_found() {
            when(this.userService.getUserById(1l)).thenReturn(null);
    
            assertNull(this.userController.getUserById(1l));
        }

        @Test
        void should_return_user_for_a_single_user_name() {
            User user = new User(1l, "test", "test", "test", "test", "test", "test", 2l);

            when(this.userService.getUsersByUsername("test"))
                .thenReturn(
                    List.of(user));
            
            assertEquals(user, this.userController.getUsersByUsername("test").get(0));
        }

        @Test
        void should_return_null_when_user_name_not_found() {
            when(this.userService.getUsersByUsername("test")).thenReturn(null);
    
            assertNull(this.userController.getUsersByUsername("test"));
        }

        @Test
        void should_return_user_for_a_single_user_email() {
            User user = new User(1l, "test", "test", "test", "test", "test", "test", 2l);

            when(this.userService.getUsersByEmail("test"))
                .thenReturn(
                    List.of(user));
            
            assertEquals(user, this.userController.getUsersByEmail("test").get(0));
        }

        @Test
        void should_return_null_when_user_email_not_found() {
            when(this.userService.getUsersByEmail("test")).thenReturn(null);
    
            assertNull(this.userController.getUsersByEmail("test"));
        }

        @Test
        void should_return_user_for_a_single_user_first_name() {
            User user = new User(1l, "test", "test", "test", "test", "test", "test", 2l);

            when(this.userService.getUsersByFirstName("test"))
                .thenReturn(
                    List.of(user));
            
            assertEquals(user, this.userController.getUsersByFirstName("test").get(0));
        }

        @Test
        void should_return_null_when_user_first_name_not_found() {
            when(this.userService.getUsersByFirstName("test")).thenReturn(null);
    
            assertNull(this.userController.getUsersByFirstName("test"));
        }

        @Test
        void should_return_user_for_a_single_user_last_name() {
            User user = new User(1l, "test", "test", "test", "test", "test", "test", 2l);

            when(this.userService.getUsersByLastName("test"))
                .thenReturn(
                    List.of(user));
            
            assertEquals(user, this.userController.getUsersByLastName("test").get(0));
        }

        @Test
        void should_return_null_when_user_last_name_not_found() {
            when(this.userService.getUsersByLastName("test")).thenReturn(null);
    
            assertNull(this.userController.getUsersByLastName("test"));
        }

        @Test
        void should_return_user_for_a_single_user_phone() {
            User user = new User(1l, "test", "test", "test", "test", "test", "test", 2l);

            when(this.userService.getUsersByPhone("test"))
                .thenReturn(
                    List.of(user));
            
            assertEquals(user, this.userController.getUsersByPhone("test").get(0));
        }

        @Test
        void should_return_null_when_user_phone_not_found() {
            when(this.userService.getUsersByPhone("test")).thenReturn(null);
    
            assertNull(this.userController.getUsersByPhone("test"));
        }

        @Test
        void should_return_user_for_a_single_user_address_id() {
            User user = new User(1l, "test", "test", "test", "test", "test", "test", 2l);

            when(this.userService.getUsersByAddressId(2l))
                .thenReturn(
                    List.of(user));
            
            assertEquals(user, this.userController.getUsersByAddressId(2l).get(0));
        }

        @Test
        void should_return_null_when_user_address_id_not_found() {
            when(this.userService.getUsersByAddressId(2l)).thenReturn(null);
    
            assertNull(this.userController.getUsersByAddressId(2l));
        }

        @Test
        void should_create_user() {
            User user = new User(1l, "test", "test", "test", "test", "test", "test", 2l);

            when(this.userService.saveOrUpdateUser(user)).then(returnsFirstArg());
    
            assertEquals(user, this.userController.createUser(user));
        }

        @Test
        void should_update_user() {
            User user = new User(1l, "test", "test", "test", "test", "test", "test", 2l);

            when(this.userService.saveOrUpdateUser(user)).then(returnsFirstArg());
    
            assertEquals(user, this.userController.updateUser(1l, user));
        }

        @Test
        void should_delete_user() {
            this.userController.deleteUser(1l);
    
            verify(this.userService, times(1)).deleteUser(1l);
        }
        
                

}