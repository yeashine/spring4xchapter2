package cn.litten.service;

import cn.litten.dao.LoginLogDao;
import cn.litten.dao.UserDao;
import cn.litten.domain.LoginLog;
import cn.litten.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private UserDao userDao;
    private LoginLogDao loginLogDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setLoginLog(LoginLogDao loginLogDao) {
        this.loginLogDao = loginLogDao;
    }

    //判断是否有匹配的用户记录
    public boolean hasMatchUser(String username, String password) {
        Integer count = userDao.getMatchCount(username, password);
        return count > 0;
    }
    //根据用户名查找相关的用户信息
    public User findUserByUsername(String username){
        User user = userDao.findUserByUsername(username);
        return user;
    }
    //用户登录成功后，后台所做的操作
    @Transactional
    public void loginSuccess(User user){
        user.setCredits(5+user.getCredits());
        LoginLog loginLog = new LoginLog();
        loginLog.setIp(user.getLastIp());
        loginLog.setUserId(user.getUserId());
        loginLog.setLoginDate(user.getLastVisit());
        userDao.updateLoginInfo(user);
        loginLogDao.insertLoginLog(loginLog);
    }


}
