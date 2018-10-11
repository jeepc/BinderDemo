package com.jeepc.binderdemo.client;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jeepc.binderdemo.ICommonBinder;
import com.jeepc.binderdemo.INonuseAidl;
import com.jeepc.binderdemo.NonuseAidlProxy;
import com.jeepc.binderdemo.client.R;


/**
 * Created by jeepc on 2018/10/11.
 */
public class NonuseAidlActivity extends AppCompatActivity {

    private static final String ACTION = "com.jeepc.binderdemo.NonuseAidlService";
    private static final String PACKGE_NAME = "com.jeepc.binderdemo.server";

    TextView mTvResult;
    boolean isConnected;
    INonuseAidl mService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nonuse_aidl);
        mTvResult = findViewById(R.id.tv_result);
    }

    private final ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            isConnected = true;
            mService = NonuseAidlProxy.asInterface(service);
            showToast("已启动服务！");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isConnected = false;
        }
    };

    private void startService() {
        Intent intent = new Intent();
        intent.setAction(ACTION);
        intent.setPackage(PACKGE_NAME);
        bindService(intent,mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    private void showToast(String text) {
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show();
    }

    public void test(View view) {
        if(isConnected){
            try {
                if(mService!=null) {
                    String text = mService.int2String(111);
                    mTvResult.setText(text);
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }else {
            startService();
        }
    }

    private static final String LOCAL_ACTION = "com.jeepc.binderdemo.LocalService";
    private static final String LOCAL_PACKGE_NAME = "com.jeepc.binderdemo.client";
    boolean isConnectedLocal;
    INonuseAidl mLocalService;

    private final ServiceConnection mLocalServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            isConnectedLocal = true;
            mLocalService = NonuseAidlProxy.asInterface(service);
            showToast("已启动服务！");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isConnectedLocal = false;
        }
    };

    private void startLocalService() {
        Intent intent = new Intent();
        intent.setAction(LOCAL_ACTION);
        intent.setPackage(LOCAL_PACKGE_NAME);
        bindService(intent,mLocalServiceConnection, Context.BIND_AUTO_CREATE);
    }

    public void testLocalBinder(View view) {

        if(isConnectedLocal){
            try {
                if(mLocalService!=null) {
                    String text = mLocalService.int2String(222);
                    mTvResult.setText(text);
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }else {
            startLocalService();
        }
    }
}
