package com.mimu.simple.ssm;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * author: mimu
 * date: 2019/10/23
 */
@RestController
public class CommonController {

    @RequestMapping(value = "")
    public void info() {

    }
}
