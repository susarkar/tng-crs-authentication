//package com.rbtsb.config;
//
//import feign.RequestInterceptor;
//import feign.RequestTemplate;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//@Component
//public class FeignClientInterceptor implements RequestInterceptor {
//
//    private static final String AUTHORIZATION_HEADER = "Authorization";
//    private static final String TOKEN_TYPE = "Bearer";
//
//    @Override
//    public void apply(RequestTemplate requestTemplate) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication != null && authentication.getDetails() instanceof MyUserDetailsService) {
////            MyUserDetailsService details = (MyUserDetailsService) authentication.getDetails();
//            String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTU4NjE3MzkwOCwiaWF0IjoxNTg2MTM3OTA4fQ.A6W_lY1xm6X1KmvlvuJ5rCwJcf8cBK5r9oAu9l4XlBM";
//            requestTemplate.header(AUTHORIZATION_HEADER, String.format("%s %s", TOKEN_TYPE, token));
//        }
//    }
//}
