package io.terameteo.listofaction

import android.app.Application
import androidx.room.Room
import io.terameteo.listofaction.model.ItemCollectionDB
import io.terameteo.listofaction.model.MyModel

class MyApplication : Application(){

    lateinit var myDB: ItemCollectionDB
    lateinit var myModel: MyModel

    override fun onCreate() {
        super.onCreate()
        myModel = MyModel()
        myDB = Room.databaseBuilder(this, ItemCollectionDB::class.java, "collection_item")
            .fallbackToDestructiveMigration()
            .build()

    }
}