package com.example.admin.w2weekendlistdialogsms;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    DialogPictureFrag dialogPictureFrag = new DialogPictureFrag();
    AlertDialog ad;
    EditText etPhone, etMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etMessage = (EditText) findViewById(R.id.etMessage);
        etPhone = (EditText) findViewById(R.id.etRecipient);
    }

    public void createDialog(View view) {

        switch (view.getId()){

            case R.id.btnDialogFrag:
                FragmentManager fm = getSupportFragmentManager();


                dialogPictureFrag.show(fm,"TAG");
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dialogPictureFrag.dismiss();
                    }
                },3000);

                break;

            case R.id.btnAlertDiag:
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("This is an Alert Dialog")
                        .setTitle("ALERT")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();

            break;

            case R.id.btnAlertDiagCustom:

                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                builder1.setView(R.layout.custom_alert);
                AlertDialog alertDialog1 = builder1.create();
                alertDialog1.show();

                break;

            case R.id.btnAlertDiagList:

                //String[] colors = {"Red", "Blue", "Green", "Yellow", "Purple", "Orange", "Cyan", "Black", "White", "Grey"};
                String[] colors = getResources().getStringArray(R.array.mylist);

                AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
                LayoutInflater inflater = getLayoutInflater();
                View convertView = inflater.inflate(R.layout.alert_dialog_list, null);
                builder2.setView(convertView);
                //builder2.setView(R.layout.alert_dialog_list);
                /*builder2.setItems(colors, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });*/

                ListView lv = (ListView) convertView.findViewById(R.id.lvList);
                ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,colors);
                lv.setAdapter(adapter);

                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        ad.dismiss();
                    }
                });



                ad = builder2.create();
                ad.show();

        }
    }

    public void sendNotification(View view) {
        // The id of the channel.
        String CHANNEL_ID = "my_channel_01";
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.pause)
                        .setContentTitle("My notification")
                        .setContentText("Hello World!");
                        //.setChannelId(CHANNEL_ID);
// Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(this, MainActivity.class);

// The stack builder object will contain an artificial back stack for the
// started Activity.
// This ensures that navigating backward from the Activity leads out of
// your app to the Home screen.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
// Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(MainActivity.class);
// Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

// mNotificationId is a unique integer your app uses to identify the
// notification. For example, to cancel the notification, you can pass its ID
// number to NotificationManager.cancel().
        int mNotificationId = 5;
        mNotificationManager.notify(mNotificationId, mBuilder.build());
    }

    public void sendMessage(View view) {
        SmsManager sms = SmsManager.getDefault();

        String phoneNumber = etPhone.getText().toString();
        String message = etMessage.getText().toString();
        sms.sendTextMessage(phoneNumber,null,message,null,null);
    }
}
