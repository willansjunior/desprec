package br.com.disprec

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.DatePicker
import br.com.disprec.adapter.ListaTransacoesAdapter
import br.com.disprec.model.Transacao
import br.com.disprec.model.enums.TipoTransacao
import br.com.disprec.util.ResumoView
import br.com.disprec.util.extension.formatDate
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.form_transacao.view.*
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private val list: MutableList<Transacao> = mutableListOf()

    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val list = gerarListaTransacoes()

        ResumoView(window.decorView).montarResumo(list)

        val adapter = ListaTransacoesAdapter(list, this)

        lv_transacoes.adapter = adapter

        lista_transacoes_adiciona_receita.setOnClickListener {
            val view: View = window.decorView
            val viewCriada = LayoutInflater
                .from(this)
                .inflate(R.layout.form_transacao, view as ViewGroup, false)

            viewCriada.form_transacao_data
                .setText(Calendar
                    .getInstance()
                    .formatDate("dd/MM/yyyy"))

            viewCriada.form_transacao_data.setOnClickListener {
                DatePickerDialog(this,
                    DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                        val date = Calendar.getInstance()
                        date.set(year, month, dayOfMonth)
                        viewCriada.form_transacao_data.setText(date.formatDate("dd/MM/yyyy"))
                    }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH))
                    .show()
            }

            val adapterCategoria = ArrayAdapter
                .createFromResource(this, R.array.categorias_de_receita, android.R.layout.simple_spinner_dropdown_item)
            viewCriada.form_transacao_categoria.adapter = adapterCategoria

            AlertDialog
                .Builder(this)
                .setTitle(R.string.adiciona_receita)
                .setPositiveButton("Adicionar"
                    ) { _, _ ->
                    val dateFormat = SimpleDateFormat("dd/MM/yyyy").parse(viewCriada.form_transacao_data.text.toString())
                    val data = Calendar.getInstance()

                    data.time = dateFormat

                    val transacao = Transacao(
                        valor = BigDecimal(viewCriada.form_transacao_valor.text.toString()),
                        tipo = TipoTransacao.RECEITA,
                        data = data,
                        categoria = viewCriada.form_transacao_categoria.selectedItem.toString()
                    )

                    list.add(transacao)
                    ResumoView(window.decorView).montarResumo(list)
                    adapter.notifyDataSetChanged()

                    lista_transacoes_adiciona_menu.close(true)
                }

                .setNegativeButton("Cancelar", null)
                .setView(viewCriada)
                .show()
        }

        lista_transacoes_adiciona_despesa.setOnClickListener {
            
        }
    }

//    private fun montarResumo(list : List<Transacao>) {
//        var receita = BigDecimal.ZERO
//        var despesa = BigDecimal.ZERO
//        var media = BigDecimal.ZERO
//        for (transacao in list) {
//            if (transacao.tipo == TipoTransacao.RECEITA) {
//                receita = receita.plus(transacao.valor)
//            } else if (transacao.tipo == TipoTransacao.DESPESA) {
//                despesa = despesa.plus(transacao.valor)
//            }
//        }
//        resumo_card_receita.text = receita.formatCurrent(this)
//        resumo_card_despesa.text = despesa.formatCurrent(this)
//        var total = receita - despesa
//        resumo_card_total.text = total.formatCurrent(this)
//    }

//    private fun gerarListaTransacoes(): List<Transacao> {
//        return listOf(
//            Transacao(
//                valor = BigDecimal(20.50),
//                categoria = "Almoço de final de semana",
//                tipo = TipoTransacao.DESPESA
//            ),
//            Transacao(
//                valor = BigDecimal(100.0),
//                categoria = "Economia",
//                tipo = TipoTransacao.RECEITA
//            )
//        )
//    }
}
