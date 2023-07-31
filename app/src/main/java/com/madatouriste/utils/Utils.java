package com.madatouriste.utils;

import android.Manifest;
import android.app.Dialog;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.madatouriste.R;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.lang.reflect.Array;
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

    public static ArrayList<String> getImage(){
        String url ="http://192.168.56.1/";
        ArrayList<String> imageObjects = new ArrayList<String>();
        imageObjects.add( url+"01.png");
        imageObjects.add(url+"02.png");
        imageObjects.add( url+"03.png");
        imageObjects.add(url+"04.png");
        imageObjects.add(url+"05.png");
        imageObjects.add(url+"06.png");
        imageObjects.add(url+"07.png");
//        imageObjects.add(url+"08.png");
//        imageObjects.add(url+"09.png");
//        imageObjects.add(url+"10.png");
//        imageObjects.add(url+"11.png");
//        imageObjects.add(url+"12.png");
        return imageObjects;
    }


    public static void sendNotif1(Context context,String title, String message) {
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        Notification notification = new NotificationCompat.Builder(context, App.CHANNEL_1_ID)
                .setSmallIcon(R.drawable.logout_icon)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        notificationManager.notify(1, notification);
    }

    public static void sendNotif2(Context context,String title, String message) {
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        Notification notification = new NotificationCompat.Builder(context, App.CHANNEL_2_ID)
                .setSmallIcon(R.drawable.logout_icon)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .build();

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        notificationManager.notify(2, notification);
    }

    public static void ShowPopup(Context context) {
        TextView txtclose;
        Button btnFollow;
        Dialog myDialog = new Dialog(context);;
        myDialog.setContentView(R.layout.fragment_popup);
        txtclose =(TextView) myDialog.findViewById(R.id.txtclose);
        txtclose.setText("M");
        btnFollow = (Button) myDialog.findViewById(R.id.btnfollow);
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }

    public static boolean fragmentNavig(FragmentActivity activity, Fragment fragment){
        activity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment, fragment)
                .addToBackStack(null)
                .commit();
            return true;
    }
}
