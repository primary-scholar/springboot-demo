package com.mimu.simple.springboot.sdc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

/**
 * author: mimu
 * date: 2019/7/7
 */
@Data
@Slf4j
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TermData {
    @JsonIgnore
    private int id;
    private long pId;
    private long termId;
}
