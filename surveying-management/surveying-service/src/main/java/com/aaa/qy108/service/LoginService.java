package com.aaa.qy108.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.aaa.qy108.base.BaseService;
import com.aaa.qy108.mapper.UserMapper;
import com.aaa.qy108.model.User;
import com.aaa.qy108.redis.RedisService;
import com.aaa.qy108.vo.TokenVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.aaa.qy108.constant.Redis.*;


/**
 * @Author guohang
 * @Description 登录功能的service层
 * @Date 2020/5/15 22:46
 */
@Service
public class LoginService extends BaseService<User> {

    @Autowired
    private UserMapper userMapper;

    /**
    * @Description: 执行登录操作
    * @Author: guohang
    * @Date: 2020/5/15 22:50
    * @Param: [user]
    * @return: com.aaa.qy108.vo.TokenVo
    */
    public TokenVo doLogin(User user, RedisService redisService){
        TokenVo tokenVo = new TokenVo();
        //先判断user是否为空
        if (null != user){
            //不为空则验证账号密码是否正确
            User usr = userMapper.selectOne(user);
            //判断从数据库中查询出来的是否为空
            if (null != usr && !"".equals(usr)){
                //登录成功，生成一个uuid当做token
                String token = IdUtil.simpleUUID();
                User updUsr = new User();
                //设置id、登录时间、token，修改根据主键修改不为null的字段
                updUsr.setToken(token).setLastLoginTime(DateUtil.now()).setId(usr.getId());
                int updateResult = userMapper.updateByPrimaryKeySelective(updUsr);
                //判断token是否更新成功
                if (updateResult > 0){
                    String setResult = redisService.set(String.valueOf(usr.getId()), token, XX, EX, 1800);
                    if (OK.equals(setResult.toUpperCase()) || ONE.equals(setResult)){
                        return tokenVo.setToken(token).setIfSuccess(true).setRedisKey(String.valueOf(usr.getId()));
                    }
                }
            }
        }
        return tokenVo.setIfSuccess(false);
    }




}



