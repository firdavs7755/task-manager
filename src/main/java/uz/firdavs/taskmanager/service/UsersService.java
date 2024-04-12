package uz.firdavs.taskmanager.service;


import uz.firdavs.taskmanager.dto.ResponseDto;
import uz.firdavs.taskmanager.payload.rq.ReqAuth;
import uz.firdavs.taskmanager.payload.rq.ReqUser;
import uz.firdavs.taskmanager.service.base.BaseService;

import javax.servlet.http.HttpServletRequest;

public interface UsersService extends BaseService {
    ResponseDto<?> getAllUsers();
    ResponseDto<?> login(HttpServletRequest request,ReqAuth reqAuth);
    ResponseDto<?> logout(HttpServletRequest request);
    ResponseDto<?> insertUser(ReqUser reqUser);
    ResponseDto<?> createRow(ReqUser reqUser);
    ResponseDto<?> updateUserById(ReqUser reqUser,Integer id);
    ResponseDto<?> deleteUserByID(Integer id);
}
