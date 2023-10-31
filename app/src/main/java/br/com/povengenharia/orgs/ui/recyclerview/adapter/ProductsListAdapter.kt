package br.com.povengenharia.orgs.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.povengenharia.orgs.R
import br.com.povengenharia.orgs.databinding.ProductItemBinding
import br.com.povengenharia.orgs.model.Product

class ProductsListAdapter(
    private val context: Context,
    products: List<Product>
) : RecyclerView.Adapter<ProductsListAdapter.ViewHolder>() {

    private val products = products.toMutableList()

    class ViewHolder(private val binding: ProductItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            binding.tvProductItemName.text = product.name
            binding.tvProductItemDescription.text = product.description
            binding.tvProductItemPrice.text = product.price.toPlainString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ProductItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        holder.bind(product)
    }

    fun update(product: List<Product>) {
        this.products.clear()
        this.products.addAll(product)
        notifyDataSetChanged()
    }

}
