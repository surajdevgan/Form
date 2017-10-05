package com.example.suraj.form.OfflineMode;

import android.net.Uri;

/**
 * Created by suraj on 16-09-2017.
 */

public class Util {
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "cpdemo.db";
    public static final String TAB_NAME = "Users";

    public static final String COL_ID = "_ID";
    public static final String COL_NAME = "NAME";
    public static final String COL_AGE = "AGE";

    public static final String CREATE_TAB_QUERY = "create table Users(" +
            "_ID integer primary key autoincrement,"+
            "NAME text," +
            "AGE text"+
            ")";


public static final Uri USER_URI = Uri.parse("content://com.example.suraj.form.OfflineMode.beancontentprovider/"+TAB_NAME);


}
