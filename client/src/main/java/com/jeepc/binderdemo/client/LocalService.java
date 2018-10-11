package com.jeepc.binderdemo.client;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.jeepc.binderdemo.NonuseAidlStub;

public class LocalService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new NonuseAidlStub();
    }

}
