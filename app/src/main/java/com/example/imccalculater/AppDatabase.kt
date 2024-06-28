package com.example.imccalculater

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.imccalculater.model.Calc
import com.example.imccalculater.model.CalcDao


@Database (entities = [Calc::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class AppDatabase:RoomDatabase() {
    abstract fun calcDao():CalcDao
    companion object{

        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context):AppDatabase{
        return if (INSTANCE == null){
            synchronized(this){
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "IMC Calculater"
                ).build()
            }
           INSTANCE as AppDatabase
        }else{
            INSTANCE as AppDatabase
        }
        }
    }
}

