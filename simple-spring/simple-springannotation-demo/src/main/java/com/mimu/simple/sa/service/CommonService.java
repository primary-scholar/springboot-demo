package com.mimu.simple.sa.service;

import com.mimu.simple.sa.core.SimpleLogger;
import com.mimu.simple.sa.model.PersonData;
import com.mimu.simple.sa.model.TermData;
import com.mimu.simple.sa.repository.PeopleRepository;
import com.mimu.simple.sa.repository.TermRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    @Transactional
    public boolean updateInfo(PersonData personData, TermData termData) {
        boolean result = false;
        try {
            result = updatePeople(personData) && updateTerm(termData);
        } catch (Exception e) {
        }
        return result;
    }

    public boolean updatePeople(PersonData personData) {
        return peopleRepository.update(personData);
    }

    /**
     * 事物回滚
     * 只有遇到 RuntimeException 或 Error 时
     * spring 事务才会执行 rollback 操作
     *
     * @param termData
     * @return
     */
    public boolean updateTerm(TermData termData) {
        if (!termRepository.update(termData)) {
            throw new RuntimeException();
        }
        return true;
    }

}
