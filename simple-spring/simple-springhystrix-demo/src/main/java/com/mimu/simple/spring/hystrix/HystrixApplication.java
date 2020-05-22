package com.mimu.simple.spring.hystrix;

import com.mimu.simple.spring.hystrix.config.ApplicationConfig;
import com.mimu.simple.spring.hystrix.service.CommonService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * author: mimu
 * date: 2019/8/30
 */
public class HystrixApplication {
    private static final int size = 1;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        CommonService commonService = context.getBean(CommonService.class);
        List<InnerInfo> innerInfoList = getList();
        innerInfoList.stream().parallel().forEach(innerInfo -> innerInfo.setName(commonService.getInfo(innerInfo.getName())));
        /*for (InnerInfo innerInfo : innerInfoList) {
            innerInfo.getId();
        }*/
        //System.out.println(commonService.getInfo("aa"));
    }

    public static List<InnerInfo> getList() {
        List<InnerInfo> innerInfoList = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            InnerInfo innerInfo = new InnerInfo();
            innerInfo.setId(i);
            innerInfo.setName("s" + i);
            innerInfoList.add(innerInfo);
        }
        return innerInfoList;
    }

    public static class InnerInfo {
        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
