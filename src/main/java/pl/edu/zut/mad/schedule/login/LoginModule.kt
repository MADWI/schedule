package pl.edu.zut.mad.schedule.login

import dagger.Module
import dagger.Provides
import pl.edu.zut.mad.schedule.User
import pl.edu.zut.mad.schedule.data.ScheduleRepository
import pl.edu.zut.mad.schedule.data.ScheduleService
import pl.edu.zut.mad.schedule.util.NetworkConnection

@Module
internal class LoginModule(private val view: LoginMvp.View) {

    @Login
    @Provides
    fun provideLoginPresenter(service: ScheduleService, repository: ScheduleRepository,
                              messageProvider: MessageProviderLogin, connection: NetworkConnection,
                              user: User): LoginMvp.Presenter =
            LoginPresenter(view, repository, service, connection, messageProvider, user)

    @Login
    @Provides
    fun provideMessageProviderLogin() = MessageProviderLogin()
}
