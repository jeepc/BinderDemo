package com.jeepc.binderdemo.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.jeepc.binderdemo.ICallback;
import com.jeepc.binderdemo.ICommonBinder;
import com.jeepc.binderdemo.PersonBean;

/**
 * Created by jeepc on 2018/10/10.
 */
public class CommonService extends Service {
    private static final String TAG = "BinderDemoTag";

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private final ICommonBinder.Stub mBinder = new ICommonBinder.Stub() {

        @Override
        public String int2String(int i) throws RemoteException {
            return i+"---";
        }

        @Override
        public void testCallback(PersonBean p, ICallback callback) throws RemoteException {
            Log.i(TAG,p.toString());
            p.setAge(2222);
            p.setName("name2222");
            callback.onCallBack(p.toString());
        }

        @Override
        public void testInOut(PersonBean inP, PersonBean outP, PersonBean inoutP) throws RemoteException {
            Log.i(TAG,inP.toString());
            Log.i(TAG,outP.toString());
            Log.i(TAG,inoutP.toString());
            inP.setAge(2222);
            outP.setAge(2222);
            inoutP.setAge(2222);

        }
    };
}
