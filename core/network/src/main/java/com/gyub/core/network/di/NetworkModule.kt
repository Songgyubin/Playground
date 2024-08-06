package com.gyub.core.network.di

import android.util.Log
import com.gyub.core.network.const.Http.Url.BASE_URL
import com.gyub.core.network.util.NetworkUtil
import com.gyub.core.network.util.NetworkUtil.getPrettyLog
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton

/**
 * 네트워크 모듈
 *
 * @author   Gyub
 * @created  2024/08/05
 */
@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {
    @Provides
    @Singleton
    fun providesNetworkJson(): Json = Json {
        ignoreUnknownKeys = true
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        networkJson: Json,
        okHttpClient: OkHttpClient,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(
                networkJson.asConverterFactory("application/json".toMediaType()),
            ).build()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(NetworkUtil.createHeader())
            .addInterceptor(NetworkUtil.createQuery())
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofitInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor { message ->
            Log.d("### Retrofit --", getPrettyLog(message))
        }.apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
}