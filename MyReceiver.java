package com.example.appbyadaya;

import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    static String incoming_number;
    static Context appCnt;

    MyReceiver(){
        Log.d("BRcv", "Started");
    }

    public void onReceive(Context context, Intent intent) {
        appCnt = context;
        Log.d("BRcv", "incoming message "+intent.getAction().toString());
        if(intent.getAction().equals("android.intent.action.PHONE_STATE")) {
            TelephonyManager tm = (TelephonyManager)context.getSystemService(Service.TELEPHONY_SERVICE);
            switch (tm.getCallState()) {
                case TelephonyManager.CALL_STATE_RINGING:
                    incoming_number = intent.getStringExtra("incoming_number");
                    Log.d("BRcv", "RINGING :"+ incoming_number);
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    Log.d("BRcv", "incoming ACCEPT :"+ incoming_number);
                    break;
                case TelephonyManager.CALL_STATE_IDLE:
                    Log.d("BRcv", "incoming IDLE");
                    break;
            }
        }
        if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                // get sms objects
                Object[] pdus = (Object[]) bundle.get("pdus");
                if (pdus.length == 0) {
                    return;
                }
                // large message might be broken into many
                SmsMessage[] messages = new SmsMessage[pdus.length];
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < pdus.length; i++) {
                    messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                    sb.append(messages[i].getMessageBody());
                }
                String sender = messages[0].getOriginatingAddress();
                String message = sb.toString();
                Toast.makeText(appCnt, message, Toast.LENGTH_SHORT).show();
                Log.d("BRcv", message);
                // prevent any other broadcast receivers from receiving broadcast
                // abortBroadcast();
            }
        }
    }
}

