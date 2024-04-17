package uz.firdavs.taskmanager.payload.rq;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class EmployeeRqDto{
    @NotNull
    private String name;
    @NotNull
    private Integer departments_id;
    private Integer wish_id;
    private List<Integer> idsList;
    private List<Integer> idProjects;

}
