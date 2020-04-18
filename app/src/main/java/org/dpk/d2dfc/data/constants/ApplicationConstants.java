package org.dpk.d2dfc.data.constants;

import org.dpk.d2dfc.data_models.Reporter;

public class ApplicationConstants {

    public static final String FAMILY_PHONE_EXIST_ERROR = "Family Number Already Exists";
    public static final String FAMILY_MEMBER_PHONE_NAME_EXIST_ERROR = "Family Mobile and Name Exists";
    public static Reporter appReporter=new Reporter(RegistrationConstants.COMPLEX_VALUE,RegistrationConstants.COMPLEX_VALUE,RegistrationConstants.COMPLEX_VALUE);
    public static String SELECTED_FAMILY_PHONE = appReporter.getPhone();
    public static final String LANGUAGE_CODE_LABEL ="ln_code",
            LANGUAGE_CODE_BANGLA="bn",
            LANGUAGE_CODE_ENGLISH="en";
    public static String LANGUAGE_CODE =LANGUAGE_CODE_ENGLISH;

    public static final String ACCOUNT_EXIST_ERROR="account_exist";


}
