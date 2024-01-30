package com.lifo.sync.io.jdbc.`type`.bridge.bedrock

import com.lifo.sync.io.jdbc.`type`.PostgresqlRow
import com.lifo.sync.utils.Utils

case class WithdrawalProvenOnL1(
                                 version: Int,
                                 index: Long,
                                 withdrawal_hash: String,
                                 l1_proven_transaction_hash: String,
                                 l1_proven_block_number: Long,
                                 l1_proven_block_timestamp: Long,
                                 l1_proven_from_address: String,
                                 l1_proven_to_address: String,
                               ) extends PostgresqlRow {
  override def onConflictSQL(tableName: String): String = {
    s"""
       |ON CONFLICT (version, index) DO UPDATE
       |SET l1_proven_transaction_hash = EXCLUDED.l1_proven_transaction_hash,
       |    l1_proven_block_number = EXCLUDED.l1_proven_block_number,
       |    l1_proven_block_timestamp = EXCLUDED.l1_proven_block_timestamp,
       |    l1_proven_from_address = EXCLUDED.l1_proven_from_address,
       |    l1_proven_to_address = EXCLUDED.l1_proven_to_address
       |""".stripMargin
  }

  override def tableName: String = "l2_to_l1_txns"
}