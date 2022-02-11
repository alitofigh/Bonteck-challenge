package com.bonteck.challenge.bonteckchallenge.response;

import com.google.gson.Gson;

/**
 * @author Ali Tofigh 2/10/2022 4:07 PM
 */

public class BaseResponse {

    public String prepareResult(BaseResponse response) {
        return new Gson().toJson(response);
    }

}
