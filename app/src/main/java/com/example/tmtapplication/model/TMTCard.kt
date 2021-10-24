package com.example.tmtapplication.model

data class TMTCard(
    val card: Card,
    val card_type: String
) {
    data class Card(
        val attributes: Attributes,
        val description: Description,
        val image: Image,
        val title: Title,
        val value: String
    )

    data class Description(
        val attributes: Attributes,
        val value: String
    )

    data class Attributes(
        val font: Font,
        val text_color: String
    ) {
        data class Font(
            val size: Int
        )
    }

    data class Image(
        val size: Size,
        val url: String
    ) {
        data class Size(
            val height: Int,
            val width: Int
        )
    }

    data class Title(
        val attributes: Attributes,
        val value: String
    )
}
