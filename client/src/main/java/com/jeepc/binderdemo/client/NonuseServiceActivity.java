package com.jeepc.binderdemo.client;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jeepc.binderdemo.BinderCursor;
import com.jeepc.binderdemo.ICommonBinder;


/**
 * Created by jeepc on 2018/10/11.
 */
public class NonuseServiceActivity extends AppCompatActivity {

    TextView mTvResult;
    ICommonBinder mService;
    public static final String AUTHORITY = "com.jeepc.binderdemo.server.BinderContentProvider";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/test");
    public static Uri mCurrentURI = CONTENT_URI;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nonuse_service);
        mTvResult = findViewById(R.id.tv_result);
    }


    private void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    public void test(View view) {
        if(mService!=null) {
            try {
                String text = mService.int2String(111);
                mTvResult.setText(text);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }else {
            Cursor cursor = this.getContentResolver().query(mCurrentURI,null,null,null,null );
            IBinder iBinder = BinderCursor.getBinder(cursor);
            mService = ICommonBinder.Stub.asInterface(iBinder);
            showToast("已获取binder");
        }
    }
}
