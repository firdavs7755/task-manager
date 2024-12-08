package uz.firdavs.taskmanager.payload.rq;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseRqDto {
    private String name;
    private String description;
    private Long price;
    private String link;
    private String login;
    private String password;
}
