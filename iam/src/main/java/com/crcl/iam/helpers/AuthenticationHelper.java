package com.crcl.iam.helpers;

import com.crcl.iam.dto.UserDto;

public interface AuthenticationHelper {
    UserDto getCurrent();
}
