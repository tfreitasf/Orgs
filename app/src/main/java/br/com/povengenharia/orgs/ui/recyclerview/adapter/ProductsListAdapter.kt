package br.com.povengenharia.orgs.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import br.com.povengenharia.orgs.R
import br.com.povengenharia.orgs.databinding.ProductItemBinding
import br.com.povengenharia.orgs.extensions.TryLoadImage
import br.com.povengenharia.orgs.extensions.formatValueAsBrazilianCurrency
import br.com.povengenharia.orgs.model.Product
import kotlinx.coroutines.CoroutineScope

class ProductsListAdapter(
    private val context: Context,
    private val scope: CoroutineScope,
    products: List<Product> = emptyList(),
    var whenClickDelete: (product: Product) -> Unit = {},
    var whenClickEdit: (product: Product) -> Unit = {},
    var whenClickOnItem: (product: Product) -> Unit = {}
) : RecyclerView.Adapter<ProductsListAdapter.ViewHolder>() {

    val products = products.toMutableList()

    inner class ViewHolder(private val binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var product: Product


        init {
            itemView.setOnClickListener {
                if (::product.isInitialized) {
                    whenClickOnItem(product)

                }
            }
            itemView.setOnLongClickListener {
                if (::product.isInitialized) {
                    showPopupMenu(product, it)
                    true
                } else {
                    false
                }
            }
        }

        private fun showPopupMenu(product: Product, itemView: View) {
            val popup = PopupMenu(context, itemView)
            popup.menuInflater.inflate(R.menu.menu_product_details, popup.menu)
            popup.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.menu_product_details_delete -> {
                        whenClickDelete(product)
                        true
                    }

                    R.id.menu_product_details_edit -> {
                        whenClickEdit(product)
                        true
                    }

                    else -> false
                }
            }
            popup.show()
        }


        fun bind(product: Product) {
            this.product = product
            with(binding) {
                tvProductItemName.text = product.name
                tvProductItemDescription.text = product.description
                val priceFormated = formatValueAsBrazilianCurrency(product.price)
                tvProductItemPrice.text = priceFormated
                ivProductItemPicture.TryLoadImage(url = product.image)

            }
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

    fun removeProduct(product: Product) {
        val index = products.indexOfFirst { it.id == product.id }
        if (index >= 0) {
            products.removeAt(index)
            notifyItemRemoved(index)
        }
    }


}
