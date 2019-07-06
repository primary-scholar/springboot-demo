package com.mimu.simple.sa.service;

import com.mimu.simple.sa.model.People;
import com.mimu.simple.sa.model.Term;
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
    public boolean saveInfo(People people, Term term) {
        return savePeople(people) && saveTerm(term);
    }

    public boolean savePeople(People people) {
        return peopleRepository.save(people);
    }

    public boolean saveTerm(Term term) {
        return termRepository.save(term);
    }

    @Transactional
    public boolean updateInfo(People people, Term term) {
        boolean result = false;
        try {
            result = updatePeople(people) && updateTerm(term);
        } catch (Exception e) {
        }
        return result;
    }

    public boolean updatePeople(People people) {
        return peopleRepository.update(people);
    }

    /**
     * 事物回滚
     * 只有遇到 RuntimeException 或 Error 时
     * spring 事务才会执行 rollback 操作
     * @param term
     * @return
     */
    public boolean updateTerm(Term term) {
        if (!termRepository.update(term)) {
            throw new RuntimeException();
        }
        return true;
    }

}
