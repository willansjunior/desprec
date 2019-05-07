package br.com.disprec.adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import br.com.disprec.R
import br.com.disprec.model.Transacao
import br.com.disprec.model.enums.TipoTransacao
import br.com.disprec.util.extension.formatCurrent
import br.com.disprec.util.extension.formatDate
import kotlinx.android.synthetic.main.transacao_item.view.*

class ListaTransacoesAdapter(private val transacoes : List<Transacao>, private val context : Context) : BaseAdapter() {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflate = LayoutInflater.from(context)
            .inflate(R.layout.transacao_item, parent, false)

        var transacao = transacoes[position]

        if (transacao.tipo == TipoTransacao.RECEITA) {
            inflate.transacao_valor.setTextColor(ContextCompat.getColor(context, R.color.receita))
            inflate.transacao_icone.setBackgroundResource(R.drawable.icone_transacao_item_receita)
        } else {
            inflate.transacao_valor.setTextColor(ContextCompat.getColor(context, R.color.despesa))
            inflate.transacao_icone.setBackgroundResource(R.drawable.icone_transacao_item_despesa)
        }

        inflate.transacao_valor.text = transacao.valor.formatCurrent(context)
        inflate.transacao_categoria.text = transacao.categoria

        inflate.transacao_data.text = transacao.data.formatDate("dd/MM/yyyy")

        return inflate
    }

    override fun getItem(position: Int): Transacao {
        return transacoes[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return transacoes.size
    }
}