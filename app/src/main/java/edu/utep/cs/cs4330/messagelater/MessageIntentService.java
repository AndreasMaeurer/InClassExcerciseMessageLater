package edu.utep.cs.cs4330.messagelater;

import android.app.IntentService;
import android.content.Intent;
import android.telephony.SmsManager;

public class MessageIntentService extends IntentService {

    public MessageIntentService() {
        super("MessageIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String phone = intent.getStringExtra("phone");
        String msg = intent.getStringExtra("message");
        int delay = Integer.parseInt(intent.getStringExtra("delay"));
        try {
            Thread.sleep(delay * 1000);
        } catch (InterruptedException e) {
        }
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phone, null, msg, null, null);
    }
}
