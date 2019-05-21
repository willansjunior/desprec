package br.com.disprec.util.extension

import android.content.Context
import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

fun Calendar.formatDate(format:String) : String {
    return SimpleDateFormat(format).format(this.time)
}

fun BigDecimal.formatCurrent(context: Context) : String {
    val currencyInstance = DecimalFormat.getCurrencyInstance(
        Locale(
            context.resources.configuration.locale.language,
            context.resources.configuration.locale.country
        )
    )
    return currencyInstance
        .format(this)
        .replace("R$", "R$ ")
        .replace("-R$ ", "R$ -")
}