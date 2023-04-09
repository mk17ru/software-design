package entity

class User(
    var login : String,
    var currency : CURRENCY
)

enum class CURRENCY {
    DOLLAR,
    RUBLES,
    EURO
}