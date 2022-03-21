package app.service;

import app.dao.UserDao;
import app.dao.util.DaoManager;

import app.entities.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@AllArgsConstructor
public class UserService {
    private UserDao userDao;
    public User get(String login) {

        return DaoManager.getInstance().transaction(x -> userDao.getByLogin(x,login));
    }
}
