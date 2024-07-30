import android.app.Application
import app.cash.sqldelight.db.SqlDriver
import com.example.kmmnoteapp.data.local.DatabaseDriverFactory
import com.example.kmmnoteapp.data.note.SqlDelightNodeDataSource
import com.example.kmmnoteapp.domain.note.NoteDataSource
import com.example.kmmnoteapp.sqldelight.database.NoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSqlDriver(app: Application): SqlDriver {
        return DatabaseDriverFactory(app).createDriver()
    }

    @Provides
    @Singleton
    fun provideNoteDataSource(driver: SqlDriver): NoteDataSource {
        return SqlDelightNodeDataSource(NoteDatabase(driver))
    }
}