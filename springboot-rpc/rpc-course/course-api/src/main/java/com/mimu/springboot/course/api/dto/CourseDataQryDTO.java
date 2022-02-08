package com.mimu.springboot.course.api.dto;

import com.mimu.springboot.rpc.common.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class CourseDataQryDTO extends BaseDTO implements Serializable {
    private static final long serialVersionUID = 4916206060958664661L;

    private Integer schoolId;
}
