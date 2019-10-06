package com.nevertoolate.injection.component

import com.nevertoolate.architecture.BaseView
import com.nevertoolate.architecture.PostPresenter
import com.nevertoolate.injection.ContextModule
import com.nevertoolate.injection.NetworkModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [(ContextModule::class), (NetworkModule::class)])
interface PresenterInjector {
    /**
     * Injects required dependencies into the specified PostPresenter
     * @param cardPresenter PostPresenter in which to inject the dependencies
     */
    fun inject(cardPresenter: PostPresenter)

    @Component.Builder
    interface Builder {
        fun build(): PresenterInjector

        fun networkModule(networkModule: NetworkModule): Builder
        fun contextModule(contextModule: ContextModule): Builder

        @BindsInstance
        fun baseView(baseView: BaseView): Builder
    }
}