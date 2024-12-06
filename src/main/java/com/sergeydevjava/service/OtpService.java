package com.sergeydevjava.service;


import com.sergeydevjava.dto.CheckOtpRequest;
import com.sergeydevjava.dto.CreateOtpRequest;

public interface OtpService {

    void generateOtp(CreateOtpRequest createLinkInfoRequest);

    void checkOtp(CheckOtpRequest checkOtpRequest);
}
