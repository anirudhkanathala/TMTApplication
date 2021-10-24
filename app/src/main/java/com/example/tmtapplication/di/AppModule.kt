package com.example.tmtapplication.di
import com.example.tmtapplication.api.RetrofitClient
import com.example.tmtapplication.api.datasource.TDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
/**
 * <h1>AppModule</h1>
 * AppModule is used to inject required classes
 *
 */

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideRecipeDataSource(
        client: RetrofitClient
    ): TDataSource = TDataSource(client)


    @Provides
    fun provideRetrofitClient(): RetrofitClient = RetrofitClient()

}
