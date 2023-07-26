package com.madatouriste.utils;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.entity.StringEntity;

public class ApiService {
    private static final String BASE_URL = "http://192.168.56.1:3900/"; // Remplacez par l'URL de votre API
    private static final int TIMEOUT = 30000; // Temps d'attente en millisecondes pour les requêtes

    private static AsyncHttpClient client = new AsyncHttpClient();

    private static ProgressBuilder dialog ;
    static {
        client.setTimeout(TIMEOUT);
    }

    public static void get(String endpoint, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        String absoluteUrl = getAbsoluteUrl(endpoint);
        client.get(absoluteUrl, params, responseHandler);
    }

    public static void post(String endpoint, RequestParams params, JSONObject body, AsyncHttpResponseHandler responseHandler) throws Exception {
        StringEntity entity = new StringEntity (body.toString());
        String absoluteUrl = getAbsoluteUrl(endpoint);
        client.post(null,absoluteUrl, entity,"application/json", responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }


//    public void testGET() throws Exception{
//        RequestParams params = new RequestParams();
//        AsyncHttpClient client = new AsyncHttpClient();
//        client.get("http://192.168.56.1:3900/api/auth/ketrika", new AsyncHttpResponseHandler() {
//            @Override
//            public void onStart() {
//                dialog.showProgressDialog();
//            }
//            @Override
//            public void onSuccess(int i, Header[] headers, byte[] responseBody) {
//                try {
//                    JSONObject json= new JSONObject( new String(responseBody));
//                    int httpStatusCode = i;
//                } catch (JSONException e) {
//                    throw new RuntimeException(e);
//                }
//
//                dialog.dismissProgressDialog();
//            }
//            @Override
//            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
//                JSONArray json = new JSONArray();
//                dialog.dismissProgressDialog();
//            }
//            @Override
//            public void onRetry(int retryNo) {
//                // called when request is retried
//                JSONArray json = new JSONArray();
//            }
//        });
//    }
//
//
//    public void testPost() throws Exception{
//        JSONObject json = new JSONObject();
//        StringEntity entity;
//        json.put("email", "a@a.com");
//        json.put("password", "12345");
//        entity = new StringEntity(json.toString());
//        AsyncHttpClient client = new AsyncHttpClient();
//        client.post(null,"http://192.168.56.1:3900/api/auth", entity,"application/json" ,new AsyncHttpResponseHandler() {
//            @Override
//            public void onStart() {
//                dialog.showProgressDialog();
//            }
//            @Override
//            public void onSuccess(int i, Header[] headers, byte[] responseBody) {
//                try {
//                    int httpStatusCode = i;
//                    JSONObject json = new JSONObject(new String(responseBody));
//                    dialog.dismissProgressDialog();
////                    nDialog.dismiss();
//                } catch (JSONException e) {
//                    throw new RuntimeException(e);
//                }
//
//            }
//
//            @Override
//            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
//                JSONArray json = new JSONArray();
//                dialog.dismissProgressDialog();
//            }
//            @Override
//            public void onRetry(int retryNo) {
//                // called when request is retried
//                JSONArray json = new JSONArray();
//            }
//        });
//    }

}
