package org.Group34.controller.menu;

import org.Group34.model.App;
import org.Group34.model.Result;
import org.Group34.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

public class ProfileMenuControllerTest {
    private ProfileMenuController controller;
    private User fakeUser;

    @BeforeEach
    void setUp() {
        controller = new ProfileMenuController();
        fakeUser = mock(User.class);
        when(fakeUser.getUsername()).thenReturn("fakeUser");
        when(fakeUser.getPassword()).thenReturn("password");
        when(fakeUser.getEmail()).thenReturn("fake@example.com");
        when(fakeUser.getNickname()).thenReturn("fakeNick");
    }

    @Test
    void changeUsername_Success_WhenValidNewUsername() {
        List<User> users = List.of(fakeUser);

        try (MockedStatic<App> mockedApp = mockStatic(App.class)) {
            mockedApp.when(App::getCurrentUser).thenReturn(fakeUser);
            mockedApp.when(App::getUsers).thenReturn(users);

            Result result = controller.changeUsername("newUser", fakeUser);

            assertTrue(result.success());
            assertEquals("Username was changed successfully.", result.message());
            verify(fakeUser).setUsername("newUser");
        }
    }

    @Test
    void changeUsername_Fail_WhenUsernameAlreadyTaken() {
        User anotherUser = mock(User.class);
        when(anotherUser.getUsername()).thenReturn("takenUser");

        List<User> users = List.of(fakeUser, anotherUser);

        try (MockedStatic<App> mockedApp = mockStatic(App.class)) {
            mockedApp.when(App::getCurrentUser).thenReturn(fakeUser);
            mockedApp.when(App::getUsers).thenReturn(users);

            Result result = controller.changeUsername("takenUser", fakeUser);

            assertFalse(result.success());
            assertEquals("Username is already taken.", result.message());
        }
    }

    @Test
    void changeUsername_Fail_WhenUsernameIsSame() {
        List<User> users = List.of(fakeUser);

        try (MockedStatic<App> mockedApp = mockStatic(App.class)) {
            mockedApp.when(App::getCurrentUser).thenReturn(fakeUser);
            mockedApp.when(App::getUsers).thenReturn(users);

            Result result = controller.changeUsername("fakeUser", fakeUser);

            assertFalse(result.success());
            assertEquals("This username is already your current username.", result.message());
        }
    }

    @Test
    void changePassword_Success_WhenValidNewPassword() {
        List<User> users = List.of(fakeUser);

        try (MockedStatic<App> mockedApp = mockStatic(App.class)) {
            mockedApp.when(App::getCurrentUser).thenReturn(fakeUser);
            mockedApp.when(App::getUsers).thenReturn(users);

            Result result = controller.changePassword("New1234!", "password", fakeUser);

            assertTrue(result.success());
            assertEquals("Password was changed successfully.", result.message());
            verify(fakeUser).setPassword("New1234!");
        }
    }

    @Test
    void changeNickname_Success_WhenNewNickname() {
        when(fakeUser.getNickname()).thenReturn("oldNick");

        Result result = controller.changeNickname("newNick", fakeUser);

        assertTrue(result.success());
        assertEquals("Nickname was changed successfully.", result.message());
        verify(fakeUser).setNickname("newNick");
    }

    @Test
    void changeEmail_Success_WhenValidNewEmail() {
        List<User> users = List.of(fakeUser);

        try (MockedStatic<App> mockedApp = mockStatic(App.class)) {
            mockedApp.when(App::getCurrentUser).thenReturn(fakeUser);
            mockedApp.when(App::getUsers).thenReturn(users);

            Result result = controller.changeEmail("new@example.com", fakeUser);

            assertTrue(result.success());
            assertEquals("Email was changed successfully.", result.message());
            verify(fakeUser).setEmail("new@example.com");
        }
    }

    @Test
    void showUserInfo_ReturnsFormattedUserData() {
        when(fakeUser.getHighestMoney()).thenReturn(1000);
        when(fakeUser.getPlayedGamesCount()).thenReturn(25);

        Result result = controller.showUserInfo(fakeUser);

        String expected = "fakeUser\nfakeNick\n1000\n25";
        assertTrue(result.success());
        assertEquals(expected, result.message());
    }
}
