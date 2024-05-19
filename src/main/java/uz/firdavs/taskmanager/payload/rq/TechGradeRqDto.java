package uz.firdavs.taskmanager.payload.rq;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class TechGradeRqDto {
    private Integer employee_id;
    private List<TechGrade> list;

}
