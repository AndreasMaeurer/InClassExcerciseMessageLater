package edu.utep.cs.cs4330.messagelater;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_SMS_PERMISSION = 100;
    private int value;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestSmsPermission();
    }

    private void requestSmsPermission() {
        String smsPermission = Manifest.permission.SEND_SMS;
        int grant = ContextCompat.checkSelfPermission(this, smsPermission);
        if (grant != PackageManager.PERMISSION_GRANTED) {
            String[] permissions = new String[] { smsPermission };
            ActivityCompat.requestPermissions(this, permissions, REQUEST_SMS_PERMISSION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_SMS_PERMISSION) {
            showToast(grantResults[0] == PackageManager.PERMISSION_GRANTED ?
                    "Permission granted!" : "Permission not granted!");
        }
        requestSmsPermission();
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void SendClicked(View view) throws IOException {
        Intent intent = new Intent(this, MessageIntentService.class);

        // starting a service
        String phone = ((EditText) findViewById(R.id.phoneEdit)).getText().toString();
        String delay = ((EditText) findViewById(R.id.delayEdit)).getText().toString();
        String message = ((EditText) findViewById(R.id.msgEdit)).getText().toString();
        intent.putExtra("phone", phone);//("phone","9153162485");//("phone", …));
        intent.putExtra("delay", delay);//("delay", 30000);//("delay", …);
        intent.putExtra("message",message);//("message", "ready for extra credit picture");//("message", …);
        startService(intent);
        showToast("Button has been clicked");
    }
}
