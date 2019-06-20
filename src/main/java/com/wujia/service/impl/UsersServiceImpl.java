package com.wujia.service.impl;

import com.wujia.entity.Users;
import com.wujia.dao.UsersMapper;
import com.wujia.service.UsersService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 吾嘉
 * @since 2019-06-20
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {

}
