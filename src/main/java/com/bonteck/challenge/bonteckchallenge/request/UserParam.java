package com.bonteck.challenge.bonteckchallenge.request;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

@Setter
@Getter
public class UserParam implements Serializable {
    public UserParam() {
    }

    String name;
    @SerializedName("user-name")
    String username;
    String password;
    int balance;
    @SerializedName("role-id")
    int roleId;
}
