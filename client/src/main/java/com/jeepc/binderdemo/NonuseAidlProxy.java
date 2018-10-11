/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: D:\\projects\\demoProject\\BinderDemo\\client\\src\\main\\aidl\\com\\jeepc\\binderdemo\\ICommonBinder.aidl
 */
package com.jeepc.binderdemo;
// Declare any non-default types here with import statements

import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class NonuseAidlProxy implements INonuseAidl{
    private static final String TAG = "BinderDemoTag";

    private static final int GET_STRING = 1000;
    private static final String DESCRIPTOR = "com.jeepc.binderdemo.INonuseAidl";
    private android.os.IBinder mRemote;

    public NonuseAidlProxy(IBinder remote) {
        this.mRemote = remote;
    }

    public java.lang.String getInterfaceDescriptor() {
        return DESCRIPTOR;
    }


    @Override
    public String int2String(int i) throws RemoteException {
        android.os.Parcel data = android.os.Parcel.obtain();
        android.os.Parcel reply = android.os.Parcel.obtain();
        String result = "";
        try {
            data.writeInt(i);
            mRemote.transact(GET_STRING, data, reply,
                    0);
            result = reply.readString();
        }finally {
            data.recycle();
            reply.recycle();
        }
        return result;
    }

    @Override
    public IBinder asBinder() {
        return mRemote;
    }

    public static INonuseAidl asInterface(
            android.os.IBinder obj) {
        if ((obj == null)) {
            return null;
        }
        android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
        if (((iin != null) && (iin instanceof INonuseAidl))) {
            Log.e(TAG,"queryLocalInterface success!");
            return ((INonuseAidl) iin);
        }
        return new NonuseAidlProxy(obj);
    }
}
