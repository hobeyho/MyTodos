package com.nickjwpark.mytodos;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Nick on 5/28/16.
 */
public class LogLibrary {

    static String tag = "debugging";

    //빈 줄을 프린트 해 준다
    //로그에서 빈 줄은 프린트가 불가능 하기 때문에 . 으로 대체한다
    public static void print(){
        Log.d(tag, ".");
    }

    //String msg 를 프린트 해 준다
    public static void print(String msg){
        Log.d(tag, msg);
    }

    //int n 을 프린트 해 준다
    public static void print(int n){
        Log.d(tag, "" + n);
    }

    //int [] arr 을 한줄씩 프린트 해 준다
    public static void printEach(int [] arr){
        Log.d(tag, "--- printing int array ---");
        for(int i=0; i<arr.length; i++){
            Log.d(tag, "index " + i + ": " + arr[i]);
        }
        Log.d(tag, "--- finished printing int array ---");
    }

    //String [] arr 을 한줄씩 프린트 해 준다
    public static void printEach(String [] arr){
        Log.d(tag, "--- printing String array ---");
        for(int i=0; i<arr.length; i++){
            Log.d(tag, "index " + i + ": " + arr[i]);
        }
        Log.d(tag, "--- finished printing String array ---");
    }

    //double [] arr 을 한줄씩 프린트 해 준다
    public static void printEach(double [] arr){
        Log.d(tag, "--- printing double array ---");
        for(int i=0; i<arr.length; i++){
            Log.d(tag, "index " + i + ": " + arr[i]);
        }
        Log.d(tag, "--- finished printing double array ---");
    }

    //ArrayList 를 한줄씩 프린트 해 준다
    public static void printEach(ArrayList arrList){
        Log.d(tag, "--- printing array list ---");
        for(int i=0; i<arrList.size(); i++){
            Log.d(tag, "index " + i + ": " + arrList.get(i));
        }
        Log.d(tag, "--- finished printing array list ---");
    }

    //int [] arr 을 통째로 프린트 해 준다
    public static void printWhole(int [] arr){
        Log.d(tag, "int [] : " + Arrays.toString(arr));
    }

    //String [] arr 을 통째로 프린트 해 준다
    public static void printWhole(String [] arr){
        Log.d(tag, "String [] : " + Arrays.toString(arr));
    }

    //double [] arr 을 통째로 프린트 해 준다
    public static void printWhole(double [] arr){
        Log.d(tag, "double [] : " + Arrays.toString(arr));
    }

    //ArrayList 를 통째로 프린트 해 준다
    public static void printWhole(ArrayList arrList){
        Log.d(tag, "ArrayList: " + arrList);
    }

    // *** ERROR *** 이라고 프린트 해 준다
    // Log.e 를 쓰도록 한다
    public static void printError(){
        Log.e(tag, "*** ERROR ***");
    }

    // *** ERROR + 메세지를 프린트 해 준다
    // Log.e 를 쓰도록 한다
    public static void printError(String msg){
        Log.e(tag, "*** ERROR : " + msg);
    }

    // *** ERROR + 숫자를 프린트 해 준다
    // Log.e 를 쓰도록 한다
    public static void printError(int n){
        Log.e(tag, "*** ERROR : " + n);
    }

}