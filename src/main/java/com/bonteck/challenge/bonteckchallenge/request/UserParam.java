package com.bonteck.challenge.bonteckchallenge.request;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Setter
@Getter
public class UserParam implements Serializable {

    public UserParam() {
    }

    String name;
    String username;
    String password;
    @Min(value = 0, message = "balance can't be less than 0")
    @Max(value = 1000000, message = "balance can't be more than 1000000" )
    int balance;
}
