package uz.firdavs.taskmanager.req;



import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReqName {
     @NotNull
     private String name;
}
