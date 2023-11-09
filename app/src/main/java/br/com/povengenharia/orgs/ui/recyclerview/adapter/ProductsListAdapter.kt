package br.com.povengenharia.orgs.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.povengenharia.orgs.databinding.ProductItemBinding
import br.com.povengenharia.orgs.extensions.TryLoadImage
import br.com.povengenharia.orgs.extensions.formatValueAsBrazilianCurrency
import br.com.povengenharia.orgs.model.Product

class ProductsListAdapter(
    private val context: Context,
    products: List<Product>,
    var whenClickOnItem: (product: Product) -> Unit = {}
) : RecyclerView.Adapter<ProductsListAdapter.ViewHolder>() {

    private val products = products.toMutableList()

    inner class ViewHolder(private val binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var product: Product

        init {
            itemView.setOnClickListener {
                if (::product.isInitialized) {
                    whenClickOnItem(product)
                }
            }
        }


        fun bind(product: Product) {
            this.product = product
            binding.tvProductItemName.text = product.name
            binding.tvProductItemDescription.text = product.description
            val priceFormated = formatValueAsBrazilianCurrency(product.price)
            binding.tvProductItemPrice.text = priceFormated
            binding.ivProductItemPicture.TryLoadImage(url = product.image)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
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
