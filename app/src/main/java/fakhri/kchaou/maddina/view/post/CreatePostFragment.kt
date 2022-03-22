package fakhri.kchaou.maddina.view.post


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import fakhri.kchaou.maddina.R
import fakhri.kchaou.maddina.databinding.FragmentCreatePostBinding
import fakhri.kchaou.maddina.view.profile.ProfileFragment
import fakhri.kchaou.maddina.viewmodel.PostVM


class CreatePostFragment : Fragment() {

    private var _binding  : FragmentCreatePostBinding? = null
    private val binding get() =_binding!!
    private val postVM = PostVM()


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



        binding.btnBack.setOnClickListener {
            val profileFragment = ProfileFragment()
            val fragmentManager: FragmentManager? = fragmentManager
            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.home_fragment, profileFragment)
            fragmentTransaction?.commit()
              //Log.println(Log.ASSERT, "------", "<<<<<<<<<<<<<<<<<<<<<<<")
        }

        binding.btnCreate.setOnClickListener {
            val sharedPreferences: SharedPreferences? = this.activity?.getSharedPreferences("user", Context.MODE_PRIVATE)
            val userId :String = sharedPreferences?.getString("userId", "").toString()

            var postText = binding.postText.text.toString()
            postVM.createPost(userId, postText)
        }




        return binding.root
    }


}