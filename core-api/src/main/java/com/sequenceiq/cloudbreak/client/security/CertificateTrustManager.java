package com.sequenceiq.cloudbreak.client.security;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CertificateTrustManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(CertificateTrustManager.class);

    private CertificateTrustManager() {
    }

    public static HostnameVerifier hostnameVerifier() {

        // Do not verify host names
        return new HostnameVerifier() {

            @Override
            public boolean verify(String hostname, SSLSession sslSession) {
                LOGGER.info("verify hostname: {}", hostname);
                return true;
            }
        };

    }


    public static SSLContext sslContext() {
        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            @Override
            public X509Certificate[] getAcceptedIssuers() {
                LOGGER.info("accept all issuer");
                return null;
            }

            @Override
            public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                LOGGER.info("checkClientTrusted");
                // Trust everything
            }

            @Override
            public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                LOGGER.info("checkServerTrusted");
                // Trust everything
            }
        }
        };
        try {
            // Install the all-trusting trust manager
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new SecureRandom());
            LOGGER.warn("Trust all SSL cerificates has been installed");
            return sc;
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException("F", e);
        }
    }

}