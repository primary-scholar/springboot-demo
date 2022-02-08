package com.mimu.springboot.rpc.common.util;

import com.mimu.springboot.rpc.common.RpcResponse;

public class RpcResponseUtil {

    public static <Rep> RpcResponse<Rep> createSuccessResponse(Rep data) {
        return createSuccessData(data, "success");
    }

    private static <Rep> RpcResponse<Rep> createSuccessData(Rep data, String msg) {
        RpcResponse<Rep> rpcResponse = new RpcResponse<Rep>();
        rpcResponse.setCode(200);
        rpcResponse.setMsg(msg);
        rpcResponse.setData(data);
        return rpcResponse;
    }
}
