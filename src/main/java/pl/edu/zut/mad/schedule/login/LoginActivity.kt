package pl.edu.zut.mad.schedule.login

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_login.*
import pl.edu.zut.mad.schedule.R
import pl.edu.zut.mad.schedule.util.app
import javax.inject.Inject


class LoginActivity : AppCompatActivity(), LoginMvp.View {

    @Inject lateinit var presenter: LoginMvp.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        init()
    }

    private fun init() {
        initInjections()
        initViews()
    }

    private fun initInjections() = app.component
            .plus(LoginModule(this))
            .inject(this)

    private fun initViews() =
            downloadScheduleButtonView.setOnClickListener { presenter.onDownloadScheduleClick() }

    override fun getAlbumNumberText() = albumNumberTextView.text.toString()

    override fun showError(errorResId: Int) {
        albumNumberLayoutView.error = resources.getString(errorResId)
    }

    override fun showLoading() {
        loadingView.visibility = View.VISIBLE
        albumNumberLayoutView.error = null
        albumNumberTextView.isEnabled = false
        downloadScheduleButtonView.isEnabled = false
    }

    override fun hideLoading() {
        loadingView.visibility = View.GONE
        albumNumberTextView.isEnabled = true
        downloadScheduleButtonView.isEnabled = true
    }

    override fun onDataSaved() {
        setResult(Activity.RESULT_OK)
        finish()
    }
}