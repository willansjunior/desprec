package br.com.disprec

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.disprec.adapter.ListaTransacoesAdapter
import br.com.disprec.model.Transacao
import br.com.disprec.model.enums.TipoTransacao
import br.com.disprec.util.extension.formatCurrent
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.resumo_card.*
import java.math.BigDecimal

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list = gerarListaTransacoes()

        montarResumo(list)

        val adapter = ListaTransacoesAdapter(list, this)

        lv_transacoes.adapter = adapter
    }

    private fun montarResumo(list : List<Transacao>) {
        var receita = BigDecimal.ZERO
        var despesa = BigDecimal.ZERO
        var media = BigDecimal.ZERO
        for (transacao in list) {
            if (transacao.tipo == TipoTransacao.RECEITA) {
                receita = receita.plus(transacao.valor)
            } else if (transacao.tipo == TipoTransacao.DESPESA) {
                despesa = despesa.plus(transacao.valor)
            }
        }
        resumo_card_receita.text = receita.formatCurrent(this)
        resumo_card_despesa.text = despesa.formatCurrent(this)
        var total = receita - despesa
        resumo_card_total.text = total.formatCurrent(this)
    }

    private fun gerarListaTransacoes(): List<Transacao> {
        return listOf(
            Transacao(
                valor = BigDecimal(20.50),
                categoria = "Almoço de final de semana",
                tipo = TipoTransacao.DESPESA
            ),
            Transacao(
                valor = BigDecimal(100.0),
                categoria = "Economia",
                tipo = TipoTransacao.RECEITA
            )
        )
    }
}
