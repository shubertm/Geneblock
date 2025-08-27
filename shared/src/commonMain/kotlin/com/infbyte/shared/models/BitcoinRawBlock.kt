package com.infbyte.shared.models

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class BitcoinRawBlock (
    @SerialName("hash"        ) var hash       : String?           = null,
    @SerialName("ver"         ) var ver        : Long?              = null,
    @SerialName("prev_block"  ) var prevBlock  : String?           = null,
    @SerialName("mrkl_root"   ) var mrklRoot   : String?           = null,
    @SerialName("time"        ) var time       : Long?              = null,
    @SerialName("bits"        ) var bits       : Long?              = null,
    @SerialName("next_block"  ) var nextBlock  : ArrayList<String> = arrayListOf(),
    @SerialName("fee"         ) var fee        : Long?              = null,
    @SerialName("nonce"       ) var nonce      : Long?              = null,
    @SerialName("n_tx"        ) var nTx        : Long?              = null,
    @SerialName("size"        ) var size       : Long?              = null,
    @SerialName("block_index" ) var blockIndex : Long?              = null,
    @SerialName("main_chain"  ) var mainChain  : Boolean?          = null,
    @SerialName("height"      ) var height     : Long?              = null,
    @SerialName("weight"      ) var weight     : Long?              = null,
    @SerialName("tx"          ) var tx         : ArrayList<Tx>     = arrayListOf()
)

@Serializable
data class Tx (
    @SerialName("hash"         ) var hash        : String?           = null,
    @SerialName("ver"          ) var ver         : Long?              = null,
    @SerialName("vin_sz"       ) var vinSz       : Long?              = null,
    @SerialName("vout_sz"      ) var voutSz      : Long?              = null,
    @SerialName("size"         ) var size        : Long?              = null,
    @SerialName("weight"       ) var weight      : Long?              = null,
    @SerialName("fee"          ) var fee         : Long?              = null,
    @SerialName("relayed_by"   ) var relayedBy   : String?           = null,
    @SerialName("lock_time"    ) var lockTime    : Long?              = null,
    @SerialName("tx_index"     ) var txIndex     : Long?              = null,
    @SerialName("double_spend" ) var doubleSpend : Boolean?          = null,
    @SerialName("time"         ) var time        : Long?              = null,
    @SerialName("block_index"  ) var blockIndex  : Long?              = null,
    @SerialName("block_height" ) var blockHeight : Long?              = null,
    @SerialName("inputs"       ) var inputs      : ArrayList<Inputs> = arrayListOf(),
    @SerialName("out"          ) var out         : ArrayList<Out>    = arrayListOf()
)

@Serializable
data class Inputs (
    @SerialName("sequence" ) var sequence : Long?     = null,
    @SerialName("witness"  ) var witness  : String?  = null,
    @SerialName("script"   ) var script   : String?  = null,
    @SerialName("index"    ) var index    : Long?     = null,
    @SerialName("prev_out" ) var prevOut  : PrevOut? = PrevOut()
)

@Serializable
data class Out (
    @SerialName("type"               ) var type              : Long?              = null,
    @SerialName("spent"              ) var spent             : Boolean?          = null,
    @SerialName("value"              ) var value             : Long?              = null,
    @SerialName("spending_outpoints" ) var spendingOutpoints : ArrayList<String> = arrayListOf(),
    @SerialName("n"                  ) var n                 : Long?              = null,
    @SerialName("tx_index"           ) var txIndex           : Long?              = null,
    @SerialName("script"             ) var script            : String?           = null,
    @SerialName("addr"               ) var addr              : String?           = null
)

@Serializable
data class PrevOut (
    @SerialName("type"               ) var type              : Long?                         = null,
    @SerialName("spent"              ) var spent             : Boolean?                     = null,
    @SerialName("value"              ) var value             : Long?                         = null,
    @SerialName("spending_outpoints" ) var spendingOutpoints : ArrayList<SpendingOutpoints> = arrayListOf(),
    @SerialName("n"                  ) var n                 : Long?                         = null,
    @SerialName("tx_index"           ) var txIndex           : Long?                         = null,
    @SerialName("script"             ) var script            : String?                      = null
)

@Serializable
data class SpendingOutpoints (
    @SerialName("tx_index" ) var txIndex : Long? = null,
    @SerialName("n"        ) var n       : Long? = null
)