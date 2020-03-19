package com.mimu.simple.spring.annotation.demo.service;

import com.mimu.simple.spring.annotation.demo.model.TermData;
import com.mimu.simple.spring.annotation.demo.repository.TermRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 author: mimu
 date: 2020/3/19
 */
@Service
public class TermTransactionServiceB {

    @Autowired
    private TermRepository termRepository;

    /** Propagation.REQUIRED
     * A.info1() B.info1() 处在同一个事务中
     * 无论是 A.info1() 还是 B.info1() 中抛出异常 则 A,B 都会回滚
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void info1() {
        TermData termData = new TermData();
        termData.setPid(5);
        termData.setTid(5);
        termRepository.save(termData);
        //throw new RuntimeException();
    }

    /** Propagation.REQUIRED 和 Propagation.REQUIRES_NEW 后者被嵌套 或者 Propagation.REQUIRES_NEW 相互嵌套
     * A.info2() B.info2() 处在不同的两个事务中
     * 如果后者抛异常，且影响到了前者 则A,B 都回滚
     * 如果后者抛异常，但被前者捕获 则A 不回滚,B 回滚
     * 如果后者已经提交，而前者抛异常 则A 回滚,B 不回滚
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void info2() {
        TermData termData = new TermData();
        termData.setPid(5);
        termData.setTid(5);
        termRepository.save(termData);
        //throw new RuntimeException();
    }

    /** Propagation.REQUIRED 和 Propagation.NESTED 后者被嵌套
     * A.info3() B.info3() 后者运行在前者的 一个子事务中，并有自己的 savePoint
     * 如果后者抛异常，且影响到了前者 则A,B 都回滚
     * 如果后者抛异常，但被前者捕获 则A 不回滚,B 回滚
     * 如果后者已经提交，而前者抛异常 则A 回滚,B 同样回滚
     */
    @Transactional(propagation = Propagation.NESTED, rollbackFor = Exception.class)
    public void info3() {
        TermData termData = new TermData();
        termData.setPid(5);
        termData.setTid(5);
        termRepository.save(termData);
        throw new RuntimeException();
    }

}
