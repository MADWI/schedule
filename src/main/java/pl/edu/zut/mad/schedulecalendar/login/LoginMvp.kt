package pl.edu.zut.mad.schedulecalendar.login


interface LoginMvp {

    interface View {
        fun showLoading()

        fun hideLoading()

        fun onDataSaved(albumNumber: Int)

        fun showError(message: String?)

        fun hideError()
    }

    interface Presenter {
        fun fetchScheduleForAlbumNumber(albumNumber: Int)

        fun cancelFetch()
    }
}