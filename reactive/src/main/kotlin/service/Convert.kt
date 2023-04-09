package service

import entity.CURRENCY

class Convert {

    var dollarToCurrency = mapOf<CURRENCY, Double>(
        CURRENCY.DOLLAR to 1.0,
        CURRENCY.EURO to 1.2,
        CURRENCY.RUBLES to 30.0
    )

    fun convert(dollars : Double, currency: CURRENCY) : Double {
        return dollars * dollarToCurrency[currency]!!
    }

}