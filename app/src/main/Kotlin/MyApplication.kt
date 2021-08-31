package io.terameteo.listofaction

import android.app.Application
import androidx.room.Room
import io.terameteo.listofaction.model.ItemCollectionDB
import io.terameteo.listofaction.model.MyModel

class MyApplication : Application(){
    companion object {
        lateinit var _database: ItemCollectionDB
        val myDatabase get() = _database
    }

    override fun onCreate() {
        super.onCreate()
        val myModel = MyModel()
        val db = Room.databaseBuilder(this, ItemCollectionDB::class.java, "collection_item")
            .fallbackToDestructiveMigration()
            .build()

    }
}