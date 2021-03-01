package com.mvp.utils

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.Settings
import android.text.Html
import android.text.InputFilter
import android.text.Spanned
import android.text.TextUtils
import android.util.DisplayMetrics
import android.util.Patterns
import android.view.inputmethod.InputMethodManager
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.google.gson.JsonElement
import com.mvp.common.BaseActivity
import com.mvp.utils.AppConstants
import io.reactivex.Observable
import org.json.JSONObject
import java.io.*
import java.net.URLConnection
import java.net.URLEncoder
import java.text.DateFormat
import java.text.DecimalFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.Callable
import java.util.regex.Pattern

class Utilities {

    companion object {

        fun isStringValid(info: String?): Boolean {
            return info != null && info.trim { it <= ' ' }.isNotEmpty()
        }

        fun isNotEmpty(data: Collection<Any?>?): Boolean {
            return data != null && data.isNotEmpty()
        }

        fun isNotEmpty(responseObject: Any?): Boolean {
            return responseObject != null
        }

        fun isJsonNotEmpty(element: JsonElement?): Boolean {
            return element != null && !element.isJsonNull
        }

        fun isNotEmpty(path: String?): Boolean {
            return path != null && path.trim { it <= ' ' }.isNotEmpty()
        }

        fun isValidEmail(email: String?): Boolean {
            return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }

        fun isValidPassword(password: String): Boolean {
            val passRegex =
                "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$"
            val pattern = Pattern.compile(passRegex)
            val matcher = pattern.matcher(password)
            return matcher.matches()
        }

        fun isValidPhoneNumber(phone: String): Boolean {
            val passRegex = "^(?:(?:\\+|0{0,2})91(\\s*[\\-]\\s*)?|[0]?)?[6789]\\d{9}\$"
            val pattern = Pattern.compile(passRegex)
            val matcher = pattern.matcher(phone)
            return matcher.matches()
        }

        fun hasJsonKey(key: String?, `object`: JSONObject): Boolean {
            return `object`.has(key)
        }

        fun isNetworkAvailable(context: Context): Boolean {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting
        }

        fun getDeviceId(context: Context): String? {
            return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
        }

        fun isWebUri(path: String): Boolean {
            return path.startsWith("http")
        }


        fun getRoundUpNumber(number: Double?): String? {
            val df =
                DecimalFormat("#####################################################################.################################################")
            return df.format(number)
        }

        fun prepareImageUrl(path: String, url: String): String? {
            return "$path/$url"
        }

        fun convertToDateFormat(updatedDate: String?): String? {
            if (!isStringValid(updatedDate)) {
                return "Cannot parse"
            }
            val date = Date(java.lang.Long.valueOf(updatedDate!!) * 1000L)
            val sdf = SimpleDateFormat("dd-MMMM-yyyy", Locale.US)
            sdf.timeZone = TimeZone.getTimeZone("GMT-4")
            return sdf.format(date)
        }

        fun convertToDateFormat(updatedDate: Long): String {
            val date = Date((updatedDate))
            val sdf = SimpleDateFormat("dd-MMMM-yyyy", Locale.US)
            sdf.timeZone = TimeZone.getTimeZone("GMT-4")
            return sdf.format(date)
        }

        fun getEncodePath(path: String?): String? {
            try {
                return URLEncoder.encode(path, "UTF-8")
            } catch (e: UnsupportedEncodingException) {
                e.printStackTrace()
            }
            return ""
        }

        fun hideKeyboard(activity: Activity) {
            try {
                if (activity.currentFocus != null) {
                    val imm =
                        activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(activity.currentFocus!!.windowToken, 0)
                }
            } catch (e: Exception) {
                //e.printStackTrace();
            }
        }

        fun dpToPx(context: Context, dp: Int): Int {
            val displayMetrics = context.resources.displayMetrics
            return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT))
        }

        fun createImageFile(): File? {
            // Create an image file name
            val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.ENGLISH).format(Date())
            val imageFileName = "JPEG_$timeStamp"
            val folderPath = Environment.getExternalStorageDirectory().toString() + "/temp/"
            val folder = File(folderPath)
            if (!folder.exists()) {
                folder.mkdir()
            }
            var image: File? = null
            try {
                image = File.createTempFile(imageFileName, ".jpg", folder)
            } catch (e: IOException) {
                e.printStackTrace()
            }
            return image
        }

        fun createImageFile(context: Context): File? {
            // Create an image file name
            val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
            val imageFileName = UUID.randomUUID().toString()
            val folderPath =
                context.getExternalFilesDir(Environment.DIRECTORY_PICTURES).toString() + "/temp/"
            val folder = File(folderPath)
            if (!folder.exists()) {
                folder.mkdir()
            }
            var image: File? = null
            try {
                image = File.createTempFile(imageFileName, ".jpg", folder)
            } catch (e: IOException) {
                e.printStackTrace()
            }
            return image
        }


        private fun isImageValid(uri: Uri): Boolean {
            val options = BitmapFactory.Options()
            options.inJustDecodeBounds = true
            BitmapFactory.decodeFile(File(getFilePath(uri.toString())!!).absolutePath, options)
            val imageHeight = options.outHeight
            val imageWidth = options.outWidth
            return !(imageHeight == -1 && imageWidth == -1)
        }

        private fun isImageValid(context: Context, uri: Uri): Boolean {
            val options = BitmapFactory.Options()
            options.inJustDecodeBounds = true
            BitmapFactory.decodeFile(File(getFilePath(context, uri.toString())!!).path, options)
            val imageHeight = options.outHeight
            val imageWidth = options.outWidth
            return !(imageHeight == -1 && imageWidth == -1)
        }

        private fun getFilePath(imageUri: String): String? {
            return if (imageUri.contains("/temp")) {
                Environment.getExternalStorageDirectory().toString() + imageUri.substring(
                    imageUri.indexOf(
                        "/temp"
                    )
                )
            } else {
                imageUri
            }
        }

        private fun getFilePath(context: Context, imageUri: String): String? {
            return if (imageUri.contains("/temp")) {
                context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                    .toString() + imageUri.substring(
                    imageUri.indexOf(
                        "/temp"
                    )
                )
            } else {
                imageUri
            }
        }



//        fun markImageAsGeoTag(image: File, ttLocation: TTNewLocation?) {
//            val location: Location =
//             Utilities.toLocation(ttLocation)
//            if (location != null) {
//                try {
//                    val exif = ExifInterface(image.absolutePath)
//                    exif.setAttribute(
//                        ExifInterface.TAG_GPS_LATITUDE,
//                        GPS.convert(location.latitude)
//                    )
//                    exif.setAttribute(
//                        ExifInterface.TAG_GPS_LATITUDE_REF,
//                        GPS.latitudeRef(location.latitude)
//                    )
//                    exif.setAttribute(
//                        ExifInterface.TAG_GPS_LONGITUDE,
//                        GPS.convert(location.longitude)
//                    )
//                    exif.setAttribute(
//                        ExifInterface.TAG_GPS_LONGITUDE_REF,
//                        GPS.longitudeRef(location.longitude)
//                    )
//                    exif.saveAttributes()
//                } catch (e: IOException) {
//                    e.printStackTrace()
//                }
//            }
//        }

        fun inputStreamToString(inputStream: InputStream): String? {
            return try {
                val bytes = ByteArray(inputStream.available())
                inputStream.read(bytes, 0, bytes.size)
                String(bytes)
            } catch (e: IOException) {
                null
            }
        }

        fun parseCreateUpdateDate(strDate: String?): Int {
            val answerFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH)
            return try {
                answerFormat.parse(strDate!!)!!.time.toInt()
            } catch (e: ParseException) {
                e.printStackTrace()
                0
            }
        }

        fun getTimeStampFromModelName(timeStr: String): Long {
            return try {
                val start = timeStr.lastIndexOf("-") + 1
                val end = timeStr.lastIndexOf(".")
                timeStr.substring(start, end).toLong()
            } catch (e: java.lang.Exception) {
                0
            }
        }

        fun convertToDateToLong(strDate: String): Date {
            return if (!isStringValid(strDate)) {
                Date()
            } else Date(java.lang.Long.valueOf(strDate!!) * 1000L)
        }

        fun convertToDateToLong(strDate: Long?): Date {
            return if (strDate == null) {
                Date()
            } else {
                Date(strDate)
            }
        }

//        fun stringToGeom(geomData: String?): GeomData? {
//            val gson = Gson()
//            return gson.fromJson(geomData, GeomData::class.java)
//        }

        fun getDate(date: String): String {
            val sdf: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.ENGLISH)
            return try {
                val currentDate = sdf.parse(date)
                val formatter = SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH)
                formatter.format(currentDate!!)
            } catch (e: ParseException) {
                ""
            }
        }

        fun getTime(date: String): String {
            val answerFormat = SimpleDateFormat("hh:mm aa", Locale.ENGLISH)
            val display12Formatter = SimpleDateFormat("hh:mm aa", Locale.getDefault())
            var selectedTime: Date
            return try {
                selectedTime = answerFormat.parse(date)!!
                display12Formatter.format(selectedTime)
            } catch (e: ParseException) {
                selectedTime = Date()
                display12Formatter.format(selectedTime)
            }
        }

        fun getDateAndTime(date: String, time: String): Long {
            val timeFormat = SimpleDateFormat("hh:mm aa", Locale.ENGLISH)
            val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.ENGLISH)
            return try {
                val timeParse = timeFormat.parse(time)
                val dateParse = dateFormat.parse(date)
                val dateArray = dateFormat.format(timeParse!!).split("T").toTypedArray()
                val timeArray = dateFormat.format(dateParse!!).split("T").toTypedArray()
                val neededDate = timeArray[0] + "T" + dateArray[1] + "Z"
                getTimeInMilliSecFromUTC(neededDate)
            } catch (e: ParseException) {
                e.printStackTrace()
                0
            }
        }

        fun isSameDay(currentDate: Date): Boolean {
            val currentDateFormat = SimpleDateFormat("yyyyMMdd", Locale.ENGLISH)
            return currentDateFormat.format(Date(System.currentTimeMillis())) == currentDateFormat.format(
                currentDate
            )
        }

        fun isSameDay(fromDate: Date, toDate: Date): Boolean {
            val fromCalendar = Calendar.getInstance()
            val toCalender = Calendar.getInstance()
            fromCalendar.time = fromDate
            toCalender.time = toDate
            return fromCalendar[Calendar.DAY_OF_YEAR] == toCalender[Calendar.DAY_OF_YEAR]
                    && fromCalendar[Calendar.MONTH] == toCalender[Calendar.MONTH]
                    && fromCalendar[Calendar.YEAR] == toCalender[Calendar.YEAR]
        }

        fun isSameDay(date: String): Boolean {
            val messageTime = Calendar.getInstance()
            messageTime.timeInMillis = getTimeInMilliSecFromUTC(date)
            val now = Calendar.getInstance()
            return (now[Calendar.DATE] == messageTime[Calendar.DATE] && now[Calendar.MONTH] == messageTime[Calendar.MONTH]
                    && now[Calendar.YEAR] == messageTime[Calendar.YEAR])
        }

        fun isDateTodayOrYesterday(date: String): Boolean {
            val messageTime = Calendar.getInstance()
            messageTime.timeInMillis = getTimeInMilliSecFromUTC(date)
            val now = Calendar.getInstance()
            if ((now[Calendar.DATE] == messageTime[Calendar.DATE] && now[Calendar.MONTH] == messageTime[Calendar.MONTH] && now[Calendar.YEAR] == messageTime[Calendar.YEAR])
                || (now[Calendar.DATE] - messageTime[Calendar.DATE] == 1 && now[Calendar.MONTH] == messageTime[Calendar.MONTH] && now[Calendar.YEAR] == messageTime[Calendar.YEAR])
            ) {
                return true
            }
            return false
        }

        private fun getTimeInMilliSecFromUTC(date: String): Long {
            val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.getDefault())
            format.timeZone = TimeZone.getTimeZone("GMT+05:30")
            var formatDate: Date? = null
            try {
                formatDate = format.parse(date)
                return formatDate.time
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            return 0
        }

        fun getLabelDateFormat(): SimpleDateFormat {
            return SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        }

        fun getMimeType(file: File): String {
            return URLConnection.guessContentTypeFromName(file.name)
        }

        fun getTimeFromDate(date: Date): String {
            val answerFormat = SimpleDateFormat("hh:mm aa", Locale.ENGLISH)
            return answerFormat.format(date)
        }

        fun getFileName(uri: Uri): String {
            return uri.lastPathSegment.toString()
        }

        fun changeDrawableColor(context: Context, icon: Int, color: Int): Drawable {
            var drawable: Drawable = ResourcesCompat.getDrawable(context.resources, icon, null)!!
            drawable = DrawableCompat.wrap(drawable)
            DrawableCompat.setTint(
                drawable, ResourcesCompat.getColor(
                    context.resources,
                    color,
                    null
                )
            )
            return drawable
        }

        fun fromHtml(title: String, value: String): Spanned? {
            return when {
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.N -> {
                    Html.fromHtml(
                        "$title<font color=#3DAB35> &#032;$value</font>",
                        Html.FROM_HTML_MODE_LEGACY
                    )
                }
                else -> {
                    Html.fromHtml("$title<font color=#3DAB35> &#032;$value</font>")
                }
            }
        }

        fun whiteSpaceFilter(): InputFilter {
            return InputFilter { source: CharSequence, start: Int, end: Int, dest: Spanned?, dstart: Int, dend: Int ->
                var i = start
                while (i < end) {
                    if (!Character.isLetterOrDigit(source[i])) {
                        return@InputFilter ""
                    }
                    i++
                }
                null
            }
        }

        fun specialCharacterFilter(): InputFilter {
            return InputFilter { source: CharSequence, start: Int, end: Int, dest: Spanned?, dstart: Int, dend: Int ->
                var i = start
                while (i < end) {
                    if (!Character.isLetterOrDigit(source[i])) {
                        return@InputFilter ""
                    }
                    i++
                }
                null
            }
        }
    }
}