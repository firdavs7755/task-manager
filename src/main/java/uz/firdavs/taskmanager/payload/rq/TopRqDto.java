package uz.firdavs.taskmanager.payload.rq;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TopRqDto {
    private String name;
    private String description;

}
