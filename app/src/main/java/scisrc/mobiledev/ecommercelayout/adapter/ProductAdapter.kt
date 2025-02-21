package scisrc.mobiledev.ecommercelayout.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import scisrc.mobiledev.ecommercelayout.databinding.ItemProductBinding
import scisrc.mobiledev.ecommercelayout.model.Product
import scisrc.mobiledev.ecommercelayout.ui.ProductDetailActivity

class ProductAdapter(private var productList: List<Product>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.binding.productName.text = product.name
        holder.binding.productPrice.text = "${product.price} บาท"
        holder.binding.productImage.setImageResource(product.image)

        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, ProductDetailActivity::class.java)
            intent.putExtra("product", product)
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount() = productList.size

    fun updateList(newList: List<Product>) {
        productList = newList
        notifyDataSetChanged()
    }


}
