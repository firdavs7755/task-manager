package uz.firdavs.taskmanager.service;


import uz.firdavs.taskmanager.dto.ResponseDto;
import uz.firdavs.taskmanager.payload.ReqAuth;
import uz.firdavs.taskmanager.payload.ReqUser;

import javax.servlet.http.HttpServletRequest;

public interface UsersService {
    ResponseDto<?> getAllUsers();
    ResponseDto<?> login(HttpServletRequest request,ReqAuth reqAuth);
    ResponseDto<?> logout(HttpServletRequest request);
    ResponseDto<?> insertUser(ReqUser reqUser);
    ResponseDto<?> updateUserById(ReqUser reqUser,Integer id);
    ResponseDto<?> deleteUserByID(Integer id);
}
