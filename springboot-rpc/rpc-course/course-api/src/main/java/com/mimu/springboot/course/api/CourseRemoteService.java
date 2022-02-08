package com.mimu.springboot.course.api;

import com.mimu.springboot.course.api.dto.CourseDataQryDTO;
import com.mimu.springboot.course.api.dto.CourseDataRepDTO;
import com.mimu.springboot.rpc.common.QryReq;
import com.mimu.springboot.rpc.common.RpcResponse;
import com.mimu.springboot.rpc.common.api.QryService;

public interface CourseRemoteService extends QryService<CourseDataQryDTO, CourseDataRepDTO> {

    RpcResponse<CourseDataRepDTO> query4List(QryReq<CourseDataQryDTO> qryReq);
}
