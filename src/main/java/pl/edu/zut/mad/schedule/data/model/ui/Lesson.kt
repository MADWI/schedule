package pl.edu.zut.mad.schedule.data.model.ui

import pl.edu.zut.mad.schedule.data.model.db.TimeRange


data class Lesson(val teacherWithRoom: String, val subjectWithType: String,
                  val isCancelled: Boolean, val timeRange: TimeRange)