package com.mimu.springboot.rpc.common;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class QryReq<Req extends BaseDTO> implements Serializable {
    private static final long serialVersionUID = -1431305360123573091L;

    private Req data;
}
