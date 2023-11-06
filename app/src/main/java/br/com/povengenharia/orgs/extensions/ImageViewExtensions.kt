package br.com.povengenharia.orgs.extensions

import android.content.Context
import android.widget.ImageView
import br.com.povengenharia.orgs.R
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import android.os.Build.VERSION.SDK_INT
import coil.load


fun ImageView.TryLoadImage(url: String? = null) {
    val imageLoader = provideImageLoader(this.context)
    load(url, imageLoader) {
        fallback(R.drawable.errorimage)
        error(R.drawable.errorimage)
        placeholder(R.drawable.placeholder)
    }
}

private fun provideImageLoader(contex: Context): ImageLoader {
    return ImageLoader.Builder(contex)
        .components {
            if (SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()
}

