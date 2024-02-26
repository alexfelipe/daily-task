package br.com.alexf.dailytask.ui.features

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import kotlin.random.Random

@Composable
fun TasksListScreen() {
    Column(
        Modifier.fillMaxSize()
    ) {
        TaskItem(
            LoremIpsum(Random.nextInt(1, 10)).values.first(),
            LoremIpsum(Random.nextInt(1, 10)).values.first(),
            "01/01/2024 00:00"
        )
    }
}

@Composable
private fun TaskItem(title: String, description: String, datetime: String) {
    Column(
        Modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = title,
            Modifier.fillMaxWidth(),
            maxLines = 3,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = description,
            Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = Color.Gray.copy(alpha = 0.8f),
                    RoundedCornerShape(8.dp)
                )
                .padding(16.dp),
            style = LocalTextStyle.current.copy(
                fontStyle = FontStyle.Italic,
            )
        )
        Text(
            text = datetime,
            Modifier.fillMaxWidth(),
            style = LocalTextStyle.current.copy(
                textAlign = TextAlign.End,
                fontWeight = FontWeight.Bold
            )
        )
    }
}

@Preview(
    name = "task item ",
    showBackground = true
)
@Composable
private fun TaskItemPreview() {
    TaskItem(
        LoremIpsum(10).values.first(),
        LoremIpsum(10).values.first(),
        "01/01/2024 00:00"
    )
}

@Preview(
    name = "task item with large title",
    showBackground = true
)
@Composable
private fun TaskItem2Preview() {
    TaskItem(
        LoremIpsum(100).values.first(),
        LoremIpsum(10).values.first(),
        "01/01/2024 00:00"
    )
}

@Preview(
    name = "task item with large description",
    showBackground = true
)
@Composable
private fun TaskItem3Preview() {
    TaskItem(
        LoremIpsum(10).values.first(),
        LoremIpsum(100).values.first(),
        "01/01/2024 00:00"
    )
}

@Preview(
    name = "task item with large title and description",
    showBackground = true
)
@Composable
private fun TaskItem4Preview() {
    TaskItem(
        LoremIpsum(100).values.first(),
        LoremIpsum(100).values.first(),
        "01/01/2024 00:00"
    )
}

@Preview(
    showBackground = true
)
@Composable
private fun TasksListScreenPreview() {
    TasksListScreen()
}
