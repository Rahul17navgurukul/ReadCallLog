package com.rahulkashyap.calldetailsfromrecentcalllog.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.telephony.TelephonyManager;

import androidx.annotation.RequiresApi;

import java.util.Date;

public class PhoneStateReceiver extends BroadcastReceiver {
    private static int lastState = TelephonyManager.CALL_STATE_IDLE;
    private static Date callStartTime;
    private static boolean isIncoming, isOutgoing,isDemo=true;
    private static String savedNumber;
    private static int state;
    static long start_time;
    private Context context;
    PostingData postingData;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        if (intent.getAction().equals("android.intent.action.NEW_OUTGOING_CALL")) {
            savedNumber = intent.getExtras().getString("android.intent.extra.PHONE_NUMBER");

        } else {
            String stateStr = intent.getExtras().getString(TelephonyManager.EXTRA_STATE);
            savedNumber = intent.getExtras().getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
            state = 0;

            if (stateStr.equals(TelephonyManager.EXTRA_STATE_IDLE)) {
                state = TelephonyManager.CALL_STATE_IDLE;
            } else if (stateStr.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)) {
                state = TelephonyManager.CALL_STATE_OFFHOOK;
            } else if (stateStr.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                state = TelephonyManager.CALL_STATE_RINGING;
            }

        }


        if (savedNumber != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                onCallStateChanged(context, state, savedNumber);
            }
        }
    }

    public void onCallStateChanged(Context context, int state, String number) {


        if (lastState == state) {
            return;
        }

        switch (state) {

            case TelephonyManager.CALL_STATE_RINGING:
                isIncoming = true;
                callStartTime = new Date();
                savedNumber = number;
                start_time = System.nanoTime();


                break;
            case TelephonyManager.CALL_STATE_OFFHOOK:
                if (lastState != TelephonyManager.CALL_STATE_RINGING) {
                    isIncoming = false;
                    callStartTime = new Date();
                    start_time = System.nanoTime();

                }

                break;
            case TelephonyManager.CALL_STATE_IDLE:
                if (lastState == TelephonyManager.CALL_STATE_RINGING) {
                    System.out.println("missed call End");
                } else if (isIncoming) {
                    System.out.println("IncomingCall End");
                } else {
                    System.out.println("outGoingCall End");
                    postingData=new PostingData(context,"out_going",callStartTime, new Date());

                }

                break;
        }
        lastState = state;
    }


}


