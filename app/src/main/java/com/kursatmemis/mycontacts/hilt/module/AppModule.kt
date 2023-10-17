package com.kursatmemis.mycontacts.hilt.module

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.kursatmemis.mycontacts.view.adapter.PersonAdapter
import com.kursatmemis.mycontacts.config.AppDataBase
import com.kursatmemis.mycontacts.dao.PersonDao
import com.kursatmemis.mycontacts.repository.RoomRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providePersonDao(@ApplicationContext context: Context): PersonDao {
        return Room.databaseBuilder(context, AppDataBase::class.java, "AppDataBase")
            .build().personDao()
    }

    @Provides
    @Singleton
    fun provideRoomRepository(personDao: PersonDao) : RoomRepository {
        return RoomRepository(personDao)
    }

}