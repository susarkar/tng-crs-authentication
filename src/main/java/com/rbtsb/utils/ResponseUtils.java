package com.rbtsb.utils;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.DataInput;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class ResponseUtils {

    private static MessageSource messageSource;

    @Autowired
    public ResponseUtils(MessageSource messageSource) {
        ResponseUtils.messageSource = messageSource;
    }

    public static ResponseEntity<byte[]> getAttachment(byte[] media, HttpHeaders headers) throws IOException {
        if (headers == null) headers = new HttpHeaders();
        headers.setCacheControl(CacheControl.noCache().getHeaderValue());
        return new ResponseEntity<>(media, headers, HttpStatus.OK);
    }

    public static ResponseEntity<?> sendError(Exception e, HttpStatus status) {
        String message = messageSource.getMessage("entity.unprocessable.entity", null, null);
        if (e instanceof RuntimeException) {
            message = e.getMessage();
            status = HttpStatus.BAD_REQUEST;
        }
        if (e instanceof ChangeSetPersister.NotFoundException) {
            message = e.getMessage();
            status = HttpStatus.NOT_FOUND;
        }
        return sendError(message, status);
    }

    public static ResponseEntity<?> sendError(Exception e) {
        return sendError(e.getMessage(), null);
    }

    public static ResponseEntity<?> sendError(String message, HttpStatus status) {
        Map<String, Object> results = new HashMap<>();
        results.put("timestamp", DateUtil.formatISO8601(new Date()));
        results.put("status", status.value());
        results.put("message", message);
        results.put("error", status);

        return new ResponseEntity<>(results, status != null ? status : HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<?> sendSuccess(String message) {
        Map<String, Object> results = new HashMap<>();
        results.put("message", message);
        return new ResponseEntity<>(results,  HttpStatus.OK);
    }
}
