package com.sergeydevjava.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sergeydevjava.dto.common.CommonResponse;
import com.sergeydevjava.dto.common.ValidationError;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OtpControllerTest extends AbstractTest {

    public static final String API_V_1_OTP = "/api/v1/otp";

    @Test
    void createOtp() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(API_V_1_OTP + "/generateAndSend")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertFileToString("json/create-otp-request.json")))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void createOtpInvalid() throws Exception {
        ResultActions createOtp = mockMvc.perform(MockMvcRequestBuilders.post(API_V_1_OTP + "/generateAndSend")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertFileToString("json/create-otp-request-invalid-otp.json")))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        String jsonResponse = createOtp.andReturn().getResponse().getContentAsString();
        CommonResponse<?> commonResponse = objectMapper.readValue(jsonResponse, new TypeReference<CommonResponse<?>>() {});

        checkValidationError(commonResponse.getValidationErrors(), Set.of("body.resendAttempts", "body.length", "body.ttl", "body.resendTimeout", "body.telegramChatId", "body.processId", "body.message"));
    }

    @Test
    void checkOtpInvalid() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(API_V_1_OTP + "/check")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertFileToString("json/check-otp-request.json")))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void checkOtp() throws Exception {
        ResultActions checkOtp = mockMvc.perform(MockMvcRequestBuilders.post(API_V_1_OTP + "/check")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertFileToString("json/check-otp-request-invalid.json")))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        String jsonResponse = checkOtp.andReturn().getResponse().getContentAsString();
        CommonResponse<?> commonResponse = objectMapper.readValue(jsonResponse, new TypeReference<CommonResponse<?>>() {});

        checkValidationError(commonResponse.getValidationErrors(), Set.of("body.processId", "body.otp"));
    }

    private static void checkValidationError(List<ValidationError> commonResponse, Set<String> invalidFields) {
        assertEquals(invalidFields.size(), commonResponse.size());
        assertEquals(
                invalidFields,
                commonResponse.stream()
                        .map(ValidationError::getField)
                        .collect(Collectors.toSet())
        );
    }
}