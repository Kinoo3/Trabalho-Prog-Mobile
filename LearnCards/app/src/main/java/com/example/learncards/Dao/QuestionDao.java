package com.example.learncards.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.learncards.Entities.Question;

import java.util.List;

@Dao
public interface QuestionDao extends BaseDao<Question>{

    @Query("DELETE FROM question")
    void deleteAllQuestions();

    @Query("SELECT * FROM question")
    List<Question> getAllQuestions();

    @Query("SELECT * FROM question WHERE card_fk = :cardId")
    List<Question> getAllQuestionOfCard(long cardId);
}
