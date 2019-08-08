package br.com.disprec.util

import android.support.v4.content.ContextCompat
import android.view.View
import br.com.disprec.R
import br.com.disprec.model.Transacao
import br.com.disprec.model.enums.TipoTransacao
import br.com.disprec.util.extension.formatCurrent
import kotlinx.android.synthetic.main.resumo_card.view.*
import java.math.BigDecimal

/**
 * Created by willans on 21/05/19.
 */
class ResumoView(private val view : View) {

    private val COR_RECEITA = ContextCompat.getColor(view.context, R.color.receita)
    private val COR_DESPESA = ContextCompat.getColor(view.context, R.color.despesa)

    fun montarResumo(list : List<Transacao>) {
        var receita = BigDecimal.ZERO
        var despesa = BigDecimal.ZERO
        for (transacao in list) {
            if (transacao.tipo == TipoTransacao.RECEITA) {
                receita = receita.plus(transacao.valor)
            } else if (transacao.tipo == TipoTransacao.DESPESA) {
                despesa = despesa.plus(transacao.valor)
            }
        }
        with(view.resumo_card_receita) {
            setTextColor(COR_RECEITA)
            text = receita.formatCurrent(view.context)
        }

        with(view.resumo_card_despesa) {
            setTextColor(COR_DESPESA)
            text = despesa.formatCurrent(view.context)
        }
        val total = receita.subtract(despesa)
        val cor = extrairCor(total)
        with(view.resumo_card_total) {
            setTextColor(cor)
            text = total.formatCurrent(view.context)
        }
    }

    private fun somaPorTipo(tipo: TipoTransacao, list : List<Transacao>): BigDecimal {
        return BigDecimal(list.filter { it.tipo == tipo }
            .sumByDouble { it.valor.toDouble() })
    }

    private fun extrairCor(total: BigDecimal): Int {
        return if (total >= BigDecimal.ZERO) {
            COR_RECEITA
        } else {
            COR_DESPESA
        }
    }

}