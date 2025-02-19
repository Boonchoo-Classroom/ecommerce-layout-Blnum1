package scisrc.mobiledev.ecommercelayout.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import scisrc.mobiledev.ecommercelayout.databinding.FragmentProductListBinding

class ProductListFragment : Fragment() {
    private var _binding: FragmentProductListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductListBinding.inflate(inflater, container, false)

        // Mock Data
        val productList = listOf(
            "ปากกา",
            "สมุดโน้ต",
            "กระเป๋านักเรียน",
            "เมาส์ไร้สาย",
            "คีย์บอร์ด"
        )

        // Setup RecyclerView
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = ProductAdapter(productList)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
