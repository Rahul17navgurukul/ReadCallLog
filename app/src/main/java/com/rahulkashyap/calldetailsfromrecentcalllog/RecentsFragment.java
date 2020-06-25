package com.rahulkashyap.calldetailsfromrecentcalllog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rahulkashyap.calldetailsfromrecentcalllog.activity.MainActivity;
import com.rahulkashyap.calldetailsfromrecentcalllog.adapter.RecyclerViewAdapter;
import com.rahulkashyap.calldetailsfromrecentcalllog.util.ReadCallLog;
import com.rahulkashyap.calldetailsfromrecentcalllog.util.model.PendingCalls;

public class RecentsFragment extends Fragment {

    private static final int LOADER_ID = 1;
    private static final String ARG_PHONE_NUMBER = "phone_number";
    private static final String ARG_CONTACT_NAME = "contact_name";

    LinearLayoutManager mLayoutManager;
//    RecentsAdapter mRecentsAdapter;
    private ReadCallLog readCallLog;
    PendingCalls pendingCalls;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recents, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycler_view);
        readCallLog = new ReadCallLog(getContext());
        getDataFromDb();

    }

    private void getDataFromDb() {
        adapter = new RecyclerViewAdapter(readCallLog.readCallLogs(), getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

    }



}

