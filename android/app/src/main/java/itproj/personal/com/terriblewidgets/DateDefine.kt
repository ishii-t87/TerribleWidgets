package itproj.personal.com.terriblewidgets

import android.support.annotation.IntRange

/**
 *
 * @brief 日付を定義する構造体
 *
 * */
class DateDefine(@IntRange(from = 1970)year: Int = 1970,
                 @IntRange(from = 1, to = 12)month: Int = 1,
                 @IntRange(from = 1, to = 31)date: Int = 1,
                 @IntRange(from = 0, to = 23)hourOfDay: Int = 0,
                 @IntRange(from = 0, to = 59)minute: Int = 0) {

    var mYear: Int = year

    var mMonth: Int = month

    var mDate: Int = date
        set(value) {
            mDate = value
            checkIllegalValues()
        }

    var mHourOfDay: Int = hourOfDay

    var mMinute: Int = minute

    init {
        checkIllegalValues()
    }

    /**
     * 不正な日時パラメータを検出した場合にexceptionを投げる
     **/
    private fun checkIllegalValues() {

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
