package com.mimu.simple.sd.consumer;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.apache.dubbo.rpc.Protocol;
import org.apache.dubbo.rpc.ProxyFactory;
import org.apache.dubbo.rpc.protocol.dubbo.DubboProtocol;
import org.apache.dubbo.rpc.protocol.injvm.InjvmProtocol;
import org.junit.Test;

import javax.xml.crypto.Data;

/**
 * author: mimu
 * date: 2019/9/21
 */
public class ExtensionLoaderTest {

    @Test
    public void info() {



    }

    @Test
    public void proxyFactory(){
        ProxyFactory adaptiveExtension1 = ExtensionLoader.getExtensionLoader(ProxyFactory.class).getAdaptiveExtension();
        System.out.println(adaptiveExtension1);
    }

    @Test
    public void protocol(){
        Protocol adaptiveExtension = ExtensionLoader.getExtensionLoader(Protocol.class).getAdaptiveExtension();
        URL url = new URL(DubboProtocol.NAME, "localhost", 20880);
        System.out.println(adaptiveExtension.refer(DubboProtocol.class, url));
        System.out.println(adaptiveExtension.getDefaultPort());

        ExtensionLoader<Protocol> protocolExtensionLoader = ExtensionLoader.getExtensionLoader(Protocol.class);
        Protocol dubboProtocol = protocolExtensionLoader.getExtension(DubboProtocol.NAME);
        System.out.println(dubboProtocol);

        Protocol inJvmProtocol = protocolExtensionLoader.getExtension(InjvmProtocol.NAME);
        System.out.println(inJvmProtocol);
    }
}
