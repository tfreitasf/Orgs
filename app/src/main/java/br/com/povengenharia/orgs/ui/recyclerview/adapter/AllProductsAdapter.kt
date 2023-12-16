package br.com.povengenharia.orgs.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.povengenharia.orgs.R
import br.com.povengenharia.orgs.databinding.ProductItemBinding
import br.com.povengenharia.orgs.extensions.TryLoadImage
import br.com.povengenharia.orgs.model.AllProductsItem
import br.com.povengenharia.orgs.extensions.formatValueAsBrazilianCurrency

class AllProductsAdapter(
    private val context: Context
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<AllProductsItem> = emptyList()

    companion object {
        private const val TYPE_USER_TITLE = 0
        private const val TYPE_PRODUCT = 1
    }

    fun updateItems(newItems: List<AllProductsItem>) {
        items = newItems
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is AllProductsItem.UserTitle -> TYPE_USER_TITLE
            is AllProductsItem.ProductItem -> TYPE_PRODUCT
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_USER_TITLE -> UserTitleViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_all_products, parent, false)
            )
            TYPE_PRODUCT -> ProductViewHolder(
                LayoutInflater.from(context).inflate(R.layout.product_item, parent, false)
            )
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = items[position]) {
            is AllProductsItem.UserTitle -> (holder as UserTitleViewHolder).bind(item)
            is AllProductsItem.ProductItem -> (holder as ProductViewHolder).bind(item)
        }
    }

    override fun getItemCount() = items.size

    class UserTitleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val title: TextView = view.findViewById(R.id.tv_item_all_products_username)

        fun bind(userTitle: AllProductsItem.UserTitle) {
            title.text = userTitle.userName
        }
    }

    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ProductItemBinding.bind(view)

        fun bind(productItem: AllProductsItem.ProductItem) {
            with(binding) {
                tvProductItemName.text = productItem.product.name
                tvProductItemDescription.text = productItem.product.description
                tvProductItemPrice.text = formatValueAsBrazilianCurrency(productItem.product.price)
                ivProductItemPicture.TryLoadImage(productItem.product.image)
            }
        }
    }
}
