package fakhri.kchaou.maddina.view.post


import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import fakhri.kchaou.maddina.R
import fakhri.kchaou.maddina.databinding.FragmentCreatePostBinding
import fakhri.kchaou.maddina.view.HomeActivity
import fakhri.kchaou.maddina.view.profile.ProfileFragment
import fakhri.kchaou.maddina.viewmodel.PostVM


class CreatePostFragment : Fragment() {

    private var _binding  : FragmentCreatePostBinding? = null
    private val binding get() =_binding!!
    lateinit var alertDialog : AlertDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreatePostBinding.inflate(inflater, container, false)
        val postVM  = ViewModelProvider(this).get(PostVM::class.java)

        binding.btnBack.setOnClickListener   {

            returnToHome()
        }
        binding.cancel.setOnClickListener    {
            binding.postText.setText("")
            binding.postTextLabel.helperText = ""

        }
        binding.btnCreate.setOnClickListener {
            val sharedPreferences: SharedPreferences? = this.activity?.getSharedPreferences("user", Context.MODE_PRIVATE)
            val userId :String = sharedPreferences?.getString("userId", "").toString()

            var postText = binding.postText.text.toString()

            if (postText.length >0){
                var dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.loading_alert, null)
                val builder    = AlertDialog.Builder(requireContext())
                    .setView(dialogView)
                    .setTitle("")
                    .setCancelable(false)

                alertDialog = builder.show()

                var result = postVM.createPost(userId, postText)
                result.observe(viewLifecycleOwner, Observer {
                    if (it == true){
                        Toast.makeText(this.context,"تم إضافة قصتك ", Toast.LENGTH_LONG).show();
                        returnToHome()

                    }
                    else{
                        alertDialog.dismiss()
                        Toast.makeText(this.context,"حدث خطأ ما, حاول لاحقا", Toast.LENGTH_LONG).show();
                    }
           });

            }else{
                binding.postTextLabel.helperText = "لا يمكن إضافة قصة فارغة"
            }
        }




        return binding.root
    }

    fun returnToHome(){
        alertDialog.dismiss()
        val profileFragment = ProfileFragment()
        val fragmentManager: FragmentManager? = fragmentManager
        val fragmentTransaction = fragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.home_fragment, profileFragment)
        fragmentTransaction?.commit()


    }




}