package fakhri.kchaou.maddina.utils

import android.os.Build
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun convert (date : String) : String{

    var timeTxt = "ggggg"

    val current = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        LocalDateTime.now()
    } else {
        TODO("VERSION.SDK_INT < O")
    }
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
    val formatted = current.format(formatter).toString()

    if(date.substring(0,10).equals(formatted.substring(0,10)))
    {
        if (date.substring(11,13).equals(formatted.substring(11,13)))
        {
            val time = formatted.substring(14,16).toInt() - date.substring(14,16).toInt()
            when(time) {
                0 -> timeTxt = "الأن "

                1 -> timeTxt = "منذ دقيقة"
                2 -> timeTxt = "منذ دقيقتان"

                3 -> timeTxt = "منذ ثلاث دقائق"

                4 -> timeTxt = "منذ أربع دقائق"

                5 -> timeTxt = "منذ خمس دقائق"

                10 -> timeTxt = "منذ عشرة دقائق"
                else-> timeTxt = " منذ $time دقيقة "


            }



        }
        else{
            val time =formatted.substring(11,13).toInt() - date.substring(11,13).toInt()
            when(time){

                1-> timeTxt ="منذ ساعة"
                2-> timeTxt ="منذ سعتان"
                3-> timeTxt ="منذ ثلاث ساعة"
                4-> timeTxt ="منذ أربع ساعات"
                5-> timeTxt ="منذ خمس ساعات"
                6-> timeTxt ="منذ ست ساعات"
                7-> timeTxt ="منذ سبع ساعات"
                8-> timeTxt ="منذ ثماني ساعات"
                9-> timeTxt =" منذ تسع ساعات"
                10-> timeTxt ="منذ عشر ساعات"
                11-> timeTxt =" $time منذ ساعات "


            }
        }

    }
    else{
        if (date.substring(0,4).equals(formatted.substring(0,4))){
            if (date.substring(5,7).equals(formatted.substring(5,7))){

                if (date.substring(8,10).equals(formatted.substring(8,10))){
                    timeTxt = " منذ يوم "
                }
                else{
                    val time = formatted.substring(8,10).toInt() - date.substring(8,10).toInt()
                    timeTxt = " منذ $time يوم "
                }
            }
            else{
                val time = formatted.substring(5,7).toInt() - date.substring(5,7).toInt()
                timeTxt = " منذ $time شهر "
            }
        }
        else{
            val time = formatted.substring(0,4).toInt() - date.substring(0,4).toInt()
            when(time){
                1-> timeTxt =  "منذ سنة"
                2-> timeTxt = "منذ سنتين"
                3-> timeTxt = "منذ ثلاث سنوات"
                4-> timeTxt = "منذ أربع سنوات"
                5-> timeTxt = "منذ خمس سنوات"
                6-> timeTxt = "منذ ست سنوات"
                7-> timeTxt = "منذ سبع سنوات"
                8-> timeTxt = "منذ ثماني سنوات"
                9-> timeTxt = "منذ تسع سنوات"
                10-> timeTxt ="منذ عشرة سنوات"
                11-> timeTxt = " منذ $time سنوات "


            }
        }
    }


    return timeTxt
}
