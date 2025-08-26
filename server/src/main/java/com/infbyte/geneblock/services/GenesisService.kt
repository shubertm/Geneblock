package com.infbyte.geneblock.services

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

class GenesisService(private val connection: Connection): Service<Block> {

    private val iODispatcher = Dispatchers.IO

    init {
        CoroutineScope(iODispatcher).launch {
            init()
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
                statement.setInt(3, item.height)
                statement.setInt(4, item.depth)
                statement.setTimestamp(5, Timestamp(item.distance))
                statement.setFloat(6, item.capacity)
                statement.setInt(7, item.size)
                statement.setString(8, item.currency.name)
                statement.setString(9, item.currency.code)
                statement.setFloat(10, item.currency.amount)
                statement.setFloat(11, item.value)
                statement.setFloat(12, item.valueToday)
                statement.setFloat(13, item.input.amount)
                statement.setFloat(14, item.output.amount)
                statement.setInt(15, item.transactions)
                statement.setInt(16, item.inputs)
                statement.setInt(17, item.outputs)
                statement.setString(18, item.miner)
                statement.setFloat(19, item.reward)
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
            val height = resultSet.getInt(HEIGHT)
            val depth = resultSet.getInt(DEPTH)
            val distance = resultSet.getTimestamp(DISTANCE).time
            val capacity = resultSet.getFloat(CAPACITY)
            val size = resultSet.getInt(SIZE)
            val currencyName = resultSet.getString(CURRENCY_NAME)
            val currencyCode = resultSet.getString(CURRENCY_CODE)
            val currencyAmount = resultSet.getFloat(CURRENCY_AMOUNT)
            val value = resultSet.getFloat(VALUE)
            val valueToday = resultSet.getFloat(VALUE_TODAY)
            val inputAmount = resultSet.getFloat(INPUT)
            val outputAmount = resultSet.getFloat(OUTPUT)
            val transactions = resultSet.getInt(TRANSACTIONS)
            val inputs = resultSet.getInt(INPUTS)
            val outputs = resultSet.getInt(OUTPUTS)
            val miner = resultSet.getString(MINER)
            val reward = resultSet.getFloat(REWARD)

            val block = Block(
                hash,
                date,
                height,
                depth,
                distance,
                capacity,
                size,
                Currency(currencyName, currencyCode, currencyAmount),
                value,
                valueToday,
                Currency(currencyName, currencyCode, inputAmount),
                Currency(currencyName, currencyCode, outputAmount),
                transactions,
                inputs,
                outputs,
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
        const val HEIGHT = "height"
        const val DEPTH = "depth"
        const val DISTANCE = "distance"
        const val CAPACITY = "capacity"
        const val SIZE = "size"
        const val CURRENCY_NAME = "currency_name"
        const val CURRENCY_CODE = "currency_code"
        const val CURRENCY_AMOUNT = "currency_amount"
        const val VALUE = "value"
        const val VALUE_TODAY = "valueToday"
        const val INPUT = "input"
        const val OUTPUT = "output"
        const val TRANSACTIONS = "transactions"
        const val INPUTS = "inputs"
        const val OUTPUTS = "outputs"
        const val MINER = "miner"
        const val REWARD = "reward"

        private const val CREATE_TABLE = "CREATE TABLE $TABLE (" +
                "$HASH $VARCHAR PRIMARY KEY, " +
                "$DATE $TIMESTAMP, " +
                "$HEIGHT $SERIAL, " +
                "$DEPTH $SERIAL, " +
                "$DISTANCE $TIMESTAMP, " +
                "$CAPACITY $SERIAL, " +
                "$SIZE $SERIAL, " +
                "$CURRENCY_NAME $VARCHAR, " +
                "$CURRENCY_CODE $VARCHAR, " +
                "$CURRENCY_AMOUNT $SERIAL, " +
                "$VALUE $SERIAL, " +
                "$VALUE_TODAY $SERIAL, " +
                "$INPUT $SERIAL, " +
                "$OUTPUT $SERIAL, " +
                "$TRANSACTIONS $SERIAL, " +
                "$INPUTS $SERIAL, " +
                "$OUTPUTS $SERIAL, " +
                "$MINER $VARCHAR, " +
                "$REWARD $SERIAL" +
                ");"

        private const val INSERT_BLOCK = "INSERT INTO $TABLE (" +
                "$HASH, " +
                "$DATE, " +
                "$HEIGHT, " +
                "$DEPTH, " +
                "$DISTANCE, " +
                "$CAPACITY, " +
                "$SIZE, " +
                "$CURRENCY_NAME, " +
                "$CURRENCY_CODE, " +
                "$CURRENCY_AMOUNT, " +
                "$VALUE, " +
                "$VALUE_TODAY, " +
                "$INPUT, " +
                "$OUTPUT, " +
                "$TRANSACTIONS, " +
                "$INPUTS, " +
                "$OUTPUTS, " +
                "$MINER, " +
                REWARD +
                ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);"

        private const val SELECT_BLOCKS = "SELECT * FROM $TABLE"
    }
}