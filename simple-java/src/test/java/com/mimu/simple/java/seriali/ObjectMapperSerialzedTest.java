package com.mimu.simple.java.seriali;

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
        System.out.println(objectMapper.writeValueAsString(entity));
        AbstractEntity abstractEntity = objectMapper.readValue(objectMapper.writeValueAsString(entity), AbstractEntity.class);
        System.out.println(objectMapper.writeValueAsString(abstractEntity));

        RoleEntity role = new RoleEntity();
        role.setName("role name");
        role.setPassword("role password");
        role.setRole("role");
        System.out.println(objectMapper.writeValueAsString(role));
        AbstractEntity roleEntity = objectMapper.readValue(objectMapper.writeValueAsString(role), AbstractEntity.class);
        System.out.println(objectMapper.writeValueAsString(roleEntity));

        TokenEntity token = new TokenEntity();
        token.setName("token name");
        token.setPassword("token password");
        token.setToken("token");
        System.out.println(objectMapper.writeValueAsString(token));
        AbstractEntity tokenEntity = objectMapper.readValue(objectMapper.writeValueAsBytes(token), AbstractEntity.class);
        System.out.println(objectMapper.writeValueAsString(tokenEntity));

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
