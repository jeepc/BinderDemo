package com.jeepc.binderdemo.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.jeepc.binderdemo.ICallback;
import com.jeepc.binderdemo.ICommonBinder;
import com.jeepc.binderdemo.NonuseAidlStub;
import com.jeepc.binderdemo.PersonBean;

/**
 * Created by jeepc on 2018/10/10.
 */
public class NonuseAidlService extends Service {
    private static final String TAG = "BinderDemoTag";

    @Override
    public IBinder onBind(Intent intent) {
        return new NonuseAidlStub();
    }

}
