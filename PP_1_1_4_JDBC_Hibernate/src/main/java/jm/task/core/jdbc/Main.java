package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

public class Main {
    public static void main(String[] args) {

//        UserDao userDao = new UserDaoHibernateImpl();
        UserDao userDao = new UserDaoJDBCImpl();

        userDao.createUsersTable();
        userDao.saveUser("����", "������", (byte) 20);
        userDao.saveUser("�����", "�������", (byte) 30);
        userDao.saveUser("����", "������", (byte) 40);
        userDao.saveUser("Michael", "Jackson", (byte) 50);
//        userDao.removeUserById(2);
        userDao.getAllUsers();
        userDao.cleanUsersTable();
        userDao.dropUsersTable();
    }
}