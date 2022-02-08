package com.mimu.springboot.course.service.rpc;

import com.mimu.springboot.course.api.CourseRemoteService;
import com.mimu.springboot.course.api.dto.CourseDataQryDTO;
import com.mimu.springboot.course.api.dto.CourseDataRepDTO;
import com.mimu.springboot.rpc.common.QryReq;
import com.mimu.springboot.rpc.common.RpcResponse;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService(interfaceClass = CourseRemoteService.class)
public class CourseRemoteServiceImpl implements CourseRemoteService {
    public RpcResponse<CourseDataRepDTO> query4One(QryReq<CourseDataQryDTO> qryReq) {
        return null;
    }

    public RpcResponse<CourseDataRepDTO> query4List(QryReq<CourseDataQryDTO> qryReq) {
        return null;
    }
}
