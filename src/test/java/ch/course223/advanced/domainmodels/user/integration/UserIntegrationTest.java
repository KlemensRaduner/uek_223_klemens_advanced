package ch.course223.advanced.domainmodels.user.integration;

import ch.course223.advanced.domainmodels.authority.Authority;
import ch.course223.advanced.domainmodels.role.Role;
import ch.course223.advanced.domainmodels.role.RoleDTO;
import ch.course223.advanced.domainmodels.user.User;
import ch.course223.advanced.domainmodels.user.UserRepository;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@DirtiesContext(classMode = AFTER_EACH_TEST_METHOD)
public class UserIntegrationTest {
    
    /*
    Exercise 4
    Write the remaining 4 Integrationtests to fully cover the basic CRUD logic of the domainmodel User. 
    The already written Test findById_requestUserById_returnsUser() functions as an example. 
    */

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MockMvc mvc;

    @Before
    public void setUp() {
    }

    @Test
    public void findById_requestUserById_returnsUser() throws Exception {
        UUID uuidToBeTestedAgainst = UUID.randomUUID();
        Set<Authority> authoritiesToBeTestedAgainst = Stream.of(new Authority().setName("USER_SEE"), new Authority().setName("USER_CREATE"), new Authority().setName("USER_MODIFY"), new Authority().setName("USER_DELETE")).collect(Collectors.toSet());
        Set<Role> rolesToBeTestedAgainst = Stream.of(new Role().setName("BASIC_USER").setAuthorities(authoritiesToBeTestedAgainst)).collect(Collectors.toSet());
        User userToBeTestedAgainst = new User().setFirstName("John").setLastName("Doe").setEmail("john.doe@noseryoung.ch").setEnabled(true).setPassword(new BCryptPasswordEncoder().encode(uuidToBeTestedAgainst.randomUUID().toString())).setRoles(rolesToBeTestedAgainst);

        userRepository.save(userToBeTestedAgainst);

        Set<AuthorityDTO> authorityDTOSToBeTestedAgainst = Stream.of(new AuthorityDTO().setName("USER_SEE"), new AuthorityDTO().setName("USER_CREATE"), new AuthorityDTO().setName("USER_MODIFY"), new AuthorityDTO().setName("USER_DELETE")).collect(Collectors.toSet());
        Set<RoleDTO> roleDTOSToBeTestedAgainst = Stream.of(new RoleDTO().setName("BASIC_USER").setAuthorities(authorityDTOSToBeTestedAgainst)).collect(Collectors.toSet());
        UserDTO userDTOToBeTestedAgainst = new UserDTO(userToBeTestedAgainst.getId()).setFirstName("John").setLastName("Doe").setEmail("john.doe@noseryoung.ch").setRoles(roleDTOSToBeTestedAgainst);

        mvc.perform(
                MockMvcRequestBuilders.get("/users/{id}", userDTOToBeTestedAgainst.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(userDTOToBeTestedAgainst.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value(userDTOToBeTestedAgainst.getFirstName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value(userDTOToBeTestedAgainst.getLastName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(userDTOToBeTestedAgainst.getEmail()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.roles[*].name").value(Matchers.containsInAnyOrder(userDTOToBeTestedAgainst.getRoles().stream().map(RoleDTO::getName).toArray())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.roles[*].authorities[*].name").value(Matchers.containsInAnyOrder(userDTOToBeTestedAgainst.getRoles().stream().map(RoleDTO::getAuthorities).flatMap(Collection::stream).map(AuthorityDTO::getName).toArray())));
    }

    @Test
    public void findAll_requestAllUsers_returnsUsers() throws Exception {
        UUID uuidToBeTestedAgainst = UUID.randomUUID();
        User userToBeTestedAgainst1 = new User().setFirstName("John").setLastName("Doe").setEmail("john.doe@noseryoung.ch").setEnabled(true).setPassword(new BCryptPasswordEncoder().encode(uuidToBeTestedAgainst.randomUUID().toString()));
        User userToBeTestedAgainst2 = new User().setFirstName("Max").setLastName("Muster").setEmail("max.muster@noseryoung.ch").setEnabled(true).setPassword(new BCryptPasswordEncoder().encode(uuidToBeTestedAgainst.randomUUID().toString()));

        userRepository.save(userToBeTestedAgainst1);
        userRepository.save(userToBeTestedAgainst2);

        UserDTO userDTOToBeTestedAgainst1 = new UserDTO(userToBeTestedAgainst1.getId()).setFirstName("John").setLastName("Doe").setEmail("john.doe@noseryoung.ch");
        UserDTO userDTOToBeTestedAgainst2 = new UserDTO(userToBeTestedAgainst2.getId()).setFirstName("Max").setLastName("Muster").setEmail("max.muster@noseryoung.ch");
        List<UserDTO> listOfUserDTOSToBeTestedAgainst = Arrays.asList(userDTOToBeTestedAgainst1,userDTOToBeTestedAgainst2);


        mvc.perform(
                MockMvcRequestBuilders.get("/users")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.*", isA(ArrayList.class)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.*", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].firstName").value(containsInAnyOrder(listOfUserDTOSToBeTestedAgainst.stream().map(UserDTO::getFirstName).toArray())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].lastName").value(containsInAnyOrder(listOfUserDTOSToBeTestedAgainst.stream().map(UserDTO::getLastName).toArray())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].email").value(containsInAnyOrder(listOfUserDTOSToBeTestedAgainst.stream().map(UserDTO::getEmail).toArray())));

    }
}


