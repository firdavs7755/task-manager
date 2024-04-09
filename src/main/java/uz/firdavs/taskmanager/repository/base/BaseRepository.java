package uz.firdavs.taskmanager.repository.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<TABLE,ID> extends JpaRepository<TABLE,ID> , JpaSpecificationExecutor<TABLE> {
}
