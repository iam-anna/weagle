package com.fiapos.weagle.ui.theme

import androidx.compose.ui.graphics.Color
import com.fiapos.weagle.domain.models.OrientationCategory

data class OrientationColors(
    val background: Color,
    val foreground: Color,
    val border: Color
)

fun OrientationCategory.toColor(): OrientationColors {
    return when(this) {

        OrientationCategory.INNOVATION -> {
            OrientationColors(
                background = Color(0xFFDDF1F8),
                foreground = Color(0xFF01514E),
                border = Color(0xFF01514E),
            )
        }

        OrientationCategory.COST_REDUCTION -> {
            OrientationColors(
                background = Color(0xFFFEF4B7),
                foreground = Color(0xFF775201),
                border = Color(0xFF775201),
            )
        }

        OrientationCategory.PRODUCTIVITY -> {
            OrientationColors(
                background = Color(0xFFE7FFE5),
                foreground = Color(0xFF003A00),
                border = Color(0xFF003A00),
            )
        }

        OrientationCategory.SUSTAINABILITY -> {
            OrientationColors(
                background = Color(0xFFFFE2AE),
                foreground = Color(0xFF603502),
                border = Color(0xFF603502),
            )
        }

        OrientationCategory.TECHNOLOGY -> {
            OrientationColors(
                background = Color(0xFFD9E7FF),
                foreground = Color(0xFF011260),
                border = Color(0xFF011260),
            )
        }
    }
}