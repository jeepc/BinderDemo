package com.jeepc.binderdemo;

import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by jeepc on 2018/10/11.
 */
public class NonuseAidlStub extends Binder implements INonuseAidl{
    private static final String DESCRIPTOR = "com.jeepc.binderdemo.INonuseAidl";
    private static final int GET_STRING = 1000;

    public NonuseAidlStub(){
        attachInterface(this,DESCRIPTOR);
    }

    @Override
    public String int2String(int i) throws RemoteException {
        return i+"---";
    }

    @Override
    public IBinder asBinder() {
        return this;
    }

    @Override
    protected boolean onTransact(int code, @NonNull Parcel data, @Nullable Parcel reply, int flags) throws RemoteException {
        switch (code) {
            case GET_STRING:
                int i = data.readInt();
                String result = this.int2String(i);
                reply.writeString(result);
                return true;
            default:
                break;
        }

        return super.onTransact(code, data, reply, flags);
    }
}
