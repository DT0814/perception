package com.xawl.perception.test;

import com.xawl.perception.pojo.result.LoginConfirmResult;
import com.xawl.perception.pojo.result.LoginRequestResult;
import com.xawl.perception.pojo.result.SimpleResult;
import com.xawl.perception.utils.JsonUtils;
import com.xawl.perception.utils.RandomStringUtils;
import com.xawl.perception.utils.SHA256Utils;
import org.junit.Test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

public class HttpsUtils {
    private String url = "http://localhost";

    /**
     * @param cid   客户端id
     * @param Acode 客户端授权码
     * @return
     */
    public SimpleResult putImg(Integer cid, String Acode, String base64Img) {
        String PutUserState = new HttpsUtils().doPost("cid=" + cid + "&Acode=" + Acode + "&base64Img=" + base64Img, "/cli/putImg");
        System.out.println(PutUserState);
        return JsonUtils.jsonToPojo(PutUserState, SimpleResult.class);
    }

    @Test
    public void uploadImage() {
        putImg(2, "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");
    }

    /**
     * 使用积分消费
     *
     * @param cid     客户端id
     * @param Acode   客户端认证码
     * @param uid     用户id
     * @param Onumber 订单编号
     * @param value   消费积分数量
     * @return
     */
    public SimpleResult VenderUseToken(Integer cid, String Acode, String uid,
                                       String Onumber, Integer value) {
        String PutUserState = new HttpsUtils().doPost("cid=" + cid + "&Acode=" + Acode +
                "&Uid=" + uid + "&Onumber=" + Onumber + "&Value=" + value, "/user/VenderUseToken");
        System.out.println(PutUserState);
        return JsonUtils.jsonToPojo(PutUserState, SimpleResult.class);
    }

    @org.junit.Test
    public void ClientloginTest() {
        login(2, "123456");
    }

    @org.junit.Test
    public void MobileloginTest() {
        login("qq888", "123");
    }

    /**
     * 请求认证
     *
     * @param cid  客户端id
     * @param pass 客户端密码
     * @return
     */
    public String login(Integer cid, String pass) {
        String request = new HttpsUtils().doPost("cid=" + cid, "/cli/request");
        LoginRequestResult loginRequestResult = JsonUtils.jsonToPojo(request, LoginRequestResult.class);
        String confirm = SHA256Utils.sha256(pass, loginRequestResult.getRandom());
        String result = new HttpsUtils().doPost("cid=" + cid + "&confirm=" + confirm, "/cli/confirm");
        LoginConfirmResult loginConfirmResult = JsonUtils.jsonToPojo(result, LoginConfirmResult.class);
        System.out.println(loginConfirmResult.getAuthCode());
        return loginConfirmResult.getAuthCode();
    }

    public String login(String account, String pass) {
        String request = new HttpsUtils().doPost("account=" + account, "/mobile/request");
        LoginRequestResult loginRequestResult = JsonUtils.jsonToPojo(request, LoginRequestResult.class);
        String confirm = SHA256Utils.sha256(pass, loginRequestResult.getRandom());
        String result = new HttpsUtils().doPost("account=" + account + "&confirm=" + confirm, "/mobile/confirm");
        LoginConfirmResult loginConfirmResult = JsonUtils.jsonToPojo(result, LoginConfirmResult.class);
        System.out.println(loginConfirmResult.getAuthCode());
        return loginConfirmResult.getAuthCode();
    }

    public String doGet(String param, String url) {
        HttpURLConnection conn = null;
        BufferedReader in = null;
        PrintWriter out = null;
        this.url += url + "?" + param;
        try {
            conn = getConn(this.url);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            out = new PrintWriter(conn.getOutputStream());
            out.flush();
            return getDate(conn.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
            }
        }
        return "";
    }

    public String doPost(String param, String url) {
        HttpURLConnection conn = null;
        PrintWriter out = null;
        this.url += url;
        try {
            conn = getConn(this.url);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            out = new PrintWriter(conn.getOutputStream());
            out.print(param);
            out.flush();
            return getDate(conn.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private HttpURLConnection getConn(String url) throws Exception {
        HttpURLConnection conn = null;
        trustAllHttpsCertificates();
        HostnameVerifier hv = new HostnameVerifier() {
            public boolean verify(String urlHostName, SSLSession session) {
                return true;
            }
        };
        HttpsURLConnection.setDefaultHostnameVerifier(hv);
        conn = (HttpURLConnection) new URL(url).openConnection();
        return conn;
    }

    private static void trustAllHttpsCertificates() throws Exception {
        javax.net.ssl.TrustManager[] trustAllCerts = new javax.net.ssl.TrustManager[1];
        javax.net.ssl.TrustManager tm = new miTM();
        trustAllCerts[0] = tm;
        javax.net.ssl.SSLContext sc = javax.net.ssl.SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, null);
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
    }


    public String getDate(InputStream inputStream) {
        String line;
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
            }
        }
        return "";
    }

    static class miTM implements javax.net.ssl.TrustManager, javax.net.ssl.X509TrustManager {
        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        public boolean isServerTrusted(java.security.cert.X509Certificate[] certs) {
            return true;
        }

        public boolean isClientTrusted(java.security.cert.X509Certificate[] certs) {
            return true;
        }

        public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType)
                throws java.security.cert.CertificateException {
            return;
        }

        public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType)
                throws java.security.cert.CertificateException {
            return;
        }
    }
}