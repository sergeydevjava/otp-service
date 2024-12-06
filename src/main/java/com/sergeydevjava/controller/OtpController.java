package com.sergeydevjava.controller;


import com.sergeydevjava.dto.CheckOtpRequest;
import com.sergeydevjava.dto.CreateOtpRequest;
import com.sergeydevjava.dto.common.CommonRequest;
import com.sergeydevjava.dto.common.CommonResponse;
import com.sergeydevjava.service.OtpService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/otp")
public class OtpController {

    private final OtpService otpService;

    @PostMapping("/generateAndSend")
    public CommonResponse<?> generateAndSendOtp(@RequestBody @Valid CommonRequest<CreateOtpRequest> createOtpRequest) {
        otpService.generateOtp(createOtpRequest.getBody());

        return CommonResponse.builder()
                .build();
    }

    @PostMapping("/check")
    public CommonResponse<?> checkOtp(@RequestBody @Valid CommonRequest<CheckOtpRequest> checkOtpRequest) {
        otpService.checkOtp(checkOtpRequest.getBody());

        return CommonResponse.builder()
                .build();
    }
}
