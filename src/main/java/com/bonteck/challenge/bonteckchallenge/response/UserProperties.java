package com.bonteck.challenge.bonteckchallenge.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Ali Tofigh 2/10/2022 4:11 PM
 */

@Setter
@Getter
public class UserProperties extends BaseResponse {
    String name;
    @SerializedName("user-name")
    String username;
    String role;
    @SerializedName("is-enabled")
    boolean isEnable;
    int balance;
    @SerializedName("non-locked")
    boolean isLocked;
}
