package fakhri.kchaou.maddina.view.profile


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import fakhri.kchaou.maddina.R
import fakhri.kchaou.maddina.databinding.FragmentProfileBinding
import fakhri.kchaou.maddina.model.entity.Post
import fakhri.kchaou.maddina.view.home.HomeAdapter
import fakhri.kchaou.maddina.view.home.HomeFragment
import fakhri.kchaou.maddina.viewmodel.PostVM


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


        postvm.getPost().observe(viewLifecycleOwner, Observer {
            posts.addAll(it)
//            Log.println(Log.ASSERT, "-----", it.size.toString())
            val adapter = HomeAdapter(requireContext(),posts )
            binding.rcPosts.layoutManager = LinearLayoutManager(requireContext())
            binding.rcPosts.adapter = adapter

        })

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