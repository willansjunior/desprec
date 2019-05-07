package br.com.disprec

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.disprec.adapter.ListaTransacoesAdapter
import br.com.disprec.model.Transacao
import br.com.disprec.model.enums.TipoTransacao
import kotlinx.android.synthetic.main.activity_main.*
import java.math.BigDecimal

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list = gerarListaTransacoes()

        val adapter = ListaTransacoesAdapter(list, this)

        lv_transacoes.adapter = adapter
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
