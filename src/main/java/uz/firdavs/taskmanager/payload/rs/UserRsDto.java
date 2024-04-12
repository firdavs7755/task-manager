package uz.firdavs.taskmanager.payload.rs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

/**
 * Author            : Firdavsbek Maxsutaliyev
 * Date:       4/12/2024
 * Time:       10:38 PM
 * Project:    task-manager
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRsDto {
    private Integer id;
    private String username;
    private String password;
    private String fio;
    private String phone;
}
