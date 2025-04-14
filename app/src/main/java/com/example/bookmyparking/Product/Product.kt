package com.example.bookmyparking.Product

data class Product(
    var image: String = "",
    var imageResource: Int = 0,
    var category: String = "",
    var name: String = "",
    var price: Double = 0.0,
    var rating: Float = 0.0f,
    var productId: String = "",
    var productInformation: String = ""
) {
    constructor() : this("", 0, "", "", 0.0, 0.0f, "", "")
}
