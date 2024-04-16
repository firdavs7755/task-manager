package uz.firdavs.taskmanager.utis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import uz.firdavs.taskmanager.config.UserPrincipal;
import uz.firdavs.taskmanager.dto.ResponseDto;
import uz.firdavs.taskmanager.entity.Users;
import uz.firdavs.taskmanager.mapper.base.BaseMapper;
import uz.firdavs.taskmanager.repository.base.BaseRepository;

import javax.swing.text.StyleContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * created by: Firdavsbek
 * Date:       23.03.2024
 * Time:       21:51
 * Project:    task-manager
 */

@Slf4j
public class Utils {
    public static <T,Dto> ResponseDto<?> generatePageable(BaseRepository<T, ?> repository, Specification<T> specification, BaseMapper<Dto,T> mapper,Map<String,Object> map) {
        Pageable pageable = generatePageable(map);
        Page<T> all = repository.findAll(specification, pageable);
        if (!all.isEmpty()){
            return new ResponseDto<>(
                    true,
                    "Данные доступны !",
                    mapper.toResponseList(all.getContent()),
                    all.getSize(),
                    all.getTotalPages(),
                    all.getTotalElements(),
                    all.getNumber(),
                    all.getSize()
            );
        }
        log.error("err. Данные недоступны ! cnt:"+all.getContent().size());
        return new ResponseDto<>(false,"Данные недоступны !",new ArrayList<>());
    }
    public static <T,Dto> ResponseDto<?> generatePageable(BaseRepository<T, ?> repository, BaseMapper<Dto,T> mapper,Map<String,Object> map) {
        Pageable pageable = generatePageable(map);
        Page<T> all = repository.findAll(pageable);
        if (!all.isEmpty()){
            return new ResponseDto<>(
                    true,
                    "Данные доступны !",
                    mapper.toResponseList(all.getContent()),
                    all.getSize(),
                    all.getTotalPages(),
                    all.getTotalElements(),
                    all.getNumber(),
                    all.getSize()
            );
        }
        log.error("err. Данные недоступны ! cnt:"+all.getContent().size());
        return new ResponseDto<>(false,"Данные недоступны !",new ArrayList<>());
    }

    public static <T,Dto> ResponseDto<?> generatePageable(BaseRepository<T, ?> repository,  BaseMapper<Dto,T> mapper) {
        List<T> all = repository.findAll();
        if (!all.isEmpty()){
            return new ResponseDto<>(
                    true,
                    "Данные доступны !",
                    mapper.toResponseList(all)
            );
        }
        log.error("err. Данные недоступны ! cnt:"+all.size());
        return new ResponseDto<>(false,"Данные недоступны !",new ArrayList<>());
    }

    public static Pageable generatePageable(Map<String, Object> map){
        if (!map.isEmpty()){
            if (map.get("page")!=null && map.get("rowsPerPage")!=null){
                if (Boolean.parseBoolean((String) map.get("descending"))){
                    return PageRequest.of(Integer.parseInt((String) map.get("page"))-1, Integer.parseInt((String) map.get("rowsPerPage")),
                            Sort.by((String) map.get("sortBy")).descending());
                }
                return PageRequest.of(Integer.parseInt((String) map.get("page"))-1, Integer.parseInt((String) map.get("rowsPerPage")),
                        Sort.by((String) map.get("sortBy")).ascending());
            } else {
                return PageRequest.of(0, 500, Sort.by("id").descending());
            }
        } else {
            return PageRequest.of(0, 500, Sort.by("id").descending());
        }
    }

    public static Users getUser(){
        UserPrincipal principal = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new Users(principal.getId(), principal.getUsername(), principal.getPassword(),principal.getFio(),principal.getPhone());
    }
    public static UserPrincipal getPrincipal(){
        UserPrincipal principal = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal;
    }

}
