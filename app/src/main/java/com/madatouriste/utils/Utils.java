package com.madatouriste.utils;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public  abstract class Utils {
    /**
     * Conversion Chaine en date
     * @param date
     * @return
     */
    public static Date convertStringToDate(String date){
        String formatAttendu = "EEE MMM dd hh:mm:ss 'GMT' yyyy";
        return convertStringToDate(date, formatAttendu);
    }

    /**
     * Conversion date avex une format
     * @param date
     * @param formatAttendu
     * @return
     */
    public static Date convertStringToDate(String date, String formatAttendu){
        SimpleDateFormat formatter = new SimpleDateFormat(formatAttendu);
        try {
            Date da = formatter.parse(date);
            return da;
        } catch (ParseException e) {
            Log.d("Erreur", "************ Parse date ::: ==> "+ e.getMessage());

        }

        return null;
    }

    /**
     * Conversion d'une date en chaine sous la form
     * @param dat
     * @return
     */
    public static String convertDateToString(Date dat){
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return date.format(dat);
    }

    /**
     * Return un float u format string avec un chiffre apres la virgule
     * @param valeur
     * @return
     */
    public static String format2Decimal(Float valeur){
        return String.format(".01f",valeur);
    }


    public static ArrayList getArray(String jsonA, Class clas) throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        JSONArray jsonArray = new JSONArray(jsonA);
        ArrayList array = new ArrayList<>();
        for(int i=0; i<jsonArray.length(); i++){
            array.add(objectMapper.readValue(jsonArray.get(i).toString(), clas));
        }
        return array;
    }

//    public  static void ecouteMenu(Context context, View button, Class classe, Object ob){
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context,classe);
//                intent.putExtra("data", (Serializable) ob);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // fermer tout autre activity
//                context.startActivity(intent);
//            }
//        });
//    }

    public  static void ecouteMenu(Context context, View button, Class classe, HashMap<String, ?>  map){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,classe);
                for (HashMap.Entry<String, ?> entry : map.entrySet()){
                    intent.putExtra(entry.getKey(), (Serializable) entry.getValue());
                }

//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // fermer tout autre activity
                context.startActivity(intent);
            }
        });
    }

    public static void saveText(Context context,String fileName,String text) {
        FileOutputStream fos = null;

        try {
            fos =  context.openFileOutput(fileName, Context.MODE_PRIVATE);
            fos.write(text.getBytes());

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String  loadText(Context context,String fileName) {
        FileInputStream fis = null;
        String text="";
        String text2 = "";
        try {
            fis = context.openFileInput(fileName);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();

//            while(fis.read(bytes)!=1){
//
//            }

            while ((text2 = br.readLine()) != null) {
                sb.append(text2).append("\n");
            }
            text = sb.toString().trim();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return text;
    }
}
