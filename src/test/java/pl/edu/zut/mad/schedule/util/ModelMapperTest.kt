package pl.edu.zut.mad.schedule.util

import com.tngtech.java.junit.dataprovider.DataProviderRunner
import com.tngtech.java.junit.dataprovider.UseDataProvider
import org.assertj.core.api.Assertions.assertThat
import org.joda.time.LocalDate
import org.junit.Test
import org.junit.runner.RunWith
import pl.edu.zut.mad.schedule.MockData
import pl.edu.zut.mad.schedule.MockData.Companion.DATE_DAY
import pl.edu.zut.mad.schedule.MockData.Companion.DATE_MONTH
import pl.edu.zut.mad.schedule.MockData.Companion.DATE_YEAR
import pl.edu.zut.mad.schedule.MockData.Companion.SUBJECT_WITH_TYPE
import pl.edu.zut.mad.schedule.MockData.Companion.TEACHER_WITH_ROOM
import pl.edu.zut.mad.schedule.MockData.Companion.TIME_END
import pl.edu.zut.mad.schedule.MockData.Companion.TIME_START
import pl.edu.zut.mad.schedule.data.model.ui.EmptyDay
import pl.edu.zut.mad.schedule.data.model.api.Day as DayApi
import pl.edu.zut.mad.schedule.data.model.ui.Day as DayUi

@RunWith(DataProviderRunner::class)
internal class ModelMapperTest {

    private val modelMapper = ModelMapper()

    @Test
    @UseDataProvider("dayApi", location = arrayOf(MockData::class))
    fun dayUiDayIsEqualToDayApiDay(dayApi: DayApi) {
        val dayUi = modelMapper.toDayUiFromApi(dayApi)

        assertThat(dayUi.date.dayOfMonth).isEqualTo(DATE_DAY)
    }

    @Test
    @UseDataProvider("dayApi", location = arrayOf(MockData::class))
    fun dayUiMonthIsEqualToDayApiMonth(dayApi: DayApi) {
        val dayUi = modelMapper.toDayUiFromApi(dayApi)

        assertThat(dayUi.date.monthOfYear).isEqualTo(DATE_MONTH)
    }

    @Test
    @UseDataProvider("dayApi", location = arrayOf(MockData::class))
    fun dayUiYearIsEqualToDayApiYear(dayApi: DayApi) {
        val dayUi = modelMapper.toDayUiFromApi(dayApi)

        assertThat(dayUi.date.year).isEqualTo(DATE_YEAR)
    }

    @Test
    @UseDataProvider("dayApi", location = arrayOf(MockData::class))
    fun dayUiHasProperSubjectWithType(dayApi: DayApi) {
        val dayUi = modelMapper.toDayUiFromApi(dayApi)
        val lesson = dayUi.lessons[0]

        assertThat(lesson.subjectWithType).isEqualTo(SUBJECT_WITH_TYPE)
    }

    @Test
    @UseDataProvider("dayApi", location = arrayOf(MockData::class))
    fun dayUiHasProperTeacherWithRoom(dayApi: DayApi) {
        val dayUi = modelMapper.toDayUiFromApi(dayApi)
        val lesson = dayUi.lessons[0]

        assertThat(lesson.teacherWithRoom).isEqualTo(TEACHER_WITH_ROOM)
    }

    @Test
    @UseDataProvider("dayApi", location = arrayOf(MockData::class))
    fun dayUiTimerRangeFromIsEqualToTimeStart(dayApi: DayApi) {
        val dayUi = modelMapper.toDayUiFromApi(dayApi)
        val lesson = dayUi.lessons[0]

        assertThat(lesson.timeRange.from).isEqualTo(TIME_START)
    }

    @Test
    @UseDataProvider("dayApi", location = arrayOf(MockData::class))
    fun dayUiTimerRangeToIsEqualToTimeEnd(dayApi: DayApi) {
        val dayUi = modelMapper.toDayUiFromApi(dayApi)
        val lesson = dayUi.lessons[0]

        assertThat(lesson.timeRange.to).isEqualTo(TIME_END)
    }

    @Test
    @UseDataProvider("dayApi", location = arrayOf(MockData::class))
    fun dayUiLessonsIsCancelledIsFalse(dayApi: DayApi) {
        val dayUi = modelMapper.toDayUiFromApi(dayApi)
        val lesson = dayUi.lessons[0]

        assertThat(lesson.isCancelled).isFalse()
    }

    @Test
    @UseDataProvider("dayApiWithCancelledLesson", location = arrayOf(MockData::class))
    fun lessonIsCancelledIsTrue(dayApi: DayApi) {
        val dayUi = modelMapper.toDayUiFromApi(dayApi)
        val lesson = dayUi.lessons[0]

        assertThat(lesson.isCancelled).isTrue()
    }

    @Test
    @UseDataProvider("dayUi", location = arrayOf(MockData::class))
    fun lessonEventHasEventIsTrue(dayUi: DayUi) {
        val lessonEvents = modelMapper.toLessonsEventsFromDayUi(dayUi)

        assertThat(lessonEvents[0].hasEvent()).isTrue()
    }

    @Test
    fun emptyDayEventIsNull() {
        val date = LocalDate.now()
        val emptyDay = EmptyDay(date)

        val lessonEvent = modelMapper.toLessonEventFromEmptyDay(emptyDay)

        assertThat(lessonEvent.event).isNull()
    }

    @Test
    fun emptyDayHasEventIsFalse() {
        val date = LocalDate.now()
        val emptyDay = EmptyDay(date)

        val lessonEvent = modelMapper.toLessonEventFromEmptyDay(emptyDay)

        assertThat(lessonEvent.hasEvent()).isFalse()
    }
}