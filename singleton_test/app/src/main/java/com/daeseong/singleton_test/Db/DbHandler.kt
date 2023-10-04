import android.content.Context
import com.im.daeseong.singleton_test.Db.Alarm
import com.im.daeseong.singleton_test.Db.DbHelperAlarm

class DbHandler private constructor() {

    private var dbHelperAlarm: DbHelperAlarm? = null

    companion object {
        private var instance: DbHandler? = null

        @JvmStatic
        fun getInstance(): DbHandler {
            if (instance == null) {
                instance = DbHandler()
            }
            return instance!!
        }
    }

    fun init(context: Context) {
        dbHelperAlarm = DbHelperAlarm(context)
    }

    fun addAlarm(alarm: Alarm) {
        dbHelperAlarm?.addAlarm(alarm)
    }

    fun updateAlarm(alarm: Alarm) {
        dbHelperAlarm?.updateAlarm(alarm)
    }

    fun deleteAlarm(title: String) {
        dbHelperAlarm?.deleteAlarm(title)
    }

    fun deleteMaxData() {
        dbHelperAlarm?.deleteMaxData()
    }

    fun getMaxData(): Alarm? {
        return dbHelperAlarm?.getMaxData()
    }

    fun clearAlarm() {
        dbHelperAlarm?.clearAlarm()
    }

    fun getAlarmList(): ArrayList<Alarm>? {
        return dbHelperAlarm?.getAlarmList()
    }

    fun getRowCount(): Int {
        return dbHelperAlarm?.getRowCount() ?: 0
    }

    fun findAlarm(title: String): Boolean {
        return dbHelperAlarm?.findAlarm(title) ?: false
    }

    fun getAlarm(title: String): Alarm? {
        return dbHelperAlarm?.getAlarm(title)
    }
}
