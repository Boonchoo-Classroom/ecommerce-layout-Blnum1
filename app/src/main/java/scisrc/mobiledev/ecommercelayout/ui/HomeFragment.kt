package scisrc.mobiledev.ecommercelayout.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import scisrc.mobiledev.ecommercelayout.R
import scisrc.mobiledev.ecommercelayout.adapter.ProductAdapter
import scisrc.mobiledev.ecommercelayout.adapter.CategoryAdapter
import scisrc.mobiledev.ecommercelayout.databinding.FragmentHomeBinding
import scisrc.mobiledev.ecommercelayout.model.Product

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val allProducts = listOf(
        Product("ปากกา", 10.0, "ปากกาสีน้ำเงิน", R.drawable.banner1, "Pen"),
        Product("ดินสอ", 5.0, "ดินสอ HB", R.drawable.banner1, "Pencil"),
        Product("สมุดโน้ต", 50.0, "สมุดปกแข็ง", R.drawable.banner1, "Book")
    )

    private var filteredProducts = allProducts.toMutableList()
    private lateinit var productAdapter: ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        // ตั้งค่า Adapter สำหรับรายการสินค้า
        productAdapter = ProductAdapter(filteredProducts)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = productAdapter

        // **เพิ่มปุ่ม "All" เพื่อแสดงสินค้าทั้งหมด**
        val categories = listOf("All") + allProducts.map { it.category }.distinct()

        val categoryAdapter = CategoryAdapter(categories) { selectedCategory ->
            filterProductsByCategory(selectedCategory)
        }

        // ตั้งค่า Adapter สำหรับหมวดหมู่สินค้า
        binding.categoryRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.categoryRecyclerView.adapter = categoryAdapter

        return binding.root
    }

    // ฟังก์ชันกรองสินค้าเมื่อเลือกหมวดหมู่
    private fun filterProductsByCategory(category: String) {
        filteredProducts = if (category == "All") {
            allProducts.toMutableList()  // แสดงสินค้าทั้งหมด
        } else {
            allProducts.filter { it.category == category }.toMutableList()
        }
        productAdapter.updateList(filteredProducts)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}