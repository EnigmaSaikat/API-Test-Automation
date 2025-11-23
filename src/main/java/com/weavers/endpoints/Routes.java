package com.weavers.endpoints;

import com.weavers.config.ConfigReader;

public class Routes {

        private static final String BASE_URI = ConfigReader.getBaseUrl();
        private static final String API_VERSION = ConfigReader.getApiVersion();
        private static final String AUTH_SERVICE = ConfigReader.getAuthServices();

        private static final String AUTH_BASE_PATH = BASE_URI + "/" + API_VERSION + "/" + AUTH_SERVICE;

        public static final String LOGIN_URI = AUTH_BASE_PATH + ConfigReader.getLoginEndpoint();
        public static final String REGISTER_URI = AUTH_BASE_PATH + ConfigReader.getRegisterEndpoint();
        public static final String REQUEST_OTP_URI = AUTH_BASE_PATH + ConfigReader.getRequestOTP();
}
