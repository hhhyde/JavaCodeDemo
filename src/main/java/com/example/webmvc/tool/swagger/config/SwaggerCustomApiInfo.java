package com.example.webmvc.tool.swagger.config;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;


public class SwaggerCustomApiInfo {

    private static String TITLE = "XX系统接口API";
    private static String DESCRIPTION = "XX系统描述";
    private static String API_VERSION = "1.0";
    private static String TERMS_OF_SERVICES_URL = "Terms of services URL";
    private static String CONTACT_NAME = "光头强";
    private static String CONTACT_URL = "联系地址";
    private static String CONTACT_EMAIL = "联系邮箱";
    private static String LICENSE = "License";
    private static String LICENSE_URL = "License 地址";

    private static Contact CONTACT = new Contact(CONTACT_NAME, CONTACT_URL, CONTACT_EMAIL);


    public static ApiInfo apiInfo() {
        return new ApiInfo(TITLE, DESCRIPTION, API_VERSION, TERMS_OF_SERVICES_URL, CONTACT,LICENSE, LICENSE_URL);
    }
}
