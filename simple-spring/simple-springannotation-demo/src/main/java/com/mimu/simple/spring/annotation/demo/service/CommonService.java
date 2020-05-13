package com.mimu.simple.spring.annotation.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.mimu.simple.spring.annotation.demo.core.SimpleLogger;
import com.mimu.simple.spring.annotation.demo.model.PersonData;
import com.mimu.simple.spring.annotation.demo.model.TermData;
import com.mimu.simple.spring.annotation.demo.repository.PeopleRepository;
import com.mimu.simple.spring.annotation.demo.repository.TermRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.List;
import java.util.Set;

/**
 * author: mimu
 * date: 2019/1/11
 */
@Slf4j
@Service
public class CommonService {

    private StringRedisTemplate stringRedisTemplate;
    private PeopleRepository peopleRepository;
    private TermRepository termRepository;

    @PostConstruct
    public void info1() {
        System.out.println("method... info1()");
    }

    @Autowired
    public void setPeopleRepository(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Autowired
    public void setTermRepository(TermRepository termRepository) {
        this.termRepository = termRepository;
    }

    @Autowired
    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Transactional
    public boolean saveInfo(PersonData personData, TermData termData) {
        return savePeople(personData) && saveTerm(termData);
    }

    public boolean savePeople(PersonData personData) {
        boolean save = peopleRepository.save(personData);
        log.info("savePeople result={}", save);
        return save;
    }

    public boolean saveTerm(TermData termData) {
        boolean save = termRepository.save(termData);
        log.info("saveTerm result={}", save);
        return save;
    }

    @SimpleLogger
    public PersonData getPeople(int pid) {
        return peopleRepository.getPeople(pid);
    }


    public PersonData getPeopleWithCache(int pid) {
        String key = "people::user_" + pid;
        String s = stringRedisTemplate.opsForValue().get(key);
        if (StringUtils.isNotEmpty(s)) {
            return JSONObject.parseObject(s, PersonData.class);
        }
        PersonData people = peopleRepository.getPeople(pid);
        if (people != null) {
            String s1 = JSONObject.toJSONString(people);
            stringRedisTemplate.opsForValue().set(key, s1, Duration.ofMillis(60000));
        }
        log.info("{}", people);
        return people;
    }

    /**
     * 只有 使用 @Transactional 标注的方法 抛出异常 事务才可以回滚
     * 事物回滚
     * 只有遇到 RuntimeException 或 Error 时
     * spring 事务才会执行 rollback 操作
     *
     * @param termData
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public boolean updateInfo(PersonData personData, TermData termData) {
        return updatePeople(personData) && updateTerm(termData);
    }

    public boolean updatePeople(PersonData personData) {
        boolean update = peopleRepository.update(personData);
        log.info("updatePeople result {}", update);
        return update;
    }

    public boolean updateTerm(TermData termData) {
        boolean update = termRepository.update(termData);
        log.info("updateTerm result={}", update);
        if (!update) {
            throw new RuntimeException();
        }
        return update;
    }

    public List<TermData> listInfo(Set<String> termIdSet) {
        String join = String.join("','", termIdSet);
        return termRepository.list(join);
    }

}
