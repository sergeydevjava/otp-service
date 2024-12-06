package com.sergeydevjava.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CheckOtpRequest {

    @NotNull(message = "Идентификатор процесса не может быть пустым")
    private UUID processId;

    @NotNull(message = "Одноразовый код не может быть пустым")
    private String otp;

}
