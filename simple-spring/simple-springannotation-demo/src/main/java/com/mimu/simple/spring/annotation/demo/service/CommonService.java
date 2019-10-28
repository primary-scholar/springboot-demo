package com.mimu.simple.spring.annotation.demo.service;

import com.mimu.simple.spring.annotation.demo.core.SimpleLogger;
import com.mimu.simple.spring.annotation.demo.model.PersonData;
import com.mimu.simple.spring.annotation.demo.model.TermData;
import com.mimu.simple.spring.annotation.demo.repository.PeopleRepository;
import com.mimu.simple.spring.annotation.demo.repository.TermRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * author: mimu
 * date: 2019/1/11
 */
@Service
public class CommonService {

    private PeopleRepository peopleRepository;
    private TermRepository termRepository;

    @Autowired
    public void setPeopleRepository(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Autowired
    public void setTermRepository(TermRepository termRepository) {
        this.termRepository = termRepository;
    }

    @Transactional
    public boolean saveInfo(PersonData personData, TermData termData) {
        return savePeople(personData) && saveTerm(termData);
    }

    public boolean savePeople(PersonData personData) {
        return peopleRepository.save(personData);
    }

    public boolean saveTerm(TermData termData) {
        return termRepository.save(termData);
    }

    @SimpleLogger
    public PersonData getPeople(int pid) {
        return peopleRepository.getPeople(pid);
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
        return peopleRepository.update(personData);
    }

    public boolean updateTerm(TermData termData) {
        if (!termRepository.update(termData)) {
            throw new RuntimeException();
        }
        return true;
    }

}
