package pl.edu.zut.mad.schedule.search

import dagger.Module
import dagger.Provides
import pl.edu.zut.mad.schedule.data.ScheduleService
import pl.edu.zut.mad.schedule.util.ModelMapper
import pl.edu.zut.mad.schedule.util.NetworkConnection

@Module
internal class SearchModule(private val view: SearchMvp.View) {

    @Search
    @Provides
    fun providePresenter(service: ScheduleService, modelMapper: ModelMapper,
        networkConnection: NetworkConnection): SearchMvp.Presenter =
        SearchPresenter(view, service, modelMapper, networkConnection)
}
