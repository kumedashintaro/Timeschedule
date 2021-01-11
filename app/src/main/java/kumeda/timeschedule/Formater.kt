package kumeda.timeschedule

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun String.toDate(pattern: String = "HH:mm"): Date? {
    val sdFormat = try {
        SimpleDateFormat(pattern)
    } catch (e: IllegalArgumentException) {
        null
    }
    val date = sdFormat?.let {
        try {
            it.parse(this)
        } catch (e: ParseException) {
            null
        }
    }
    return date
}
