package com.jeepc.binderdemo.server;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.jeepc.binderdemo.BinderCursor;
import com.jeepc.binderdemo.ICallback;
import com.jeepc.binderdemo.ICommonBinder;
import com.jeepc.binderdemo.NonuseAidlStub;
import com.jeepc.binderdemo.PersonBean;

/**
 * Created by jeepc on 2018/1/3.
 */

public class BinderContentProvider extends ContentProvider {

    public static class ProviderInfo {

        public static final String AUTHORITY = "com.jeepc.binderdemo.server.BinderContentProvider";
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/test");


    }

    private static final UriMatcher sUriMatcher;
    private static final int MATCH_FIRST = 1;
    private static final int MATCH_SECOND = 2;


    static {
        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sUriMatcher.addURI(ProviderInfo.AUTHORITY, "first", MATCH_FIRST);
        sUriMatcher.addURI(ProviderInfo.AUTHORITY, "second", MATCH_SECOND);
    }
    @Override
    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor cursor = BinderCursor.queryBinder(new ICommonBinder.Stub() {
            @Override
            public String int2String(int i) throws RemoteException {
                return i+"---";
            }

            @Override
            public void testCallback(PersonBean p, ICallback callback) throws RemoteException {

            }

            @Override
            public void testInOut(PersonBean inP, PersonBean outP, PersonBean inoutP) throws RemoteException {

            }
        });
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

}
