package com.madatouriste.utils;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Serializer {
    /**
     * Serialisarion d'un objet
     * @param filename
     * @param object
     * @param context
     */
    public static  void serializer(String filename, Object object, Context context){
        try{
            FileOutputStream file = context.openFileOutput(filename,Context.MODE_PRIVATE);
            ObjectOutputStream oos;
            oos = new ObjectOutputStream(file);
            oos.writeObject(object);
            oos.flush();
            oos.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Deserealisation
     * @param filename
     * @param context
     * @return
     */
    public static Object deSerialiser(String filename, Context context){
        try {
            FileInputStream file = context.openFileInput(filename);
            ObjectInputStream ois;
            ois = new ObjectInputStream(file);
            Object object = ois.readObject();
            ois.close();
            return object;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
}
