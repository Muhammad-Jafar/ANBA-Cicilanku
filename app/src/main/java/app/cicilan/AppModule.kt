package app.cicilan

import android.app.Application
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.room.Room
import app.cicilan.db.CicilanDb
import app.cicilan.preference.StoreData
import app.cicilan.presentation.DetailViewModel
import app.cicilan.presentation.HomeViewModel
import app.cicilan.presentation.MainActivity
import app.cicilan.presentation.home.HomeFragment
import app.cicilan.presentation.home.MainListFragment
import app.cicilan.presentation.home.detail.DetailFragment
import app.cicilan.presentation.home.detail.DetailLogFragment
import app.cicilan.presentation.settings.about.AboutFragment
import app.cicilan.presentation.settings.donate.DonateFragment
import app.cicilan.repositories.CicilanLogRepository
import app.cicilan.repositories.CicilanRepository
import app.cicilan.repositories.CicilanViewerRepository
import app.cicilan.repository.CicilanLogRepoImpl
import app.cicilan.repository.CicilanRepoImpl
import app.cicilan.repository.CicilanViewerRepoImpl
import app.cicilan.usecases.CountCicilanUseCase
import app.cicilan.usecases.GetListCicilanLogUseCase
import com.google.android.material.color.DynamicColors
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.fragment.dsl.fragment
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.context.startKoin
import org.koin.dsl.module

/**
 * Created by: Muhammad Jafar
 * At: 06 Nov 23
 * Find me: 131.powerfull@gmail.com
 */

class AppModule : Application() {
    override fun onCreate() {
        super.onCreate()

        DynamicColors.applyToActivitiesIfAvailable(this)

        val fragmentModule = module {
            scope<MainActivity> {
                fragment { HomeFragment() }
                fragment { MainListFragment() }
                fragment { DetailFragment() }
                fragment { DetailLogFragment() }
                fragment { AboutFragment() }
                fragment { DonateFragment() }
            }
        }

        val databaseModule = module {
            single {
                Room.databaseBuilder(androidContext(), CicilanDb::class.java, "cicilan")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            single { get<CicilanDb>().cicilanDao() }
        }

        val dataStoreModule = module {
            single {
                PreferenceDataStoreFactory.create {
                    androidContext().preferencesDataStoreFile("CicilanDataStore")
                }
            }
            single { StoreData(get()) }
        }

        val interfaceModule = module {

        }

        val repositoryModule = module {
            single<CicilanRepository> { CicilanRepoImpl(get(), get()) }
            single<CicilanLogRepository> { CicilanLogRepoImpl(get()) }
            single<CicilanViewerRepository> { CicilanViewerRepoImpl(get()) }
        }

        val usecaseModule = module {
            factory { GetListCicilanLogUseCase(get()) }
            factory { CountCicilanUseCase(get()) }
        }

        val viewModelModule = module {
            viewModelOf(::HomeViewModel)
            viewModelOf(::DetailViewModel)
//            viewModel { FormViewModel(get()) }
//            viewModel { DetailViewModel(get(), get()) }
//            viewModel { SettingsViewModel(get()) }
        }

        startKoin {
            androidContext(this@AppModule)
            fragmentFactory()
            modules(
                listOf(
                    fragmentModule,
                    databaseModule,
                    dataStoreModule,
                    repositoryModule,
                    usecaseModule,
                    viewModelModule,
                ),
            )
        }
    }
}
