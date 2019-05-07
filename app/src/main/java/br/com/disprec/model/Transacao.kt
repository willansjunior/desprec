package br.com.disprec.model

import br.com.disprec.model.enums.TipoTransacao
import java.math.BigDecimal
import java.util.Calendar

class Transacao (val valor: BigDecimal,
                 val categoria: String,
                 val tipo: TipoTransacao,
                 val data: Calendar = Calendar.getInstance())