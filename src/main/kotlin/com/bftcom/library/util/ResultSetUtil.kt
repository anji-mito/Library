package com.bftcom.library.util

import java.sql.ResultSet

private fun ResultSet.getIntOrNull(columnLabel: String): Int? {
    val value = this.getInt(columnLabel)
    return if (value == 0) null else value
}