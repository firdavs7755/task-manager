package uz.firdavs.taskmanager.projections;


public interface EmployeesProjection {
    Integer getId();
    String getName();
    Integer getDepartments_id();
    Integer getTechnology_part_id();
    String getTechnology_part_name();
    String getDepartments_name();
    Integer getCreated_user_id();
    String getCreated_user_name();
    Integer getWish_id();
    String getWish_name();

//    @Column(columnDefinition = "jsonb")
//    @Type(type = "jsonbType")
    String getSkills();
    String getSkillsId();
    String getProjects();
    String getProjects_id();
    String getGrades();
    String getGrade_ids();
}
