package pl.edu.zut.mad.schedule.login

import dagger.Module
import dagger.Provides
import pl.edu.zut.mad.schedule.User
import pl.edu.zut.mad.schedule.data.ScheduleRepository
import pl.edu.zut.mad.schedule.data.ScheduleService
import pl.edu.zut.mad.schedule.module.RepositoryModule
import pl.edu.zut.mad.schedule.module.ServiceModule
import pl.edu.zut.mad.schedule.module.UserModule
import pl.edu.zut.mad.schedule.util.NetworkConnection
import pl.edu.zut.mad.schedule.util.MessageProvider
import javax.inject.Singleton


@Module(includes = arrayOf(
        UserModule::class,
        ServiceModule::class,
        RepositoryModule::class
))
class LoginModule(private val view: LoginMvp.View) {

    @Provides
    @Singleton
    fun provideTextProvider() = MessageProvider()

    @Provides
    @Singleton
    fun provideLoginPresenter(service: ScheduleService, repository: ScheduleRepository,
                              messageProvider: MessageProvider, connection: NetworkConnection,
                              user: User): LoginMvp.Presenter =
            LoginPresenter(view, repository, service, connection, messageProvider, user)
}