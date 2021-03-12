package com.wheelseye.Blogging.request;

import lombok.Data;

@Data
public class ChangePassword {
    String oldPassword;
    String newPassword;
}
