package com.mimu.simple.comn.netty;


import com.google.protobuf.InvalidProtocolBufferException;
import org.junit.Test;

/**
 * author: mimu
 * date: 2018/11/11
 */
public class ResModelTest {

    public ResModel.ResModelInfo createResModelInfo() {
        ResModel.ResModelInfo.Builder builder = ResModel.ResModelInfo.newBuilder();
        builder.setSubId(1);
        builder.setRespCode(200);
        builder.setDesc("response here: " + builder.getSubId());
        return builder.build();
    }

    public ResModel.ResModelInfo decode(byte[] body) throws InvalidProtocolBufferException {
        return ResModel.ResModelInfo.parseFrom(body);
    }

    public byte[] encode(ResModel.ResModelInfo resModelInfo){
        return resModelInfo.toByteArray();
    }

    @Test
    public void testInfo() throws InvalidProtocolBufferException {
        ResModel.ResModelInfo resModelInfo = createResModelInfo();
        System.out.println(resModelInfo.toString());
        ResModel.ResModelInfo resModelInfo1 = decode(encode(resModelInfo));
        System.out.println(resModelInfo1.toString());
        System.out.println(resModelInfo1.equals(resModelInfo));
    }

}