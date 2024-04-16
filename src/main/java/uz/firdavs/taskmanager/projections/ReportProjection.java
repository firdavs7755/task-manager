package uz.firdavs.taskmanager.projections;

public interface ReportProjection {

    Integer getId();
    String getName();
    Integer getEmps_cnt();
    Integer getFront_cnt();
    Integer getBack_cnt();
    Integer getWants_new_project();
    Integer getNo_wants_new_project();



}
