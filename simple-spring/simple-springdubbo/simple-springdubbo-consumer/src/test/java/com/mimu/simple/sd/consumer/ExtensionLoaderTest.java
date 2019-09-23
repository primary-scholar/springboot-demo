package com.mimu.simple.sd.consumer;

import org.apache.dubbo.common.extension.ExtensionLoader;
import org.apache.dubbo.rpc.Protocol;
import org.apache.dubbo.rpc.protocol.dubbo.DubboProtocol;
import org.apache.dubbo.rpc.protocol.injvm.InjvmProtocol;
import org.junit.Test;

/**
 * author: mimu
 * date: 2019/9/21
 */
public class ExtensionLoaderTest {

    @Test
    public void info(){
        Protocol adaptiveExtension = ExtensionLoader.getExtensionLoader(Protocol.class).getAdaptiveExtension();
        ExtensionLoader<Protocol> protocolExtensionLoader = ExtensionLoader.getExtensionLoader(Protocol.class);
        Protocol dubboProtocol = protocolExtensionLoader.getExtension(DubboProtocol.NAME);
        System.out.println(dubboProtocol);

        Protocol inJvmProtocol = protocolExtensionLoader.getExtension(InjvmProtocol.NAME);
        System.out.println(inJvmProtocol);
    }
}
