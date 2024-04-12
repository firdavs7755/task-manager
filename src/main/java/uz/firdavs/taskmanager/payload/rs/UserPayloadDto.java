package uz.firdavs.taskmanager.payload.rs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * Author            : Firdavsbek Maxsutaliyev
 * Date:       4/13/2024
 * Time:       2:29 AM
 * Project:    task-manager
 */


@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserPayloadDto {
    private List<Map<String,Object>> userRoles;
    private Map<String,Object> user;
}
