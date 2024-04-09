package uz.firdavs.taskmanager.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultDTO<T> {
    @Schema()
    @NotNull
    private Integer code;

    @Schema()
    @NotNull
    @NotBlank
    private String message;
    @Schema()
    @NotNull
    @NotBlank
    private String devMessage;
    private T responseBody;

    public ResultDTO(Integer code, String msg) {
        this.code = code;
        this.message = msg;
    }

    public ResultDTO(Integer code, String message, String devMessage) {
        this.code = code;
        this.message = message;
        this.devMessage = devMessage;
    }

    public ResultDTO(Integer code, String message, T responseBody) {
        this.code = code;
        this.message = message;
        this.responseBody = responseBody;
    }
}