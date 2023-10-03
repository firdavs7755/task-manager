package uz.firdavs.taskmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto<T> {
    private Boolean success;

    private String message;

    private T data;

    public ResponseDto(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
