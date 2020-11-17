package com.lexicon.di.base.words

import android.content.res.AssetManager
import com.boundaries.base.words.Word
import com.boundaries.base.words.WordsRepository
import com.core.common.Mapper
import com.data.base.db.words.WordsDao
import com.data.base.words.DbToWord
import com.data.base.words.ServerToDbWord
import com.data.base.words.ServerWord
import com.data.base.words.WordsRepositoryImpl
import com.lexicon.di.FeatureScope
import dagger.Binds
import dagger.Module
import dagger.Provides
import com.data.base.db.words.Word as DbWord

@Module
interface WordsModule {

    companion object {
        @Provides
        @FeatureScope
        fun provideWordRepository(
            assets: AssetManager,
            words: WordsDao,
            @ToDbWord
            toDbWord: Mapper<ServerWord, DbWord>,
            @ToWord
            toWord: Mapper<DbWord, Word>
        ): WordsRepository = WordsRepositoryImpl(assets, words, toDbWord, toWord)
    }

    @Binds
    @FeatureScope
    @ToDbWord
    fun provideToDbWord(mapper: ServerToDbWord): Mapper<ServerWord, DbWord>

    @Binds
    @FeatureScope
    @ToWord
    fun provideToWord(mapper: DbToWord): Mapper<DbWord, Word>
}