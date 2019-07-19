package com.vyin.baidu.utils;

import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
* @ author liu qihang:
* @version 创建时间 2018年6月20日下午1:53:20
* 
*/
public class MyX509TrustManager implements X509TrustManager {
    //检查客户端证书
    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
    }

    //检查服务器端证书
    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
    }

    //返回受信任的x509数组
    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }
}
