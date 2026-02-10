package com.jsaldana.superheroapp.ui.screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun MainHeader() {
	Row(
		modifier = Modifier
			.fillMaxWidth()
			.padding(24.dp),
		horizontalArrangement = Arrangement.End,
		verticalAlignment = Alignment.CenterVertically
	) {
		Column(horizontalAlignment = Alignment.End) {
			Text(
				text = "hero",
				style = MaterialTheme.typography.displayMedium.copy(
					fontWeight = FontWeight.Bold,
					color = Color(0xFF2D3135)
				)
			)
			Text(
				text = "the Superhero Encyclopedia",
				style = MaterialTheme.typography.labelSmall,
				color = Color.Gray
			)
		}
	}
}