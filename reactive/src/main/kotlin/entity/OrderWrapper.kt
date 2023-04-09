package entity

data class OrderWrapper(
    var id : Int,
    var name : String,
    var price : Double,
    var currency: String
)