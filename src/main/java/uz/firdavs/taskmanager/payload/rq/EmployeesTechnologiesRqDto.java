package uz.firdavs.taskmanager.payload.rq;

import lombok.Data;

@Data
public class EmployeesTechnologiesRqDto {
    private Integer emp_id;
    private Integer tech_id;
}
