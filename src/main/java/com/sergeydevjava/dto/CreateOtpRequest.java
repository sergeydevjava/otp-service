package com.sergeydevjava.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import java.util.UUID;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CreateOtpRequest {

    @NotNull(message = "Идентификатор процесса не может быть пустым")
    private UUID processId;

    @NotNull(message = "Идентификатор тереграм чата не может быть пустым")
    private String telegramChatId;

    @NotNull(message = "Текст сообщения не может быть пустым")
    private String message;

    @Range(min = 4, max = 8, message = "Длина одноразового пароля должна быть от 4 до 8")
    @NotNull(message = "Длина одноразового пароля не может быть пустым полем")
    private Integer length;

    @Min(value = 30, message = "Время жизни одноразового пароля должно быть не менее 30 секунд")
    @NotNull(message = "Время жизни одноразового пароля в секундах не может быть пустым")
    private Integer ttl;

    @Range(min = 1, max = 3, message = "Количество возможных повторных отправок кода должно быть от 1 до 3")
    @NotNull(message = "Количество возможных повторных отправок кода не может быть пустым")
    private Integer resendAttempts;

    @Min(value = 30, message = "Таймаут перед повторным запросом пароля должен быть не менее 30 секунд")
    @NotNull(message = "Таймаут перед повторным запросом пароля в секундах не может быть пустым")
    private Integer resendTimeout;

}
