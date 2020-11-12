package fr.eni.tp.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import fr.eni.tp.entities.Article;

@Dao
public interface ArticleDAO {
    @Query("SELECT * FROM article")
    List<Article> get();

    @Query("SELECT * FROM article ORDER BY price")
    List<Article> getSorted();

    @Query("SELECT * FROM article WHERE id IN (:articleIDs)")
    List<Article> get(int... articleIDs);

    @Query("SELECT * FROM article WHERE id = :articleID")
    Article get(int articleID);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Article article);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insert(Article... articles);

    @Update
    void update(Article... articles);

    @Delete
    int delete(Article... article);
}
