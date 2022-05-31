package fakhri.kchaou.maddina.view.profile


import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import fakhri.kchaou.maddina.R
import fakhri.kchaou.maddina.databinding.FragmentProfileBinding
import fakhri.kchaou.maddina.model.entity.Post
import fakhri.kchaou.maddina.view.home.HomeAdapter
import fakhri.kchaou.maddina.view.home.HomeFragment
import fakhri.kchaou.maddina.viewmodel.PostVM
import fakhri.kchaou.maddina.viewmodel.UserVM


class UserProfilFragment : Fragment() {

    private var _binding  : FragmentProfileBinding? = null
    private val binding get() =_binding!!
    var posts : ArrayList<Post> = ArrayList()


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

        val postvm = PostVM()
        val userVM = UserVM()

        /********** read the id of user from Sharedpreference to get data from firebase *****************/

        val sharedPreferences: SharedPreferences = requireContext().getSharedPreferences("user", Context.MODE_PRIVATE)
        val id = sharedPreferences.getString("userId", "")!!

        /********** get info of user from firebase *****************/


            userVM.getUserById(id).observe(viewLifecycleOwner, Observer {
                Log.println(Log.ASSERT,"firebase", "Got value ${it}")
                Log.println(Log.ASSERT,"firebase", id)
            })





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






    fun returnToHome(){
        val profileFragment = HomeFragment()
        val fragmentManager: FragmentManager? = fragmentManager
        val fragmentTransaction = fragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.home_fragment, profileFragment)
        fragmentTransaction?.commit()


    }
}