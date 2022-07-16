package fakhri.kchaou.maddina.view.profile


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import fakhri.kchaou.maddina.R
import fakhri.kchaou.maddina.databinding.FragmentProfileBinding
import fakhri.kchaou.maddina.model.entity.Post
import fakhri.kchaou.maddina.model.entity.User
import fakhri.kchaou.maddina.view.home.HomeFragment
import fakhri.kchaou.maddina.view.profile.edit.EditProfilActivity
import fakhri.kchaou.maddina.viewmodel.UserVM



class UserProfilFragment : Fragment() {

    private var _binding  : FragmentProfileBinding? = null
    private val binding get() =_binding!!
    var posts : ArrayList<Post> = ArrayList()
    private var user = User()
    private var imageURI : Uri? = null
    lateinit var userVM : UserVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)


       userVM= UserVM()

        /********** read the id of user from Sharedpreference to get data from firebase *****************/

        val sharedPreferences: SharedPreferences = requireContext().getSharedPreferences("user", Context.MODE_PRIVATE)
        val id = sharedPreferences.getString("userId", "")!!

        /********** get info of user from firebase *****************/


            userVM.getUserById(id).observe(viewLifecycleOwner, Observer {
                if (!it.userImage.isNullOrEmpty()){
                    Glide.with(requireContext() /* context */)
                        .load(it.userImage)
                        .into(binding.profileImage)
                }else{
                    Glide.with(requireContext() /* context */)
                        .load(R.drawable.img_if_no_user_image)
                        .into(binding.profileImage)
                }
              binding.username.text = it.name
                if (it.bio != ""){
                    binding.bio.text = "( ${it.bio} )"
                    val param = binding.bio.layoutParams as ViewGroup.MarginLayoutParams
                    param.setMargins(0,0,0,8)
                    binding.bio.layoutParams = param

                }
                else{
                    binding.bio.text=""


                }
                if(it.job != ""){
                    binding.jobTitle.setText(it.job)
                }
                else{
                    binding.jobTitle.setText("لم يحدد بعد")
                }
                if (it.adress != ""){
                    binding.adress.setText(it.adress)
                }
                else{
                    binding.adress.setText("لم يحدد بعد")
                }


            })

        /********** edit users info *****************/
        binding.editProfil.setOnClickListener {
            activity?.let{
                val intent = Intent (it, EditProfilActivity::class.java)
                it.startActivity(intent)
            }
        }

        /********** edit user image *****************/
        binding.editUserImage.setOnClickListener {
            user.id = id
            val intent = Intent()
            intent.action = Intent.ACTION_GET_CONTENT
            intent.type = "image/*"
            this.startActivityForResult(intent,2)

        }

        val  callbacks = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {

                requireActivity().finish()
            }

        }
        requireActivity().onBackPressedDispatcher.addCallback(callbacks)




//        postvm.getPost().observe(viewLifecycleOwner, Observer {
//            posts.addAll(it)
////            Log.println(Log.ASSERT, "-----", it.size.toString())
//            val adapter = HomeAdapter(requireContext(),posts )
//            binding.rcPosts.layoutManager = LinearLayoutManager(requireContext())
//            binding.rcPosts.adapter = adapter
//
//        })

        return binding.root
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 2 && resultCode == AppCompatActivity.RESULT_OK && data != null){
            imageURI = data.data
            userVM.updateUserImage(user.id!!, imageURI).observe(requireActivity(), Observer {
                Glide.with(requireContext() /* context */)
                    .load(it.message)
                    .into(binding.profileImage)

            })

        }
    }


    fun returnToHome(){
        val profileFragment = HomeFragment()
        val fragmentManager: FragmentManager? = fragmentManager
        val fragmentTransaction = fragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.home_fragment, profileFragment)
        fragmentTransaction?.commit()


    }
}