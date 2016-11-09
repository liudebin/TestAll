package qian.ling.yi.ext.ssl;

import javax.security.cert.X509Certificate;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by liuguobin on 2016/9/13.
 */
public class SSLServlet extends HttpServlet{
    private static final String ATTR_CER = "javax.servlet.request.X509Certificate";
    private static final String CONTENT_TYPE = "text/plain;charset=UTF-8";
    private static final String DEFAULT_ENCODING = "UTF-8";
    private static final String SCHEME_HTTPS = "https";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("请求:" + System.nanoTime());
        response.setContentType(CONTENT_TYPE);
        response.setCharacterEncoding(DEFAULT_ENCODING);
        PrintWriter out = response.getWriter();
        out.println("cmd=["+request.getParameter("cmd")+"], data=["+request.getParameter("data")+"]");
        System.out.println("12324124");
        X509Certificate[] certs = (X509Certificate[]) request.getAttribute(ATTR_CER);
        if (certs != null) {
            int count = certs.length;
            System.out.println(count);
            out.println("共检测到[" + count + "]个客户端证书");
            for (int i = 0; i < count; i++) {
                out.println("客户端证书 [" + (++i) + "]： ");
                out.println("校验结果：" + verifyCertificate(certs[--i]));
                out.println("证书详细：\r" + certs[i].toString());
            }
        } else {
            System.out.println("?");
            if (SCHEME_HTTPS.equalsIgnoreCase(request.getScheme())) {
                out.println("这是一个HTTPS请求，但是没有可用的客户端证书");
            } else {
                out.println("这不是一个HTTPS请求，因此无法获得客户端证书列表 ");
            }
        }
        out.close();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }


    private boolean verifyCertificate(X509Certificate certificate) {
        boolean valid = false;
        try {
            certificate.checkValidity();
            valid=true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return valid;
    }
}
