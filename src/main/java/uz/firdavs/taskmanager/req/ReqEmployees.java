package uz.firdavs.taskmanager.req;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReqEmployees {
    @NotNull
    private String name;
    @NotNull
    private Integer departments_id;
    private List<Integer> idsList;

}
