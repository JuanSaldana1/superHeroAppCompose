package com.jsaldana.superheroapp.ui.screens.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun SectionHeader(
	title: String,
	icon: ImageVector? = null,
	emoji: String? = null
) {
	Row(
		modifier = Modifier
			.fillMaxWidth()
			.padding(horizontal = 24.dp, vertical = 16.dp),
		verticalAlignment = Alignment.CenterVertically
	) {
		Text(
			text = title,
			style = MaterialTheme.typography.displaySmall.copy(
				fontWeight = FontWeight.ExtraBold,
				color = Color(0xFF2D3135)
			)
		)
		Spacer(modifier = Modifier.width(8.dp))
		if (icon != null) {
			Icon(
				imageVector = icon,
				contentDescription = null,
				modifier = Modifier.size(32.dp),
				tint = Color(0xFF2D3135)
			)
		} else if (emoji != null) {
			Text(text = emoji, style = MaterialTheme.typography.displaySmall)
		}
	}
}