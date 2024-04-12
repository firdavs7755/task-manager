package uz.firdavs.taskmanager.payload.rs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.firdavs.taskmanager.payload.base.BaseId;

/**
 * Author            : Firdavsbek Maxsutaliyev
 * Date:       4/12/2024
 * Time:       10:27 PM
 * Project:    task-manager
 */


@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoleRsDto extends BaseId {
    private String name;
}
