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
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jeepc.binderdemo.ICallback;
import com.jeepc.binderdemo.ICommonBinder;
import com.jeepc.binderdemo.PersonBean;
import com.jeepc.binderdemo.client.R;

/**
 * Created by jeepc on 2018/10/10.
 */
public class CommonActivity extends AppCompatActivity {

    private static final String TAG = "BinderDemoTag";

    private static final String ACTION = "com.jeepc.binderdemo.CommonService";
    private static final String PACKGE_NAME = "com.jeepc.binderdemo.server";


    TextView mTvResult;
    boolean isConnected;
    ICommonBinder mService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);
        mTvResult = findViewById(R.id.tv_result);
    }

    private final ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            isConnected = true;
            mService = ICommonBinder.Stub.asInterface(service);
            showToast("已启动服务！");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isConnected = false;
        }
    };

    private void startCommonService() {
        Intent intent = new Intent();
        intent.setAction(ACTION);
        intent.setPackage(PACKGE_NAME);
        bindService(intent,mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    //通常情况
    public void testCommon(View view) {
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
            startCommonService();
        }

    }

    //回调
    public void testCallback(View view) {
        if(isConnected){
            try {
                if(mService!=null) {
                    PersonBean p = new PersonBean();
                    p.setName("name1111");
                    p.setAge(1111);
                    mService.testCallback(p, new ICallback.Stub(){

                        @Override
                        public void onCallBack(String result) throws RemoteException {
                            Log.i(TAG,result);
                        }
                    });
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }else {
            startCommonService();
        }
    }

    //in out
    public void testInOut(View view) {
        if(isConnected){
            try {
                if(mService!=null) {
                    PersonBean inP = new PersonBean();
                    inP.setAge(111);
                    PersonBean outP = new PersonBean();
                    outP.setAge(111);
                    PersonBean inoutP = new PersonBean();
                    inoutP.setAge(111);
                    mService.testInOut(inP,outP,inoutP);
                    Log.i(TAG,inP.toString());
                    Log.i(TAG,outP.toString());
                    Log.i(TAG,inoutP.toString());
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }else {
            startCommonService();
        }
    }


    private void showToast(String text) {
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show();
    }

}
