package uz.firdavs.taskmanager.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDto<T> {
    private Integer count;
    private Integer code;
    private String message;
    private String token;
    private Integer page;
    private Integer pageSize;
    private Integer totalPages;
    private Long totalElements;
    private Boolean success;
    private T data;

    public ResponseDto(Boolean success) {
        this.success = success;
    }

    public ResponseDto(Boolean success,String message, String token, T data) {
        this.message = message;
        this.token = token;
        this.success = success;
        this.data = data;
    }

    public ResponseDto(Boolean success, Integer code, String message) {
        this.code = code;
        this.message = message;
        this.success = success;
    }

    public ResponseDto( Boolean success,String message) {
        this.message = message;
        this.success = success;
    }

    public ResponseDto(Boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public ResponseDto(Boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public ResponseDto( Boolean success, T data,Integer count) {
        this.success = success;
        this.data = data;
        this.count = count;
    }
    public ResponseDto( Boolean success,String message, T data,Integer count,Integer totalPages,Long totalElements,Integer page,Integer pageSize) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.count = count;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.page = page;
        this.pageSize = pageSize;
    }
}
