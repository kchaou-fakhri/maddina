package fakhri.kchaou.maddina.view.profile


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import fakhri.kchaou.maddina.R
import fakhri.kchaou.maddina.databinding.FragmentProfileBinding
import fakhri.kchaou.maddina.view.home.HomeFragment
import fakhri.kchaou.maddina.viewmodel.PostVM


class UserFragment : Fragment() {

    private var _binding  : FragmentProfileBinding? = null
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
        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        binding.btnBack.setOnClickListener   {
            returnToHome()
        }
        binding.username.setOnClickListener    {

            Toast.makeText(this.context, "test", Toast.LENGTH_SHORT).show()
            binding.username.setText("Manef Jedidi")
        }
/*        binding.profile_image.setOnClickListener    {

            Toast.makeText(this.context, "test", Toast.LENGTH_SHORT).show()

        }*/

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