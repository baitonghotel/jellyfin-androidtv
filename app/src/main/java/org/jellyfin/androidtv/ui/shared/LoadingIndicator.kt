package org.jellyfin.androidtv.ui.shared

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import org.jellyfin.androidtv.ui.base.CircularProgressIndicator
import org.jellyfin.androidtv.ui.base.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.fillMaxWidth
import org.jellyfin.androidtv.ui.base.JellyfinTheme
import org.jellyfin.sdk.model.api.BaseItemDto
import org.jellyfin.sdk.model.api.BaseItemKind
import org.jellyfin.sdk.model.api.ImageType
import org.jellyfin.androidtv.util.ImageHelper
import org.jellyfin.androidtv.util.apiclient.itemImages
import org.jellyfin.androidtv.util.apiclient.parentImages
import org.jellyfin.androidtv.ui.composable.AsyncImage
import org.koin.compose.koinInject

import androidx.compose.ui.draw.alpha
import android.widget.ImageView

@Composable
fun LoadingIndicator(
    modifier: Modifier = Modifier,
    text: String = "baitong TV",
    item: BaseItemDto? = null
) {
    Box(modifier = modifier.fillMaxSize()) {
        if (item?.type == BaseItemKind.LIVE_TV_CHANNEL || item?.type == BaseItemKind.TV_CHANNEL || item?.type == BaseItemKind.PROGRAM) {
            val imageHelper: ImageHelper = koinInject()
            // Try to get logo first for Live TV/Programs, fallback to primary
            val image = item.itemImages[ImageType.LOGO] 
                ?: item.parentImages[ImageType.LOGO] 
                ?: item.itemImages[ImageType.PRIMARY]
            
            val imageUrl = image?.let {
                imageHelper.getImageUrl(it, 1920, 1080)
            }
            if (imageUrl != null) {
                AsyncImage(
                    url = imageUrl,
                    modifier = Modifier.fillMaxSize().alpha(0.5f),
                    scaleType = ImageView.ScaleType.CENTER_CROP
                )
            }
        }

        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator(
                color = JellyfinTheme.colorScheme.onBackground,
                modifier = Modifier.fillMaxWidth(0.2f)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = text,
                style = JellyfinTheme.typography.default,
                color = JellyfinTheme.colorScheme.onBackground
            )
        }
    }
}
