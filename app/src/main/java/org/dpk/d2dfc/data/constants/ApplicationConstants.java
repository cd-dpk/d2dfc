package org.dpk.d2dfc.data.constants;

import android.os.Environment;

import org.dpk.d2dfc.data_models.Reporter;

public class ApplicationConstants {
    public static final long APP_INIT_TIME= 1586455200;
    public static long APP_TIME= APP_INIT_TIME;
    public static long SELECTED_FOLLOW_UP_DATE = APP_INIT_TIME;
    public static final String externalStorageFolder = Environment.getExternalStorageDirectory().toString();
    public static final String appFolder="d2dfc";
    public static final String FAMILY_PHONE_EXIST_ERROR = "Family Number Already Exists";
    public static final String FAMILY_MEMBER_PHONE_NAME_EXIST_ERROR = "Family Mobile and Name Exists";
    public static final String EMAIL_TYPE = "text/plain*";
    public static final String EMAIL_SUBJECT_DEV = "D2D Follow Corona- Feedback";
    public static final String EMAIL_SUBJECT_REPORT = "Reporting d2dfc Data";
    public static Reporter appReporter=new Reporter(
            RegistrationConstants.COMPLEX_VALUE,
            RegistrationConstants.COMPLEX_VALUE,
            RegistrationConstants.COMPLEX_VALUE);
    public static String SELECTED_FAMILY_PHONE = RegistrationConstants.COMPLEX_VALUE;
    public static String SELECTED_FAMILY_NAME = RegistrationConstants.COMPLEX_VALUE;
    public static String SELECTED_FAMILY_PERSON_NAME = RegistrationConstants.COMPLEX_VALUE;
    public static final String LANGUAGE_CODE_LABEL ="ln_code",
            LANGUAGE_CODE_BANGLA="bn",
            LANGUAGE_CODE_ENGLISH="en";
    public static String LANGUAGE_CODE =LANGUAGE_CODE_ENGLISH;

    public static final String ACCOUNT_EXIST_ERROR="account_exist";


    public static String filePath="";
}
