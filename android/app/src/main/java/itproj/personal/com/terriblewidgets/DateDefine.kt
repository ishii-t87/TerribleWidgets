package itproj.personal.com.terriblewidgets

/**
 *
 * @brief 日付を定義する構造体
 *
 * */
class DateDefine(year: Int = 1970, month: Int = 1, date: Int = 1, hourOfDay: Int = 0, minute: Int = 0) {

    private var mYear: Int = year
    private var mMonth: Int = month
    private var mDate: Int = date
    private var mHourOfDay: Int = hourOfDay
    private var mMinute: Int = minute

    init {
        checkIllegalValues()
    }

    /**
     * 不正な日時パラメータを検出した場合にexceptionを投げる
     **/
    private fun checkIllegalValues() {

        if (mYear < 1970) throw IllegalArgumentException()
        if (!(mMonth in 1..12)) throw IllegalArgumentException()
        if (!(mHourOfDay in 0..23)) throw IllegalArgumentException()
        if (!(mMinute in 0..59)) throw IllegalArgumentException()

        var isValidDate =   false
        when(mDate) {
            in 1..28 -> isValidDate = true
            29 -> {
                if (mMonth != 2 || mDate % 4 == 0) {
                    isValidDate = true
                }
            }
            30 -> {
                if (mMonth != 2) {
                    isValidDate = true
                }
            }
            31 -> {
                when(mMonth) {
                    1,3,5,7,8,10,12 -> isValidDate = true
                }
            }
        }

        if (isValidDate == false)  throw IllegalArgumentException()
    }
}
