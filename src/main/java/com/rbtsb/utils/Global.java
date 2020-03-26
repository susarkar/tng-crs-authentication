package com.rbtsb.utils;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.time.format.DateTimeFormatter;

/**
 * Constant Variables
 *
 * @author Kent
 */
public interface Global {

    /**
     * Constant variables for web return views
     */
    final static View JSON_VIEW = new MappingJackson2JsonView();

    /**
     * Constant variables for web return data labels
     */
    String REPORT_PATH = "report_path";
    final static String WEB_DATA_LABEL = "data";
    final static String WEB_DRAW_LABEL = "draw";
    final static String WEB_RECORD_TOTAL_LABEL = "recordsTotal";
    final static String WEB_RECORD_FILTERED_LABEL = "recordsFiltered";
    final static String WEB_ERROR_LABEL = "errorMessage";

    /**
     * Date format for DateUtils to parseDate
     * e.g. DateUtils.parseDate("12/12/2012", Global.dateFormat[0])
     */
    final static String[] dateFormat = {"dd/MM/yyyy", "dd-MM-yyyy", "yyyy/MM/dd", "yyyy-MM-dd"};

    final static String JSON_DATE_FORMAT = "dd/MM/yyyy";

    final static int VARCHAR2000 = 2000;
    final static int VARCHAR255 = 255;
    final static int VARCHAR128 = 128;
    final static int VARCHAR100 = 100;
    final static int VARCHAR64 = 64;
    final static int VARCHAR50 = 50;
    final static int VARCHAR32 = 32;
    final static int VARCHAR30 = 30;
    final static int VARCHAR16 = 16;
    final static int VARCHAR15 = 15;
    final static int VARCHAR10 = 10;
    final static int VARCHAR8 = 8;
    final static int VARCHAR6 = 6;
    final static int VARCHAR5 = 5;
    final static int VARCHAR4 = 4;
    final static int VARCHAR3 = 3;
    final static int VARCHAR2 = 2;

    final static String PASSWORD_PATTERN = "((?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()-+]).{8,20})";
    final static int LOGIN_ATTEMPTS = 5;

    final static String ROLE_SUPER_ADMIN = "ROLE_SUPER_ADMIN";

    final static String usernameParams = "j_username";
    final static String passwordParams = "j_password";


    final static String MAP_KEY = "key";
    final static String MAP_VALUE = "value";

    final static String ISO_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    DateTimeFormatter ISO_DATE_FORMATTER = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

    final static String RECOVERY_FILE_PATH = "RECOVERY_FILE_PATH";
    final static String RECOVERY_EMAIL = "RECOVERY_EMAIL";
    final static String ALERT_EMAIL = "ALERT_EMAIL";
    final static String AUTO_REFRESH_INTERVAL_MS = "AUTO_REFRESH_INTERVAL_MS";
    final static String REPORT_REPO_PATH = "REPORT_REPO_PATH";
    final static String MAX_TIME_NUM = "MAX_TIME_NUM";

    String WHITE_SPACE = " ";
    final static String TAG_UPDATE_THRESHOLD_MI = "TAG_UPDATE_THRESHOLD_MI";
    final static String VECTOR_UPDATE_THRESHOLD_MI = "VECTOR_UPDATE_THRESHOLD_MI";
    final static String WALLET_UPDATE_THRESHOLD_MI = "WALLET_UPDATE_THRESHOLD_MI";
    final static String TX_READY_TO_VECTOR_THRESHOLD_MIN = "TX_READY_TO_VECTOR_THRESHOLD_MIN";
    final static String LAST_SYNC_THRESHOLD_MI = "LAST_SYNC_THRESHOLD_MI";

    final static String TAG_UPDATE_EMAIL_MI = "TAG_UPDATE_EMAIL_MI";
    final static String VECTOR_UPDATE_EMAIL_MI = "VECTOR_UPDATE_EMAIL_MI";
    final static String WALLET_UPDATE_EMAIL_MI = "WALLET_UPDATE_EMAIL_MI";
    final static String TX_READY_TO_VECTOR_EMAIL_MIN = "TX_READY_TO_VECTOR_EMAIL_MIN";

    final static String WALLET_FETCH_UPDATE_PERIOD = "WALLET_FETCH_UPDATE_PERIOD";
    final static String VECTOR_FETCH_UPDATE_PERIOD = "VECTOR_FETCH_UPDATE_PERIOD";

    String T_TYPE_BU = "BU";
    String T_TYPE_BR = "BR";
    String T_TYPE_TP = "TP";
    String T_TYPE_CLOSE_TOLL = "C";
}

