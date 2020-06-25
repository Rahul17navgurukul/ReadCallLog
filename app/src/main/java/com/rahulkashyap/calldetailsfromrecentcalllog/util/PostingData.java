package com.rahulkashyap.calldetailsfromrecentcalllog.util;

import android.content.Context;

import com.rahulkashyap.calldetailsfromrecentcalllog.util.model.PendingCalls;

import java.util.Date;

import io.realm.Realm;

public class PostingData {

    private Context context;
    private Realm realm;
    private PendingCalls pendingCalls;


    public PostingData(Context context, String outGoing, Date callStartTime, Date date) {
        this.context = context;
        realm = Realm.getDefaultInstance();
        loadData(outGoing,callStartTime,date);
    }

    private void loadData(String outGoing, Date callStartTime, Date date) {


    }

    private void updateData(String number, String name, long duration) {
        pendingCalls = realm.where(PendingCalls.class).equalTo("status", "pending").findFirstAsync();
        realm.beginTransaction();
        pendingCalls.setNumber(number);
        pendingCalls.setName(name);
        pendingCalls.setDuration(duration);
        pendingCalls.setStatus("success");
        System.out.println("DataaUpdatedSuccess");
        realm.commitTransaction();
    }

}
