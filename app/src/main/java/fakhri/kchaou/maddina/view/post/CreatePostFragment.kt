package fakhri.kchaou.maddina.view.post


import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import fakhri.kchaou.maddina.R
import fakhri.kchaou.maddina.databinding.FragmentCreatePostBinding
import fakhri.kchaou.maddina.view.home.HomeFragment
import fakhri.kchaou.maddina.viewmodel.PostVM


class CreatePostFragment : Fragment() {

    private var _binding  : FragmentCreatePostBinding? = null
    private val binding get() =_binding!!
    lateinit var alertDialog : AlertDialog
    private  var uriImage : Uri? = null


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

            if (postText.length >0 || uriImage != null){
                var dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.loading_alert, null)
                val builder    = AlertDialog.Builder(requireContext())
                    .setView(dialogView)
                    .setTitle("")
                    .setCancelable(false)

                alertDialog = builder.show()

                var result = postVM.createPost(userId, postText, uriImage)
                result.observe(viewLifecycleOwner, Observer {
                    if (it == true){
                        alertDialog.dismiss()
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
        binding.addImageViedo.setOnClickListener {
            val intent = Intent()
            intent.setAction(Intent.ACTION_GET_CONTENT)
            intent.setType("image/*")
            startActivityForResult(intent,2)

        }


        val  callbacks = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
               returnToHome()
            }

        }
        requireActivity().onBackPressedDispatcher.addCallback(callbacks)


        return binding.root
    }

    fun returnToHome(){
        val profileFragment = HomeFragment()
        val fragmentManager: FragmentManager? = fragmentManager
        val fragmentTransaction = fragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.home_fragment, profileFragment)
        fragmentTransaction?.commit()


    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 2 && resultCode == RESULT_OK && data != null){
            uriImage = data.data

        }
    }



}