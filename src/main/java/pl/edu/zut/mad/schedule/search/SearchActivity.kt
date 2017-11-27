package pl.edu.zut.mad.schedule.search

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_search.*
import pl.edu.zut.mad.schedule.R
import pl.edu.zut.mad.schedule.data.model.ui.Day
import pl.edu.zut.mad.schedule.util.app
import javax.inject.Inject

internal class SearchActivity : AppCompatActivity(), SearchMvp.View {

    @Inject internal lateinit var presenter: SearchMvp.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        init()
    }

    private fun init() {
        initInjections()
        initViews()
    }

    private fun initInjections() = app.component
        .plus(SearchModule(this))
        .inject(this)

    private fun initViews() {
        searchButton.setOnClickListener {
            presenter.onSearch()
        }
        teacherNameInputView.setText("Piotr")
        teacherSurnameInputView.setText("Piela")
        facultyAbbreviationInputView.setText("WI")
        subjectInputView.setText("Modelowanie i symulacja systemów")
    }

    override fun getTeacherName() = teacherNameInputView.text.toString()

    override fun getTeacherSurname() = teacherSurnameInputView.text.toString()

    override fun getFacultyAbbreviation() = facultyAbbreviationInputView.text.toString()

    override fun getSubject() = subjectInputView.text.toString()

    override fun onScheduleDownloaded(days: List<Day>) { // TODO: mapped to lessons with date by parameter
        val lessonsAdapter = LessonsAdapter() // TODO: consider by constructor instead of setter
        lessonsListView.adapter = lessonsAdapter
        lessonsAdapter.setDays(days)
    }
}