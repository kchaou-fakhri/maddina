package fakhri.kchaou.maddina


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import fakhri.kchaou.maddina.databinding.FragmentCreatePostBinding


class CreatePostFragment : Fragment() {

    private var _binding  : FragmentCreatePostBinding? = null
    private val binding get() =_binding!!

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

        //      Log.println(Log.ASSERT, "------", "<<<<<<<<<<<<<<<<<<<<<<<")

        }




        return binding.root
    }


}