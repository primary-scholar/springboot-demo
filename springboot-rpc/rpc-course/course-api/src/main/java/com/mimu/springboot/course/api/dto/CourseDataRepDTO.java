package com.mimu.springboot.course.api.dto;

import com.mimu.springboot.rpc.common.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
public class CourseDataRepDTO extends BaseDTO implements Serializable {
    private static final long serialVersionUID = -5585713059109713067L;

    private List<CourseDataDTO> dataDTOList;
}
