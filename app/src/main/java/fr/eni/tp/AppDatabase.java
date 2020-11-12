package fr.eni.tp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import fr.eni.tp.dao.ArticleDAO;
import fr.eni.tp.entities.Article;

@Database(entities = {Article.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase DB;

    public abstract ArticleDAO articleDAO();

    public static AppDatabase getAppDatabase(Context context) {
        if (DB == null) {
            DB = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "article-database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return DB;
    }

    public static void destroyDB() { DB = null; }
}