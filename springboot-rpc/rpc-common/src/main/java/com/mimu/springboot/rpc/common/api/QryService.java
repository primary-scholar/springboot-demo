package com.mimu.springboot.rpc.common.api;


import com.mimu.springboot.rpc.common.BaseDTO;
import com.mimu.springboot.rpc.common.QryReq;
import com.mimu.springboot.rpc.common.RpcResponse;

public interface QryService<Req extends BaseDTO,Rep extends BaseDTO> {

    RpcResponse<Rep> query4One(QryReq<Req> qryReq);

}
