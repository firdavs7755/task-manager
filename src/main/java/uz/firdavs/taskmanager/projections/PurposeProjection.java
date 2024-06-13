package uz.firdavs.taskmanager.projections;


import java.util.List;

public interface PurposeProjection {
    Integer getEmployee_id();
    String getEmployee_name();
    Integer getPurpose_id();
    String getPurpose_name();

    String getTechnology();
    String getTechnology_ids();
    String getBackend();
    String getBackend_ids();
    String getFrontend();
    String getFrontend_ids();
    String getTester();
    String getTester_ids();

}
