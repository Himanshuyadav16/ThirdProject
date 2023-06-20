package com.lise.modals.post;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoginUserBody {
    String email;
    String password;
}
