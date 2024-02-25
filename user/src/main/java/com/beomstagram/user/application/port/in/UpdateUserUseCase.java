package com.beomstagram.user.application.port.in;

import com.beomstagram.user.domain.User;

public interface UpdateUserUseCase {
    User updateUserName(UpdateUserNameCommand updateUserNameCommand);
//    User updateUserProfileImage(UpdateUserProfileImageCommand updateUserProfileImageCommand);
//    User updateUserPassword(UpdateUserPasswordCommand updateUserPasswordCommand);
}
