package org.jellyfin.androidtv.ui.shared

import android.content.Context
import android.util.AttributeSet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.AbstractComposeView
import org.jellyfin.sdk.model.api.BaseItemDto

class LoadingIndicatorView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AbstractComposeView(context, attrs, defStyleAttr) {

    var currentItem: BaseItemDto? by mutableStateOf(null)

    @Composable
    override fun Content() {
        LoadingIndicator(item = currentItem)
    }
}
