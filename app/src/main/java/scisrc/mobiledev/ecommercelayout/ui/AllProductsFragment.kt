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
import scisrc.mobiledev.ecommercelayout.databinding.FragmentAllProductsBinding
import scisrc.mobiledev.ecommercelayout.model.Product

class AllProductsFragment : Fragment() {
    private var _binding: FragmentAllProductsBinding? = null
    private val binding get() = _binding!!

    private val allProducts = listOf(
        Product("ปากกาสีน้ำเงิน", 10.0, "ปากกาสีน้ำเงิน", R.drawable.pen_1, "Pen"),
        Product("ดินสอ", 5.0, "ดินสอ 2B", R.drawable.pencil_1, "Pencil"),
        Product("สมุดปกแข็ง", 25.0, "สมุดปกแข็ง", R.drawable.book_2, "Book"),
        Product("ปากกาสีแดง", 10.0, "ปากกาสีแดง", R.drawable.pen_2, "Pen"),
        Product("ปากกาสีดำ", 10.0, "ปากกาสีดำ", R.drawable.pen3, "Pen"),
        Product("ดินสอกด", 20.0, "ดินสอกด", R.drawable.pencil_2, "Pencil"),
        Product("สมุดปกอ่อน", 20.0, "สมุดปกอ่อน", R.drawable.book_1, "Book")
    )

    private var filteredProducts = allProducts.toMutableList()
    private lateinit var productAdapter: ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAllProductsBinding.inflate(inflater, container, false)


        productAdapter = ProductAdapter(requireContext(), filteredProducts)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = productAdapter


        val categories = listOf("All") + allProducts.map { it.category }.distinct()
        val categoryAdapter = CategoryAdapter(categories) { selectedCategory ->
            filterProductsByCategory(selectedCategory)
        }
        binding.categoryRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.categoryRecyclerView.adapter = categoryAdapter

        return binding.root
    }


    private fun filterProductsByCategory(category: String) {
        filteredProducts = if (category == "All") {
            allProducts.toMutableList()
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
