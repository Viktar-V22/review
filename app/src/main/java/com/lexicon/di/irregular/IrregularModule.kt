package com.lexicon.di.irregular

import android.content.res.AssetManager
import com.boundaries.base.images.ImagesRepository
import com.boundaries.base.trains.TrainsResultRepository
import com.boundaries.base.trains.TrainsSettingsStore
import com.boundaries.irregulars.Irregular
import com.boundaries.irregulars.IrregularsRepository
import com.core.common.Mapper
import com.data.base.db.irregulars.IrregularVerb
import com.data.base.db.irregulars.IrregularVerbsDao
import com.data.irregular.DbToIrregular
import com.data.irregular.IrregularsRepositoryImpl
import com.data.irregular.ServerIrregular
import com.data.irregular.ServerToIrregularVerb
import com.domain.irregular.InteractorImpl
import com.domain.irregular.StateCase
import com.domain.irregular.StateCaseImpl
import com.interactors.irregular.Interactor
import com.interactors.irregular.NavTarget
import com.lexicon.di.FeatureScope
import com.lexicon.di.base.trains.IrregularStore
import com.lexicon.navigation.IrregularNavigation
import com.presentation.core.Navigation
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface IrregularModule {

    @Module
    companion object {

        @Provides
        @FeatureScope
        fun provideIrregularsRepository(
            assets: AssetManager,
            irregulars: IrregularVerbsDao,
            @ToIrregular
            toIrregularVerb: Mapper<ServerIrregular, IrregularVerb>,
            @ToDbIrregular
            toDbIrregular: Mapper<IrregularVerb, Irregular>
        ): IrregularsRepository {
            return IrregularsRepositoryImpl(assets, irregulars, toIrregularVerb, toDbIrregular)
        }

        @Provides
        @FeatureScope
        fun provideStateCase(
            irregulars: IrregularsRepository,
            images: ImagesRepository,
            results: TrainsResultRepository,
            @IrregularStore
            settings: TrainsSettingsStore
        ): StateCase = StateCaseImpl(irregulars, images, results, settings)
    }

    @Binds
    @FeatureScope
    fun provideInteractor(interactor: InteractorImpl): Interactor

    @Binds
    @FeatureScope
    @ToIrregular
    fun provideToIrregular(mapper: ServerToIrregularVerb): Mapper<ServerIrregular, IrregularVerb>

    @Binds
    @FeatureScope
    @ToDbIrregular
    fun provideToDbIrregular(mapper: DbToIrregular): Mapper<IrregularVerb, Irregular>

    @Binds
    @FeatureScope
    fun provideNavigation(navigation: IrregularNavigation): Navigation<NavTarget>
}