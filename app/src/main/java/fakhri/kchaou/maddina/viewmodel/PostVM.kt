package fakhri.kchaou.maddina.viewmodel

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fakhri.kchaou.maddina.model.repository.PostRepository
import java.util.*

class PostVM : ViewModel() {

    val postRepository = PostRepository()

    fun createPost( id: String, text: String) : LiveData<Boolean>{

        return postRepository.createPost( id, text)
    }

    private lateinit var timer: CountDownTimer
    private var _seconds = MutableLiveData<Int>()

    fun startTime() {
        timer = object :CountDownTimer(10000,1000){
            override fun onTick(p0: Long) {
                val timeLeft =p0/1000
                _seconds.value = timeLeft.toInt()
            }

            override fun onFinish() {

            }

        }.start()
            }

    fun stopTimer(){
        timer.cancel()
    }

    fun getData() : LiveData<Int>{
        return _seconds
    }
}