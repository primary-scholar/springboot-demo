package com.mimu.simple.java.seriali;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class ObjectMapperSerialzedTest {
    public ObjectMapper objectMapper;

    @Before
    public void objectMapper() {
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Test
    public void info() throws IOException {
        AbstractEntity entity = new AbstractEntity();
        entity.setName("name");
        entity.setPassword("password");

        ResultModel model = new ResultModel();
        model.setEntity(entity);
        model.setNo(1);
        System.out.println(objectMapper.writeValueAsString(model));
        ResultModel model1 = objectMapper.readValue(objectMapper.writeValueAsString(model), ResultModel.class);
        System.out.println(objectMapper.writeValueAsString(model1));


        RoleEntity role = new RoleEntity();
        role.setName("role name");
        role.setPassword("role password");
        role.setRole("role");
        model.setEntity(role);
        model.setNo(1);
        System.out.println(objectMapper.writeValueAsString(model));
        ResultModel model12 = objectMapper.readValue(objectMapper.writeValueAsString(model), ResultModel.class);
        System.out.println(objectMapper.writeValueAsString(model12));

        TokenEntity token = new TokenEntity();
        token.setName("token name");
        token.setPassword("token password");
        token.setToken("token");
        model.setEntity(token);
        model.setNo(1);
        System.out.println(objectMapper.writeValueAsString(model));
        ResultModel model13 = objectMapper.readValue(objectMapper.writeValueAsString(model), ResultModel.class);
        System.out.println(objectMapper.writeValueAsString(model13));


        /**
         * fastJson 存在 同样的问题，可以使用如下方法 解决
         */
        String x = JSONObject.toJSONString(model, SerializerFeature.WriteClassName);
        System.out.println(x);
        ParserConfig globalInstance = ParserConfig.getGlobalInstance();
        globalInstance.setAutoTypeSupport(true);
        Object o = JSONObject.parseObject(x, ResultModel.class, globalInstance);
        System.out.println(JSONObject.toJSONString(o));

    }

    /**
     * 对于 类中 包含有 多个 子类的 model objectMapper 反序列化时 无法 反序列化具体的 子类
     * 可以使用 @JsonTypeInfo(use = JsonTypeInfo.Id.CLASS) 来标示出 序列化中的 具体子类
     * 然后 反序列化 就没有问题了
     */
    public static class ResultModel {
        private int no;
        @JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
        private AbstractEntity entity;

        public int getNo() {
            return no;
        }

        public void setNo(int no) {
            this.no = no;
        }

        public AbstractEntity getEntity() {
            return entity;
        }

        public void setEntity(AbstractEntity entity) {
            this.entity = entity;
        }
    }

    public static class AbstractEntity {
        private String name;
        private String password;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    public static class RoleEntity extends AbstractEntity {
        private String role;


        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }
    }

    public static class TokenEntity extends AbstractEntity {
        private String token;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }

}
