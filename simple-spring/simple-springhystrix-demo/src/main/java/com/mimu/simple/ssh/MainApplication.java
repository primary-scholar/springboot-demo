package com.mimu.simple.ssh;

import com.mimu.simple.ssh.config.ApplicationConfig;
import com.mimu.simple.ssh.service.CommonService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * author: mimu
 * date: 2019/8/30
 */
public class MainApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        CommonService commonService = context.getBean(CommonService.class);
        List<InnerInfo> innerInfoList = getList();
        innerInfoList.stream().parallel().forEach(innerInfo -> innerInfo.setName(commonService.getInfo(innerInfo.getName())));
        for (InnerInfo innerInfo : innerInfoList) {
            System.out.println(innerInfo.getId() + " " + innerInfo.getName());
        }
    }

    public static List<InnerInfo> getList() {
        List<InnerInfo> innerInfoList = new ArrayList<>(1000);
        for (int i = 0; i < 100; i++) {
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
