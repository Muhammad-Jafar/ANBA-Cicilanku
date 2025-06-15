package app.cicilan

import android.app.Application
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.preferencesDataStoreFile
import app.cicilan.local.db.provideDao
import app.cicilan.local.db.provideDatabase
import app.cicilan.local.preference.StoreData
import app.cicilan.navigation.DetailViewModel
import app.cicilan.navigation.FormViewModel
import app.cicilan.navigation.HomeViewModel
import app.cicilan.navigation.MainActivity
import app.cicilan.navigation.SettingsViewModel
import app.cicilan.navigation.home.HomeFragment
import app.cicilan.navigation.home.MainListFragment
import app.cicilan.navigation.home.detail.DetailFragment
import app.cicilan.navigation.home.detail.DetailLogFragment
import app.cicilan.navigation.settings.about.AboutFragment
import app.cicilan.navigation.settings.donate.DonateFragment
import app.cicilan.repositories.contracts.CicilanLogRepository
import app.cicilan.repositories.contracts.CicilanRepository
import app.cicilan.repositories.contracts.CicilanViewerRepository
import app.cicilan.repositories.contracts.SettingRepository
import app.cicilan.repositories.repository.CicilanLogRepoImpl
import app.cicilan.repositories.repository.CicilanRepoImpl
import app.cicilan.repositories.repository.CicilanViewerRepoImpl
import app.cicilan.repositories.repository.SettingRepoImpl
import app.cicilan.usecases.CountCicilanUseCase
import app.cicilan.usecases.GetListCicilanLogUseCase
import com.google.android.material.color.DynamicColors
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.fragment.dsl.fragment
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
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

        val fragmentViewModule =
            module {
                scope<MainActivity> {
                    fragment { HomeFragment() }
                    fragment { MainListFragment() }
                    fragment { DetailFragment() }
                    fragment { DetailLogFragment() }
                    fragment { AboutFragment() }
                    fragment { DonateFragment() }
                }
            }

        val dispatcherKoinModule =
            module {
                single { Dispatchers.IO }
                single { CoroutineScope(SupervisorJob() + Dispatchers.IO) }
            }

        val databaseModule =
            module {
                single { provideDatabase(androidContext()) }
                single { provideDao(get()) }
            }

        val dataStoreModule =
            module {
                single {
                    PreferenceDataStoreFactory.create {
                        androidContext().preferencesDataStoreFile("CicilanDataStore")
                    }
                }
                single { StoreData(get()) }
            }

        val repositoryModule =
            module {
                singleOf(::CicilanRepoImpl) { bind<CicilanRepository>() }
                singleOf(::CicilanViewerRepoImpl) { bind<CicilanViewerRepository>() }
                singleOf(::CicilanLogRepoImpl) { bind<CicilanLogRepository>() }
                singleOf(::SettingRepoImpl) { bind<SettingRepository>() }
            }

        val usecaseModule =
            module {
                singleOf(::GetListCicilanLogUseCase)
                singleOf(::CountCicilanUseCase)
            }

        val viewModelModule =
            module {
                viewModelOf(::HomeViewModel)
                viewModelOf(::DetailViewModel)
                viewModel { FormViewModel(get()) }
                viewModel { DetailViewModel(/*get(), get()*/) }
                viewModel { SettingsViewModel(get()) }
            }

        startKoin {
            androidLogger()
            androidContext(this@AppModule)
            fragmentFactory()
            modules(
                listOf(
                    dispatcherKoinModule,
                    fragmentViewModule,
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
