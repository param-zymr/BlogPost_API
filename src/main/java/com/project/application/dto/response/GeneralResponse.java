package com.project.application.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@NoArgsConstructor
public class GeneralResponse {

    private Integer customCode;
    private String message;
    private Object data;

    public GeneralResponse(final Integer customCode, final String message) {
        this.customCode = customCode;
        this.message = message;
    }

    public GeneralResponse(final Integer customCode, final String message, final Object data) {
        this.customCode = customCode;
        this.message = message;
        this.data = data;
    }
}
