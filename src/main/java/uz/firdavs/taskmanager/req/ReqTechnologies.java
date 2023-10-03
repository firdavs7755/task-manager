package uz.firdavs.taskmanager.req;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReqTechnologies {
    private String name;
    @NotNull
    private Integer technology_part_id;
}
