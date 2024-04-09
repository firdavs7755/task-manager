package uz.firdavs.taskmanager.payload.rq;



import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class NameRq {
     @NotNull
     private String name;
}
