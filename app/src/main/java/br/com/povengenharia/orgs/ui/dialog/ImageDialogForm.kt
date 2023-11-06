package br.com.povengenharia.orgs.ui.dialog

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import br.com.povengenharia.orgs.databinding.FormImageBinding
import br.com.povengenharia.orgs.extensions.TryLoadImage

class ImageDialogForm(private val context: Context) {

    fun show(
        urlDefault: String? = null,
        whenImageLoaded: (image: String) -> Unit
    ) {
        val binding = FormImageBinding.inflate(LayoutInflater.from(context))

        urlDefault?.let {
            binding.ivFromImage.TryLoadImage(it)
            binding.etFormImageUrl.setText(it)
        }


        binding.btnFormImageLoad.setOnClickListener {
            val url = binding.etFormImageUrl.text.toString()
            binding.ivFromImage.TryLoadImage(url)
        }


        AlertDialog.Builder(context)
            .setPositiveButton("Confirmar") { _, _ ->
                val url = binding.etFormImageUrl.text.toString()
                whenImageLoaded(url)


            }
            .setNegativeButton("Cancelar") { _, _ -> }
            .setView(binding.root)
            .show()
    }
}


