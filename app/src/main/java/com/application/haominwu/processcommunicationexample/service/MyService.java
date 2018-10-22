package com.application.haominwu.processcommunicationexample.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.application.haominwu.processcommunicationexample.IMyAidlInterface;

public class MyService extends Service {

    public class MyServiceImpl extends IMyAidlInterface.Stub {

        @Override
        public String getMessage() {
            return "Test Message";
        }
    }

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return new MyServiceImpl();
    }
}
