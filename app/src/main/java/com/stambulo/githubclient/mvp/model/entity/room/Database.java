package com.stambulo.githubclient.mvp.model.entity.room;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.stambulo.githubclient.GithubApplication;
import com.stambulo.githubclient.mvp.model.entity.room.dao.UserDao;

@androidx.room.Database(entities = {RoomGithubUser.class}, version = 1)
public abstract class Database extends RoomDatabase {

    private static final String DB_NAME = "database.db";
    private static volatile Database INSTANCE;

    public abstract UserDao userDao();

    public static Database getInstance(){
        Database refLocal = INSTANCE;

        if (refLocal == null){
            synchronized (Database.class){
                INSTANCE = refLocal;

                if (refLocal == null){
                    INSTANCE = refLocal = Room.databaseBuilder
                            (GithubApplication.INSTANCE, Database.class, DB_NAME).build();
                }
            }
        }

        return refLocal;
    }
}
