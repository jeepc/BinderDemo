// ICommonBinder.aidl
package com.jeepc.binderdemo;
import com.jeepc.binderdemo.PersonBean;
import com.jeepc.binderdemo.ICallback;

// Declare any non-default types here with import statements

interface ICommonBinder {

    String int2String(int i);

    void testCallback(in PersonBean p,ICallback callback);

    void testInOut(in PersonBean inP,out PersonBean outP,inout PersonBean inoutP);
}
