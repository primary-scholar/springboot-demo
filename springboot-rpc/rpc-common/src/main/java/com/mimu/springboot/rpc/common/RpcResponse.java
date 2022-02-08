package com.mimu.springboot.rpc.common;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class RpcResponse<Rep> implements Serializable {
    private static final long serialVersionUID = 3426059351168745381L;

    private Integer code;
    private String msg;

    private Rep data;
}
