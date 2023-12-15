package app.sanao1006.tsundoku.data.di

import android.content.Context
import androidx.room.Room
import app.sanao1006.tsundoku.data.db.TsundokuDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideTsundokuDatabase(
        @ApplicationContext context: Context
    ) : TsundokuDb = Room.databaseBuilder(
        context = context,
        klass = TsundokuDb::class.java,
        name = "tsundoku-database"
    ).fallbackToDestructiveMigration().build()
}
