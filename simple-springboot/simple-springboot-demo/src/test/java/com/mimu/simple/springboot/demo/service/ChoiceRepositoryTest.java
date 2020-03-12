package com.mimu.simple.springboot.demo.service;

import com.mimu.simple.springboot.demo.dao.ChoiceRepository;
import com.mimu.simple.springboot.demo.model.ChoiceInfo;
import com.mimu.simple.springboot.demo.model.ChoiceItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 author: mimu
 date: 2020/3/5
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ChoiceRepositoryTest {
    private static String item[] = {"A", "B", "C", "D"};

    @Autowired
    private ChoiceRepository choiceRepository;

    @Test
    public void info() {
        List<ChoiceInfo> choiceInfoList = choiceRepository.listChoiceInfo();

        for (ChoiceInfo choiceInfo : choiceInfoList) {
            int index = (int) ((Math.random() + 0.5) * 10) % 4;
            ChoiceItem itemA = new ChoiceItem();
            itemA.setItemId(choiceInfo.getId());
            itemA.setItemKey(item[(++index) % 4]);
            itemA.setItemValue(choiceInfo.getCa());
            itemA.setItemType(1);
            choiceRepository.addChoiceItem(itemA);

            ChoiceItem itemB = new ChoiceItem();
            itemB.setItemId(choiceInfo.getId());
            itemB.setItemKey(item[(++index) % 4]);
            itemB.setItemValue(choiceInfo.getCb());
            itemB.setItemType(1);
            choiceRepository.addChoiceItem(itemB);

            ChoiceItem itemC = new ChoiceItem();
            itemC.setItemId(choiceInfo.getId());
            itemC.setItemKey(item[(++index) % 4]);
            itemC.setItemValue(choiceInfo.getCc());
            itemC.setItemType(1);
            choiceRepository.addChoiceItem(itemC);

            ChoiceItem itemD = new ChoiceItem();
            itemD.setItemId(choiceInfo.getId());
            itemD.setItemKey(item[(++index) % 4]);
            itemD.setItemValue(choiceInfo.getCd());
            itemD.setItemType(2);
            choiceRepository.addChoiceItem(itemD);

            ChoiceItem itemE = new ChoiceItem();
            itemE.setItemId(choiceInfo.getId());
            itemE.setItemKey("R");
            itemE.setItemValue(choiceInfo.getCe());
            itemE.setItemType(3);
            choiceRepository.addChoiceItem(itemE);

            //System.out.println(choiceInfo);
        }
    }
}
