package com.user.core.usecase.create;

import com.user.core.entity.UserModel;
import com.user.core.exception.UserException;
import com.user.core.gateway.UserGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CreateUserUseCaseTest {

    @Mock
    private CreateUserRequest request;

    @InjectMocks
    private CreateUserUseCase useCase;

    @Mock
    private UserGateway userGateway;

    @Mock
    private CreateUserPresenter presenter;

    @Mock
    private UserModel userModel;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void givenNotNullUserModel_whenCreate_shouldThrowException(){
        stubUserExist();
        Executable task = ()->useCase.create(request,presenter);
        Exception e = assertThrows(UserException.class, task);
        assertThat(e.getMessage()).isEqualTo("User has been registered");
    }

    @Test
    void givenUserModel_whenCreate_shouldCallUserGateway() {
        prepareAndExecute();
        verify(userGateway).create(userModel);
    }

    @Test
    void givenUserModel_whenCreate_shouldCallPresenter() {
        prepareAndExecute();
        verify(presenter).present(any());
    }


    @Test
    void givenUserModel_whenCreate_shouldCallPresenterWithCorrectResponse() {
        prepareAndExecute();
        ArgumentCaptor<UserModel> captor = ArgumentCaptor.forClass(UserModel.class);
        verify(presenter).present(captor.capture());
        UserModel actual = captor.getValue();

        assertThat(actual).isNotNull();
    }

    private void prepareAndExecute() {
        stubUser();
        useCase.create(request, presenter);
    }

    private void stubUser() {
        when(userModel.getId()).thenReturn(1L);
        when(userModel.getName()).thenReturn("dummy");
        when(userModel.getEmail()).thenReturn("dummy@dummy.com");
        when(userModel.getPassword()).thenReturn("password");
        when(request.toEntity()).thenReturn(userModel);
        when(userGateway.create(any())).thenReturn(userModel);
    }

    private void stubUserExist(){
        when(request.getEmail()).thenReturn("dummy@dummy.com");
        when(userGateway.findByEmail(any())).thenReturn(Optional.of(userModel));
    }

}