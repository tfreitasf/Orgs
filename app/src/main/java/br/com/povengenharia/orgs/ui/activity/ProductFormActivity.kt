package br.com.povengenharia.orgs.ui.activity

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import br.com.povengenharia.orgs.R
import br.com.povengenharia.orgs.dao.ProductsDao
import br.com.povengenharia.orgs.model.Product
import java.math.BigDecimal

class ProductFormActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_form)


        val botaoSalvar = findViewById<Button>(R.id.btn_salvar)
        botaoSalvar.setOnClickListener {
            val campoNome = findViewById<EditText>(R.id.et_nome)
            val campoDescricao = findViewById<EditText>(R.id.et_description)
            val campoPreco = findViewById<EditText>(R.id.et_price)

            val nome = campoNome.text.toString()
            val descricao = campoDescricao.text.toString()
            val precoEmTexto = campoPreco.text.toString()
            val preco = if (precoEmTexto.isBlank()){
                BigDecimal.ZERO
            } else{
                BigDecimal(precoEmTexto)
            }

            val produtoNovo = Product(
                name = nome,
                description = descricao,
                price = preco
            )


            Log.i("FormularioProdutoActivity", "${produtoNovo.name} ${produtoNovo.description} ${produtoNovo.price}")
            val dao = ProductsDao()
            dao.add(produtoNovo)
            Log.i("FormularioProdutoActivity", "on Create ${dao.getAllProducts()}")
            finish()
        }
    }
}