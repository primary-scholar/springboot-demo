package com.mimu.springboot.course.api.dto;

import com.mimu.springboot.rpc.common.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class CourseDataDTO extends BaseDTO implements Serializable {
    private Integer schoolId;
    private String schoolName;
}
