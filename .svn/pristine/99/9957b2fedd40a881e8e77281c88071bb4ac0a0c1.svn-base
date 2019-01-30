<%-- 
    Document   : proxy
    Created on : 2014-1-18, 19:42:14
    Author     : Administrator
--%>
<%@page import="java.io.InputStream"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.net.URL"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="java.net.HttpURLConnection"%>
<%
    HttpURLConnection con = null;
    try {
        String reqUrl = request.getQueryString();
//        System.out.println(reqUrl);
        String decodedUrl = "";
        if (reqUrl != null) {
            reqUrl = URLDecoder.decode(reqUrl, "UTF-8");
        } else {
            response.setStatus(400);
            out.println("ERROR 400: No target specified for proxy.");
        }

        // extract the host
        String host = "";
        host = reqUrl.split("\\/")[2];
        boolean allowed = false;

        // check if host (with port) is in white list

        // do the proxy action (load requested ressource and transport it to client)
        // if host is in white list
    
        // replace the white spaces with plus in URL
        //reqUrl = reqUrl.replaceAll(" ", "+"); 
        reqUrl = reqUrl.substring(4, reqUrl.length());
  //      System.out.println("Proxy URL===>" + reqUrl);
        // call the requested ressource		
        URL url = new URL(reqUrl);
      //  System.out.println("Proxy URL===>" + reqUrl);
        con = (HttpURLConnection) url.openConnection();
        con.setDoOutput(true);
        con.setRequestMethod(request.getMethod());
        String reqContenType = request.getContentType();
        if (reqContenType != null) {
//            System.out.println("ContentType=" + reqUrl);
            con.setRequestProperty("Content-Type", reqContenType);
        } else {
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        }

        int clength = request.getContentLength();
        if (clength > 0) {
            con.setDoInput(true);
            byte[] idata = new byte[clength];
            request.getInputStream().read(idata, 0, clength);
            con.getOutputStream().write(idata, 0, clength);
        }

        // respond to client
        response.setContentType(con.getContentType());

        out.clear();  
        out = pageContext.pushBody(); 
        OutputStream ostream = response.getOutputStream();
        response.setContentType(con.getContentType());
        InputStream in = con.getInputStream();
        final int length = 5000;
        byte[] bytes = new byte[length];
        int bytesRead = 0;
        while ((bytesRead = in.read(bytes, 0, length)) > 0) {
            ostream.write(bytes, 0, bytesRead);
        }

    } catch (Exception e) {

        // resond an internal server error with the stacktrace
        // on exception
        response.setStatus(500);
        byte[] idata = new byte[5000];

        if (con.getErrorStream() != null) {
            con.getErrorStream().read(idata, 0, 5000);
        }

        out.println("ERROR 500: An internal server error occured. " + e.getMessage() + " " + new String(idata));
    }

%>