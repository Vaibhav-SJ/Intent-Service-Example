package com.example.appmomos.intentserviceexample;

import android.annotation.SuppressLint;
import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;

public class MyIntentService extends IntentService
{

    Handler mHandler;
    NotificationCompat.Builder builder;
    int count = 0;

    private Handler handler;
    private Runnable handlerTask;

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mHandler = new Handler();
    }

    @Override
    protected void onHandleIntent(final Intent intent)
    {
        mHandler.post(new Runnable()
        {
            @Override
            public void run()
            {

                handler = new Handler();
                handlerTask = new Runnable()
                {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void run()
                    {

                        count++;

                        builder = new NotificationCompat.Builder(MyIntentService.this)
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setContentTitle("Timer")
                                .setContentText(String.valueOf(count));

                        Intent notificationIntent = new Intent(MyIntentService.this, MainActivity.class);
                        PendingIntent contentIntent = PendingIntent.getActivity(MyIntentService.this, 0, notificationIntent,
                                PendingIntent.FLAG_UPDATE_CURRENT);
                        builder.setContentIntent(contentIntent);

                        // Add as notification
                        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                        assert manager != null;
                        manager.notify(0, builder.build());

                        if(count < Integer.parseInt(intent.getStringExtra("count")))
                        {
                            handler.postDelayed(handlerTask, 1000);
                        }

                    }
                };
                handlerTask.run();

            }
        });


    }

}
