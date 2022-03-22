package fakhri.kchaou.maddina.view.auth

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fakhri.kchaou.maddina.R
import fakhri.kchaou.maddina.databinding.FragmentLoginBinding
import fakhri.kchaou.maddina.view.HomeActivity
import fakhri.kchaou.maddina.viewmodel.UserVM


class LoginFragment : Fragment() {

    private var _binding : FragmentLoginBinding? =null
    private val binding get() = _binding!!

     lateinit var alertDialog : AlertDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

            val userVM = UserVM(this)
        _binding = FragmentLoginBinding.inflate(inflater, container, false)




        binding.btnLogin.setOnClickListener {



            var email = binding.email.text.toString()
            var password = binding.password.text.toString()


            if(validEmail(email) == null && validPassword(password) == null){
                var dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.loading_alert, null)
                val builder    = AlertDialog.Builder(requireContext())
                    .setView(dialogView)
                    .setTitle("")
                    .setCancelable(false)

                alertDialog = builder.show()

                userVM.login(email, password)
            }
            else{

                binding.labelEmail.helperText = validEmail(email)
                binding.labelPassword.helperText = validPassword(password)
            }
        }



        return binding.root
    }

    private fun validEmail(email: String): String?
    {
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            return "البريد الالكتروني ضروري"
        }
        return null
    }

    private fun validPassword(passwordText :String): String?
    {

        if(passwordText.length < 8)
        {
            return "يجب أن يتكون الرمز من 8 رموز على الأقل"
        }
        else return null


    }


    fun updateUI(condition : Boolean){

        if(condition){
            val intent = Intent (getActivity(), HomeActivity::class.java)
            getActivity()?.startActivity(intent)
//            Thread.sleep(2_000)
//            alertDialog.dismiss()
        }

    }

}