package com.mimu.simple.comn.netty;

import com.google.protobuf.InvalidProtocolBufferException;
import org.junit.Test;


/**
 * author: mimu
 * date: 2018/11/11
 */
public class SubModelTest {

    public SubModel.SubModelInfo creatSumModelInfo(){
        SubModel.SubModelInfo.Builder builder = SubModel.SubModelInfo.newBuilder();
        builder.setSubId(1);
        builder.setProductName("netty in practise");
        builder.setAddress("around corner");
        return builder.build();
    }

    public SubModel.SubModelInfo decode(byte[] body) throws InvalidProtocolBufferException {
        return SubModel.SubModelInfo.parseFrom(body);
    }

    public byte[] encode(SubModel.SubModelInfo subModelInfo){
        return subModelInfo.toByteArray();
    }

    @Test
    public void testInfo() throws InvalidProtocolBufferException {
        SubModel.SubModelInfo subModelInfo = creatSumModelInfo();
        System.out.println(subModelInfo.toString());
        SubModel.SubModelInfo subModelInfo1 = decode(encode(subModelInfo));
        System.out.println(subModelInfo1.toString());
        System.out.println(subModelInfo.equals(subModelInfo1));
    }
}