package com.stambulo.githubclient.mvp.model.entity.room;

import androidx.room.RoomDatabase;

import com.stambulo.githubclient.mvp.model.entity.room.dao.RepositoryDao;
import com.stambulo.githubclient.mvp.model.entity.room.dao.UserDao;

@androidx.room.Database(entities = {RoomGithubUser.class, RoomGithubRepository.class}, version = 1)
public abstract class Database extends RoomDatabase {
    public static final String DB_NAME = "database.db";

    public abstract UserDao userDao();
    public abstract RepositoryDao repositoryDao();
}
