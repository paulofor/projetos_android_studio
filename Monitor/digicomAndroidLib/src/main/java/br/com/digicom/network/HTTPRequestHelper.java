package br.com.digicom.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.ProtocolVersion;
import org.apache.http.StatusLine;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import br.com.digicom.Constants;

/**
 * Wrapper to help make HTTP requests easier - after all, we want to make it nice for the people.
 * 
 * 
 * @author charliecollins
 * 
 */
public class HTTPRequestHelper {

   private static final String CLASSTAG = "HTTPRequestHelper";

   private static final int POST_TYPE = 1;
   private static final int GET_TYPE = 2;
   private static final String CONTENT_TYPE = "Content-Type";
   
   public static final String MIME_FORM_ENCODED = "application/x-www-form-urlencoded";
   public static final String MIME_TEXT_PLAIN = "text/plain";

   // establish client as static
   // (best practice in HttpClient 4 docs, note though that static will remain around for entire process)
   private static final DefaultHttpClient client;
   static {      
      HttpParams params = new BasicHttpParams();      
      params.setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
      params.setParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET, HTTP.UTF_8);
      ///params.setParameter(CoreProtocolPNames.USER_AGENT, "Android-x");      
      params.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 60000);
      params.setParameter(CoreConnectionPNames.STALE_CONNECTION_CHECK, false);
      
      SchemeRegistry schemeRegistry = new SchemeRegistry();
      schemeRegistry.register(
               new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
      schemeRegistry.register(
               new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));

      ThreadSafeClientConnManager cm = new ThreadSafeClientConnManager(params, schemeRegistry);
      
      client = new DefaultHttpClient(cm, params);      
   }

   private final ResponseHandler<String> responseHandler;

   /**
    * Constructor that accepts ResponseHandler parameter, 
    * you can define your own ResponseHandler and do whatever you need with it. 
    * 
    * Note: you can also use the default String based response handler 
    * with the static <code>HTTPRequestHelper.getResponseHandlerInstance()</code> method. 
    * 
    * @param responseHandler
    */
   public HTTPRequestHelper(final ResponseHandler<String> responseHandler) {
      this.responseHandler = responseHandler;
   }

   // ctor that automatically uses String based ResponseHandler
   public HTTPRequestHelper(final Handler handler) {
      this(HTTPRequestHelper.getResponseHandlerInstance(handler));
   }

  
   /**
    * Perform a simple HTTP GET operation.
    * 
    */
   public void performGet(final String url) {
      performRequest(null, url, null, null, null, null, HTTPRequestHelper.GET_TYPE);
   }

   /**
    * Perform an HTTP GET operation with user/pass and headers.
    * 
    */
   public void performGet(final String url, final String user, final String pass,
            final Map<String, String> additionalHeaders) {
      performRequest(null, url, user, pass, additionalHeaders, null, HTTPRequestHelper.GET_TYPE);
   }

   /**
    * Perform an HTTP POST operation with specified content type.
    * 
    */
   public void performPost(final String contentType, final String url, final String user, final String pass,
            final Map<String, String> additionalHeaders, final Map<String, String> params) {
      performRequest(contentType, url, user, pass, additionalHeaders, params, HTTPRequestHelper.POST_TYPE);
   }

   /**
    * Perform an HTTP POST operation with specified content type.
    * 
    */
   public void performPost(final String url, final Map<String, String> params) {
      performRequest(null, url, null, null, null, params, HTTPRequestHelper.POST_TYPE);
   }
   /**
    * Perform an HTTP POST operation with a default conent-type of
    * "application/x-www-form-urlencoded."
    * 
    */
   public void performPost(final String url, final String user, final String pass,
            final Map<String, String> additionalHeaders, final Map<String, String> params) {
      performRequest(HTTPRequestHelper.MIME_FORM_ENCODED, url, user, pass, additionalHeaders, params,
               HTTPRequestHelper.POST_TYPE);
   }

   /**
    * Private heavy lifting method that performs GET or POST with supplied url, user, pass, data,
    * and headers.
    * 
    * @param contentType
    * @param url
    * @param user
    * @param pass
    * @param headers
    * @param params
    * @param requestType
    */
   private void performRequest(final String contentType, final String url, final String user, final String pass,
            final Map<String, String> headers, final Map<String, String> params, final int requestType) {

      Log.d(CLASSTAG, " " + HTTPRequestHelper.CLASSTAG + " making HTTP request to url - " + url);

      // add user and pass to client credentials if present
      if ((user != null) && (pass != null)) {
         Log.d(CLASSTAG, " " + HTTPRequestHelper.CLASSTAG + " user and pass present, adding credentials to request");
         client.getCredentialsProvider().setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(user, pass));
      }

      // process headers using request interceptor
      final Map<String, String> sendHeaders = new HashMap<String, String>();
      if ((headers != null) && (headers.size() > 0)) {
         sendHeaders.putAll(headers);
      }
      if (requestType == HTTPRequestHelper.POST_TYPE) {
         sendHeaders.put(HTTPRequestHelper.CONTENT_TYPE, contentType);
      }
      if (sendHeaders.size() > 0) {
         client.addRequestInterceptor(new HttpRequestInterceptor() {

            public void process(final HttpRequest request, final HttpContext context) throws HttpException, IOException {
               for (String key : sendHeaders.keySet()) {
                  if (!request.containsHeader(key)) {
                     Log.d(CLASSTAG, " " + HTTPRequestHelper.CLASSTAG + " adding header: " + key + " | "
                              + sendHeaders.get(key));
                     request.addHeader(key, sendHeaders.get(key));
                  }
               }
            }
         });
      }

      // handle POST or GET request respectively
      if (requestType == HTTPRequestHelper.POST_TYPE) {
         Log.d(CLASSTAG, " " + HTTPRequestHelper.CLASSTAG + " performRequest POST");
         HttpPost method = new HttpPost(url);

         // data - name/value params
         List<NameValuePair> nvps = null;
         if ((params != null) && (params.size() > 0)) {
            nvps = new ArrayList<NameValuePair>();
            for (String key : params.keySet()) {
               Log.d(CLASSTAG, " " + HTTPRequestHelper.CLASSTAG + " adding param: " + key + " | " + params.get(key));
               nvps.add(new BasicNameValuePair(key, params.get(key)));
            }
         }
         if (nvps != null) {
            try {
               method.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
            } catch (UnsupportedEncodingException e) {
               Log.e(CLASSTAG, " " + HTTPRequestHelper.CLASSTAG, e);
            }
         }
         execute(client, method);
      } else if (requestType == HTTPRequestHelper.GET_TYPE) {
         Log.d(CLASSTAG, " " + HTTPRequestHelper.CLASSTAG + " performRequest GET");
         HttpGet method = new HttpGet(url);
         execute(client, method);
      }
   }

   /**
    * Once the client and method are established, execute the request. 
    * 
    * @param client
    * @param method
    */
   private void execute(HttpClient client, HttpRequestBase method) {
      Log.d(CLASSTAG, " " + HTTPRequestHelper.CLASSTAG + " execute invoked");

      // create a response specifically for errors (in case)
      BasicHttpResponse errorResponse = new BasicHttpResponse(new ProtocolVersion("HTTP_ERROR", 1, 1), 500, "ERROR");

      try {
         client.execute(method, this.responseHandler);
         Log.d(CLASSTAG, " " + HTTPRequestHelper.CLASSTAG + " request completed");
      } catch (Exception e) {
         Log.e(CLASSTAG, " " + HTTPRequestHelper.CLASSTAG, e);
         errorResponse.setReasonPhrase(e.getMessage());
         try {
            this.responseHandler.handleResponse(errorResponse);
         } catch (Exception ex) {
            Log.e(CLASSTAG, " " + HTTPRequestHelper.CLASSTAG, ex);
         }
      }
   }

   /**
    * Static utility method to create a default ResponseHandler that sends a Message to the passed
    * in Handler with the response as a String, after the request completes.
    * 
    * @param handler
    * @return
    */
   public static ResponseHandler<String> getResponseHandlerInstance(final Handler handler) {
      final ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

         public String handleResponse(final HttpResponse response) {
            Message message = handler.obtainMessage();
            Bundle bundle = new Bundle();
            StatusLine status = response.getStatusLine();
            Log.d(CLASSTAG, " " + HTTPRequestHelper.CLASSTAG + " statusCode - " + status.getStatusCode());
            Log.d(CLASSTAG, " " + HTTPRequestHelper.CLASSTAG + " statusReasonPhrase - " + status.getReasonPhrase());
            HttpEntity entity = response.getEntity();
            String result = null;
            if (entity != null) {
               try {
                  result = HTTPRequestHelper.inputStreamToString(entity.getContent());
                  bundle.putString("RESPONSE", result);
                  message.setData(bundle);
                  handler.sendMessage(message);
               } catch (IOException e) {
                  Log.e(CLASSTAG, " " + HTTPRequestHelper.CLASSTAG, e);
                  bundle.putString("RESPONSE", "Error - " + e.getMessage());
                  message.setData(bundle);
                  handler.sendMessage(message);
               }
            } else {
               Log.w(CLASSTAG, " " + HTTPRequestHelper.CLASSTAG + " empty response entity, HTTP error occurred");
               bundle.putString("RESPONSE", "Error - " + response.getStatusLine().getReasonPhrase());
               message.setData(bundle);
               handler.sendMessage(message);
            }
            return result;
         }
      };
      return responseHandler;
   }

   private static String inputStreamToString(final InputStream stream) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(stream));
      StringBuilder sb = new StringBuilder();
      String line = null;
      while ((line = br.readLine()) != null) {
         sb.append(line + "\n");
      }
      br.close();
      return sb.toString();
   }
   
   public static String getHttpResponse(String location, final Map<String, String> params) {
       String result = null;
       URL url = null;
       Log.d(Constants.LOGTAG, " " + CLASSTAG + " location = " + location);
       List<NameValuePair> nvps = null;
       try {
    	   // Montando os parametros
          
           if ((params != null) && (params.size() > 0)) {
              nvps = new ArrayList<NameValuePair>();
              for (String key : params.keySet()) {
                 Log.d(CLASSTAG, " " + HTTPRequestHelper.CLASSTAG + " adding param: " + key + " | " + params.get(key));
                 String param = params.get(key);
                 //param = TextUtils.htmlEncode(param);
                 nvps.add(new BasicNameValuePair(key, param));
              }
           }
               	      	   
           url = new URL(location);
           Log.d(CLASSTAG,"url = " + url);
       } catch (MalformedURLException e) {
           Log.e(CLASSTAG, e.getMessage());
       }

       if (url != null) {
           try {
        	   
        	   HttpClient httpclient = new DefaultHttpClient();
        	   HttpPost httppost = new HttpPost(location);
        	   httppost.setEntity(new UrlEncodedFormEntity(nvps));
        	   HttpResponse response = httpclient.execute(httppost);
        	   
        	   result = EntityUtils.toString(response.getEntity());
        	   Log.d(CLASSTAG,"Resposta:" + result);              
               

           } catch (IOException e) {
               Log.e(CLASSTAG , e.getMessage());
           }
       } else {
           Log.e(CLASSTAG, "url NULL");
       }
       return result;
   }
   
   
   private String getQuery(List<NameValuePair> params) throws UnsupportedEncodingException
   {
       StringBuilder result = new StringBuilder();
       boolean first = true;

       for (NameValuePair pair : params)
       {
           if (first)
               first = false;
           else
               result.append("&");

           result.append(URLEncoder.encode(pair.getName(), "UTF-8"));
           result.append("=");
           result.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
       }

       return result.toString();
   }
   
   public static List<String> getHttpResponseList(String location) {
	   List<String> saida = new LinkedList<String>();
	   URL url = null;
       String inputLine = null;

       try {
           url = new URL(location);
           Log.d(CLASSTAG, "Url = " + url);
       } catch (MalformedURLException e) {
           Log.e(CLASSTAG, "Erro:" + e.getMessage());
       }

       if (url != null) {
           try {
               HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
               InputStream is = urlConn.getInputStream();
               InputStreamReader isr = new InputStreamReader(is);
               BufferedReader in = new BufferedReader(isr);
       
               while (((inputLine = in.readLine()) != null)) {
            	   saida.add(inputLine);
               }
               in.close();
               urlConn.disconnect();

           } catch (IOException e) {
        	   Log.e(CLASSTAG, "Erro:" + e.getMessage());
           }
       } else {
           Log.e(CLASSTAG, " url NULL");
       }
       return saida;
   }
   
   public static String getHttpResponse(String location) {
       //StringBuffer result = new StringBuffer();
       URL url = null;
       String inputLine = null;

       try {
           url = new URL(location);
           Log.d(CLASSTAG, "Url = " + url);
       } catch (MalformedURLException e) {
           Log.e(CLASSTAG, "Erro:" + e.getMessage());
       }

       if (url != null) {
           try {
               HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
               InputStream is = urlConn.getInputStream();
               InputStreamReader isr = new InputStreamReader(is);
               BufferedReader in = new BufferedReader(isr);
               
               inputLine = in.readLine();
               //int lineCount = 0; // limit the lines for the example
               /*
               while (((inputLine = in.readLine()) != null)) {
                   //lineCount++;
                   //Log.v(CLASSTAG, "Linha=" + inputLine);
                   result.append("\n" + inputLine);
               }
                */
               in.close();
               urlConn.disconnect();

           } catch (IOException e) {
        	   Log.e(CLASSTAG, "Erro:" + e.getMessage());
           }
       } else {
           Log.e(CLASSTAG, " url NULL");
       }
       return inputLine;
   }
}
