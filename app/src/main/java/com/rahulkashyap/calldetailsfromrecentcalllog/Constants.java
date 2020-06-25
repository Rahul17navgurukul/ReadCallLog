package com.rahulkashyap.calldetailsfromrecentcalllog;

public class Constants {
    /* Stagging URL*/

//    public static final String BASE_URL ="http://134.209.158.179:8069";

    public static final String BASE_URL = "http://159.65.145.32";
    public static final String LoginUrl = BASE_URL + "/api/login";
    public static final String ApiSendBugPostUrl = "http://134.209.158.179:5001/api/send";
    public static final String ApiCallSummaryPortUrl = BASE_URL + "/api/call/summary";
    public static final String GetCallSumarry = BASE_URL + "/api/call/summary/";
    public static final String ApiRecodingPostUrl = BASE_URL + "/api/file/upload/";
    public static final String ApiRecodingGetUrl = BASE_URL + "/api/recording/details/";
    public static final String ApiLocationPostUrl = BASE_URL + "/api/geo/tracking/";
    public static final String ApiLocationGetUrl = BASE_URL + "/api/geofence/details/";
    public static final String ApiAttendecePostUrl = BASE_URL + "/api/attendence";
    public static final String ApiAttendenceGetUrl = BASE_URL + "/api/user/attendence/";
    public static final String ApiDashboadGetUrl = BASE_URL + "/api/dashboard/";
    public static final String ApiMyTeamGetUrl = BASE_URL + "/api/myteam/";
    public static final String ApiMyTeamPostUrl = BASE_URL + "/api/account_activation_mail/";
    public static final String AuthToken = "Basic RVRTQmFzaWNBdXRoOjc4QEg0IzFw";
    public static final String Authorization = "Authorization";
    public static final String SerachUrl = BASE_URL + "/api/search/recording/";
    public static final String ChangePassword = BASE_URL + "/api/change/password";
//    public static final String VERSION_URL = BASE_URL + ":5002/api/getApk/";
    public static final String VERSION_URL = "http://159.65.145.32:5002/api/getApk/";
    public static final String DEVICE_DETAILS_URL = BASE_URL+"/api/device/detail";
    public static final String TERMS_CONDITIONS = "http://employeetrackingsolution.com/termsandcondition";
    public static final String USERID = "";
    public static final String COMPANY_PROFILE = BASE_URL+"/api/company/profile/";
    public static final String SHARED_URL = BASE_URL+"/api/shared/profile";
    public static final String SHARED_HISTORY_URL = BASE_URL+"/api/shared/profile/";

    public static final String SENDEMAIL ="employeetrackingsolution@gmail.com"; //your-gmail-username
    public static final String SENDPASSWORD ="ets@employee0045375"; //your-gmail-password




    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String USER_ID = "user_id";
    public static final String FILE_NAME = "file_name";
    public static final String CREATION_TIME = "creation_datetime";
    public static final String CALL_TYPE = "call_type";
    public static final String PROSPECT_NUMBER = "prospect_number";
    public static final String FILE = "file";
    public static final String START_DATE = "start_date";
    public static final String END_DATE = "end_date";
    public static final String TALK_TIME = "talk_time";
    public static final String END_DATETIME = "end_datetime";
    public static final String START_DATETIME = "start_datetime";
    public static final String FILE_PATH = "file_path";
    public static final String USER_NAME = "user_name";
    public static final String MODEL = "model";
    public static final String API_VERSION = "api_version";
    public static final String BRAND = "brand";
    public static final String APK = "apk";

    public static final String LATITUDE = "latitude";
    public static final String LONGTUDE = "longitude";
    public static final String USER_COLOR = "user_color";

    public static final String FILE_ID = "file_id";
    public static final String REGION = "region";
    public static final String DURATION = "duration";
    public static final String OUTGOING = "out_going";
    public static final String INCOMING = "incoming";
    public static final String MISSED_CALL = "missed_call";
    public static final String PLEASE_WAIT = "Please wait...";

    public static final String COMPANY_ID = "company_id";
    public static final String SHARED_NUM = "prospect_number";
    public static final String COMPANY_NAME = "company_name";
    public static final String COMPANY_LINK = "company_link";
    public static final String PRODUCT_LINK = "product_link";
    public static final String INDIVIDUAL_LINK = "individual_link";
    public static final String WEBSITE_LINK = "website";
    public static final String SHARED_DATETITME = "shared_datetime";



    public interface DatabaseConstant {
        public static final String DATABASE_NAME = "ETS.db";
        public static final String TABLE_CALL_DETAILS = "call_details";
        public static final String CALL_DETAILS_USER_ID = "user_id";
        public static final String CALL_DETAILS_PROSPECT_NUMBER = "prospect_number";
        public static final String CALL_DETAILS_PROSPECT_NUMBER_NAME = "prospect_number_name";
        public static final String CALL_DETAILS_CALL_TYPE = "call_type";
        public static final String CALL_DETAILS_TALK_TIME = "talk_time";
        public static final String CALL_DETAILS_START_DATE = "start_datetime";
        public static final String CALL_DETAILS_END_DATE = "end_datetime";
        public static final String CALL_DETAILS_STATUS = "status";
        public static final String CALL_DETAILS_PENDING = "pending";
        public static final String CALL_DETAILS_SUCCESS = "success";

        // Recording
        public static final String TABLE_CALL_RECORDING = "call_recordings";
        public static final String CALL_RECORDING_USER_ID = "user_id";
        public static final String CALL_RECORDING_FILE_NAME = "file_name";
        public static final String CALL_RECORDING_FILE_PATH = "file_path";
        public static final String CALL_RECORDING_CREATION_DATE = "creation_date";
        public static final String CALL_RECORDING_STATUS = "status";
        public static final String CALL_RECORDING_PENDING = "pending";
        public static final String CALL_RECORDING_SUCCESS = "success";


    }

}
