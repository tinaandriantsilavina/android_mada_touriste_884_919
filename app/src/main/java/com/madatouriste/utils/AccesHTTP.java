package com.madatouriste.utils;

import android.os.AsyncTask;

//import org.apache.http.HttpResponse;
//import org.apache.http.NameValuePair;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.message.BasicNameValuePair;
//import org.apache.http.util.EntityUtils;


public class AccesHTTP extends AsyncTask<String,Integer,Long> {
//    private ArrayList<NameValuePair> parametres;
    private String  ret = null;
    public  AsyncResponse delegate = null;

    /**
     * Constructeur
     */
    public AccesHTTP() {

//        parametres= new ArrayList<NameValuePair>();
    }

    /**
     * Ajout d'un parametre post
     * @param nom
     * @param valeur
     */
    public void addParam(String nom, String valeur){
//        parametres.add(new BasicNameValuePair(nom,valeur));
    }

    /**
     * Connexion en tache de fond dans un thread séparé
     * @param strings
     * @return
     */
    @Override
    protected Long doInBackground(String... strings) {
//        HttpClient cnxHttp = new DefaultHttpClient();
//        HttpPost paramCnx= new HttpPost(strings[0]);
//        try{
//            //encodage des parametre
//            paramCnx.setEntity(new UrlEncodedFormEntity(parametres));
//            //Connexion et envoie de parametre, attente de reponse
//            HttpResponse reponse = cnxHttp.execute(paramCnx);
//            // transformation de reponse
//            ret = EntityUtils.toString(reponse.getEntity());
//        }catch ( Exception e){
//            Log.d("Erreur encodage ","*************"+e.getMessage());
//        }

        return null;
    }

    @Override
    protected void onPostExecute(Long result){
//        delegate.processFinish(ret!=null? ret.toString(): null);
    }
}
