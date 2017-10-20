package pl.edu.zut.mad.schedulecalendar

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.TextView
import com.ognev.kotlin.agendacalendarview.models.CalendarEvent
import com.ognev.kotlin.agendacalendarview.render.DefaultEventAdapter
import pl.edu.zut.mad.schedulecalendar.data.model.ui.Lesson
import pl.edu.zut.mad.schedulecalendar.data.model.ui.LessonEvent
import java.text.SimpleDateFormat
import java.util.*


internal class LessonsAdapter(private val context: Context) : DefaultEventAdapter() {

    companion object {
        private val DATE_FORMAT = SimpleDateFormat("EEEE, d MMMM", Locale.getDefault())
    }

    override fun getHeaderLayout() = R.layout.lesson_header

    override fun getHeaderItemView(view: View, day: Calendar) {
        view.findViewById<TextView>(R.id.lessonDayView).text = DATE_FORMAT.format(day.time)
    }

    override fun getEventLayout(isEmptyEvent: Boolean) =
            if (isEmptyEvent) {
                R.layout.lesson_item
            } else {
                R.layout.no_lessons_item
            }

    override fun getEventItemView(view: View, event: CalendarEvent, position: Int) {
        val lessonEvent = event as LessonEvent
        if (!lessonEvent.hasEvent()) {
            return
        }
        if (position % 2 == 0) {
            colorBackgroundToGray(view)
        }
        val lesson = lessonEvent.event as Lesson
        with(lesson) {
            view.findViewById<TextView>(R.id.timeStartView).text = timeRange.from
            view.findViewById<TextView>(R.id.timeEndView).text = timeRange.to
            view.findViewById<TextView>(R.id.subjectWithTypeView).text = subjectWithType
            view.findViewById<TextView>(R.id.teacherWithRoomView).text = teacherWithRoom
        }
    }

    private fun colorBackgroundToGray(view: View) {
        val itemViewColor = ContextCompat.getColor(context, R.color.scheduleLightGray)
        val timeViewColor = ContextCompat.getColor(context, R.color.scheduleColorPrimaryDark)
        val scheduleTaskItemView = view.findViewById<View>(R.id.scheduleTaskItemView)
        val timeGroupView = view.findViewById<View>(R.id.timeGroupView)
        scheduleTaskItemView.setBackgroundColor(itemViewColor)
        timeGroupView.setBackgroundColor(timeViewColor)
    }
}