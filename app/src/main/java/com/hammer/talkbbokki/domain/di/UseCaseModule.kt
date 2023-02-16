package com.hammer.talkbbokki.domain.di

import com.hammer.talkbbokki.domain.repository.CategoryLevelRepository
import com.hammer.talkbbokki.domain.repository.TopicRepository
import com.hammer.talkbbokki.domain.usecase.CategoryLevelUseCase
import com.hammer.talkbbokki.domain.usecase.TopicUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlinx.coroutines.Dispatchers

@InstallIn(SingletonComponent::class)
@Module
object UseCaseModule {
    @Provides
    @Singleton
    fun providesTopicUseCase(
        repository: TopicRepository
    ): TopicUseCase = TopicUseCase(repository, Dispatchers.IO)

    @Provides
    @Singleton
    fun providesCategoryLevelUseCase(
        repository: CategoryLevelRepository
    ): CategoryLevelUseCase = CategoryLevelUseCase(repository, Dispatchers.IO)
}
