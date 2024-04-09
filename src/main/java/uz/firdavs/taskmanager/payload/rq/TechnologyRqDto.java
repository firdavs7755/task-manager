package uz.firdavs.taskmanager.payload.rq;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class TechnologyRqDto {
    private String name;
    @NotNull
    private Integer technology_part_id;
}
