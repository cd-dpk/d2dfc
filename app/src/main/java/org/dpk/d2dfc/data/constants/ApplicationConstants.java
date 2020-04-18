package org.dpk.d2dfc.data.constants;

import org.dpk.d2dfc.data_models.Reporter;

public class ApplicationConstants {

    public static Reporter appReporter=new Reporter(RegistrationConstants.COMPLEX_VALUE,RegistrationConstants.COMPLEX_VALUE,RegistrationConstants.COMPLEX_VALUE);
    public static final String LANGUAGE_CODE_LABEL ="ln_code",
            LANGUAGE_CODE_BANGLA="bn",
            LANGUAGE_CODE_ENGLISH="en";
    public static String LANGUAGE_CODE =LANGUAGE_CODE_ENGLISH;

    public static final String ACCOUNT_EXIST_ERROR="account_exist";


}
