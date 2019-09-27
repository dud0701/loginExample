package com.example.login;
import com.example.login.model.User;
import com.example.login.repositories.RoleRepository;
import com.example.login.repositories.UserRepository;
import com.example.login.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;


@RunWith(SpringRunner.class)
//@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Mock
    private UserRepository mockUserRepository;
    @Mock
    private RoleRepository mockRoleRepository;
    @Mock
    private BCryptPasswordEncoder mockBCryptPasswordEncoder;

    private UserService userServiceTest;
    private User user;

    @Before
    public void setup(){
        initMocks(this);
        userServiceTest = new UserService(mockUserRepository, mockRoleRepository, mockBCryptPasswordEncoder);
        user = User.builder()
                .id(1)
                .name("YoungEun")
                .email("teste@test.com")
                .build();

        Mockito.when(mockUserRepository.save(any()))
                .thenReturn(user);
        Mockito.when(mockUserRepository.findByEmail(anyString()))
                .thenReturn(user);
    }

    @Test
    public void testFineUserByEmail(){
        final String email = "test@test.com";
        final User result = userServiceTest.findUserByEmail(email);
        assertEquals(email, result.getEmail());
    }

    @Test
    public void testSaveUser(){
        final String email="test@test.com";
        User result = userServiceTest.saveUser(User.builder().build());
        assertEquals(email, result);
    }

}
