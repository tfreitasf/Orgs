package br.com.povengenharia.orgs.ui.activity


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import br.com.povengenharia.orgs.R
import br.com.povengenharia.orgs.dao.ProductsDao
import br.com.povengenharia.orgs.ui.recyclerview.adapter.ProductsListAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ProductsListActivity : AppCompatActivity(R.layout.activity_products_list) {

    val dao = ProductsDao()
    private val adapter = ProductsListAdapter(context = this, products = dao.getAllProducts())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configuraRecyclerView()
        configuraFab()


    }

    override fun onResume() {
        super.onResume()
        adapter.update(dao.getAllProducts())
    }

    private fun configuraFab() {
        val fab = findViewById<FloatingActionButton>(R.id.fab_add)
        fab.setOnClickListener {
            vaiParaFormularioProduto()
        }
    }

    private fun vaiParaFormularioProduto() {
        val intent = Intent(this, ProductFormActivity::class.java)
        startActivity(intent)
    }

    private fun configuraRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.rv_item)

        recyclerView.adapter = adapter

    }
}
