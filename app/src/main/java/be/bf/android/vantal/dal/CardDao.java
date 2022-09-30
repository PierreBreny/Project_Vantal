package be.bf.android.vantal.dal;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import be.bf.android.vantal.entities.Card;

@Dao
public interface CardDao {
    @Insert
    void insert(Card card);

    @Query("SELECT * FROM card")
    List<Card> getAllCard();

    @Query("SELECT * FROM card WHERE card_holder = :cardHolder")
    List<Card> getCardByUser(String cardHolder);
}
