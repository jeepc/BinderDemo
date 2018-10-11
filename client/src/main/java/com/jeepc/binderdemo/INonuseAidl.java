package com.jeepc.binderdemo;

import android.os.IInterface;
import android.os.RemoteException;

/**
 * Created by jeepc on 2018/10/11.
 */
public interface INonuseAidl extends IInterface {
    String int2String(int i) throws RemoteException;
}
