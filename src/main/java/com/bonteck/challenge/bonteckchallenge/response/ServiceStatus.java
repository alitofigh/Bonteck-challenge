package com.bonteck.challenge.bonteckchallenge.response;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Ali Tofigh 2/14/2022 9:42 PM
 */

@Setter
@Getter
public class ServiceStatus extends BaseResponse {
 String name;
 int count;
 boolean status;
}
