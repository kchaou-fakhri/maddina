package fakhri.kchaou.maddina.view.auth



import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import fakhri.kchaou.maddina.R
import fakhri.kchaou.maddina.databinding.FragmentSignBinding


import fakhri.kchaou.maddina.viewmodel.UserVM

class SignFragment : Fragment() {

    private var _binding  : FragmentSignBinding? = null
    private val binding get() =_binding!!



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentSignBinding.inflate(inflater, container, false)

        val sex = resources.getStringArray(R.array.sex)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.list_item, sex)

        binding.btnSign.setOnClickListener {
            val userVM = UserVM(this)

            /********* validet the text filde format **********/
            var sex = binding.menu.text.toString()
            var email = binding.email.text.toString()
            var password = binding.password.text.toString()
            var username = binding.username.text.toString()
            if (validEmail(email) != null || validSexUser(sex) != null
                        || validPassword(password) != null || validUserName(username) != null){

             binding.emailLabel.helperText = validEmail(email)
             binding.passwordLabel.helperText = validPassword(password)
             binding.userameLabel.helperText = validUserName(username)
             binding.labelMenu.helperText = validSexUser(sex)

            }
            else{

                when(sex){
                    "رجل"-> sex = "men"
                    else -> sex = "women"
                }
                 userVM.addUser(username, email, password, sex)

            }
        }
        binding.menu.setAdapter(arrayAdapter)

        return binding.root
    }


    /*********** validate email and password *********/

    private fun validEmail(email: String): String?
    {
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            return "عنوان البريد غير صحيح"
        }
        return null
    }

    private fun validPassword(passwordText :String): String?
    {

        if(passwordText.length < 8)
        {
            return "يجب أن يتكون الرمز من 8 رموز على الأقل"
        }


        if(!passwordText.matches(".*[@#\$%^&+=].*".toRegex()))
        {
            return "يجب أن يحتوي الرمز على علامة خاصة مثل *%^&+=@#$ "
        }

        return null
    }

    private fun validUserName(username : String): String?{
        if (username ==""){
           return "اسم المستخدم ضروري"
        }
        else{
            return null
        }
    }

    private fun validSexUser(sex : String): String?{
        if (sex ==""){
            return "جنس المستخدم ضروري"
        }
        else{
            return null
        }
    }

    fun updateUI(condition : Boolean){
        if(condition){
            val intent = Intent (getActivity(), LoginActivity::class.java)
            getActivity()?.startActivity(intent)
        }

    }
}