package be.bf.android.vantal.dal;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import be.bf.android.vantal.entities.Card;

@Database(entities = {Card.class}, version = 2)
public abstract class CardDB extends RoomDatabase {
    public abstract CardDao cardDao();

    private static CardDB instance;

    public static CardDB instance(Context context) {
        if (instance == null) {
            instance = Room
                    .databaseBuilder(context, CardDB.class, "CardDb")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }

        return instance;
    }
}
