package com.rahulkashyap.calldetailsfromrecentcalllog.util;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.CallLog;
import android.util.Log;

import com.rahulkashyap.calldetailsfromrecentcalllog.util.model.CallLogInfo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import io.realm.Realm;

public class ReadCallLog {

    public static ReadCallLog instance;
    private Context context;
    private Realm realm;
    private ArrayList<CallLogInfo> mainList = null;

    public ReadCallLog(Context context) {
        this.context = context;
        realm = Realm.getDefaultInstance();
        loadData();
    }

    public void loadData() {

        mainList = new ArrayList<>();
        String projection[] = {"_id", CallLog.Calls.NUMBER, CallLog.Calls.DATE, CallLog.Calls.DURATION, CallLog.Calls.TYPE, CallLog.Calls.CACHED_NAME};
        ContentResolver contentResolver = context.getApplicationContext().getContentResolver();
        final Cursor cursor = contentResolver.query(CallLog.Calls.CONTENT_URI, projection, null, null, CallLog.Calls.DEFAULT_SORT_ORDER + " LIMIT 20");
//        CallLog.Calls.DEFAULT_SORT_ORDER
        if (cursor == null) {
            Log.d("CALLLOG", "cursor is null");
            return;
        }

        if (cursor.getCount() == 0) {
            Log.d("CALLLOG", "cursor size is 0");
            return;
        }

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            CallLogInfo callLogInfo = new CallLogInfo();
            callLogInfo.setName(cursor.getString(cursor.getColumnIndex(CallLog.Calls.CACHED_NAME)));
            callLogInfo.setNumber(cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER)));
            callLogInfo.setCallType(cursor.getString(cursor.getColumnIndex(CallLog.Calls.TYPE)));
            callLogInfo.setDate(cursor.getLong(cursor.getColumnIndex(CallLog.Calls.DATE)));
            callLogInfo.setDuration(cursor.getLong(cursor.getColumnIndex(CallLog.Calls.DURATION)));
            mainList.add(callLogInfo);

            cursor.moveToNext();
        }
        cursor.close();
    }

    public ArrayList<CallLogInfo> readCallLogs() {
        if (mainList == null)
            loadData();
        return mainList;
    }
}
