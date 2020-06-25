package com.rahulkashyap.calldetailsfromrecentcalllog.network;

public interface RequestCallback {
    void error(NetworkError volleyError);

    void success(Object obj);
}
