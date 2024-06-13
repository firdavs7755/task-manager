package uz.firdavs.taskmanager.payload.rq;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TopLikeRqDto {
    private Integer top_id;
    private Integer employee_id;

}
