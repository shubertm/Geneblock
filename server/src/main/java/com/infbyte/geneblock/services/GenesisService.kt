package com.infbyte.geneblock.services

import blocks
import com.infbyte.shared.models.Block
import com.infbyte.shared.models.Currency
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.sql.Connection
import java.sql.Timestamp
import java.util.Date

class GenesisService(private val connection: Connection): Service<Block> {

    private val iODispatcher = Dispatchers.IO

    init {
        CoroutineScope(iODispatcher).launch {
            init()
            blocks.forEach { insert(it) }
        }
    }

    override suspend fun init() {
        if (!exists()) {
            val statement = connection.createStatement()
            statement.executeUpdate(CREATE_TABLE)
        }
    }

    override suspend fun insert(item: Block) {
        withContext(iODispatcher) {
            try {
                val statement = connection.prepareStatement(INSERT_BLOCK)
                statement.setString(1, item.hash)
                statement.setTimestamp(2, Timestamp(item.date))
                statement.setInt(3, item.size)
                statement.setString(4, item.currency.name)
                statement.setString(5, item.currency.code)
                statement.setInt(6, item.transactions)
                statement.setString(7, item.miner)
                statement.setFloat(8, item.reward)
                statement.executeUpdate()
            } catch (e: Exception) { e.printStackTrace() }
        }
    }

    override suspend fun update(item: Block) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(id: Long) {
        TODO("Not yet implemented")
    }

    override fun get(): Flow<Block> {
        TODO("Not yet implemented")
    }

    override fun getAll(): Flow<List<Block>>  = flow {
        val blocks = mutableListOf<Block>()
        val statement = connection.prepareStatement(SELECT_BLOCKS)
        val resultSet = statement.executeQuery()

        while (resultSet.next()) {
            val hash = resultSet.getString(HASH)
            val date = resultSet.getTimestamp(DATE).time
            val size = resultSet.getInt(SIZE)
            val currencyName = resultSet.getString(CURRENCY_NAME)
            val currencyCode = resultSet.getString(CURRENCY_CODE)
            val transactions = resultSet.getInt(TRANSACTIONS)
            val miner = resultSet.getString(MINER)
            val reward = resultSet.getFloat(REWARD)

            val block = Block(
                hash,
                date,
                size,
                Currency(currencyName, currencyCode),
                transactions,
                miner,
                reward
            )

            blocks.add(block)
        }
        emit(blocks)
    }

    private fun exists(): Boolean {
        val meta = connection.metaData

        val tables = meta.getTables(null, null, TABLE, arrayOf("TABLE"))
        val exists = tables.next()
        return exists
    }

    companion object {
        const val TABLE = "genesis_blocks"
        const val VARCHAR = "VARCHAR(255)"
        const val TIMESTAMP = "TIMESTAMP"
        const val SERIAL = "SERIAL"

        const val HASH = "hash"
        const val DATE = "date"
        const val SIZE = "size"
        const val CURRENCY_NAME = "currency_name"
        const val CURRENCY_CODE = "currency_code"
        const val TRANSACTIONS = "transactions"
        const val MINER = "miner"
        const val REWARD = "reward"

        private const val CREATE_TABLE = "CREATE TABLE $TABLE (" +
                "$HASH $VARCHAR, " +
                "$DATE $TIMESTAMP, " +
                "$SIZE $SERIAL, " +
                "$CURRENCY_NAME $VARCHAR, " +
                "$CURRENCY_CODE $VARCHAR PRIMARY KEY, " +
                "$TRANSACTIONS $SERIAL, " +
                "$MINER $VARCHAR, " +
                "$REWARD $SERIAL" +
                ");"

        private const val INSERT_BLOCK = "INSERT INTO $TABLE (" +
                "$HASH, " +
                "$DATE, " +
                "$SIZE, " +
                "$CURRENCY_NAME, " +
                "$CURRENCY_CODE, " +
                "$TRANSACTIONS, " +
                "$MINER, " +
                REWARD +
                ") VALUES (?, ?, ?, ?, ?, ?, ?, ?);"

        private const val SELECT_BLOCKS = "SELECT * FROM $TABLE"
    }
}