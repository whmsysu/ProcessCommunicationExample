package com.application.haominwu.processcommunicationexample.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.application.haominwu.processcommunicationexample.IMyAidlInterface;
import com.application.haominwu.processcommunicationexample.R;
import com.application.haominwu.processcommunicationexample.util.Util;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @OnClick(R.id.btn_send_message)
    public void sendMessage() {
        Intent intent = new Intent();
        intent.setAction("com.application.haominwu.processcommunicationexample.IMyAidlInterface");
        Intent eIntent = new Intent(Util.createExplicitFromImplicitIntent(this,intent));
        bindService(eIntent, connection, Context.BIND_AUTO_CREATE);
    }

    @BindView(R.id.tv_message)
    public TextView textViewMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder iBinder) {
            IMyAidlInterface iMyAidlInterface = IMyAidlInterface.Stub.asInterface(iBinder);
            try {
                textViewMessage.setText(iMyAidlInterface.getMessage());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
}
