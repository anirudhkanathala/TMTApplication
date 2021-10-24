package com.example.tmtapplication.utils

import android.util.Log
import com.example.tmtapplication.utils.TUtil.isNonProd


object TLog : ITLog {
    private const val PRE_FIX_TAG = "YumLog -> "
    private var LOG_ENABLE = isNonProd() // Applies just for non-prod
    private const val DETAIL_ENABLE = true // Enabled to see more details

    private fun buildMsg(msg: String): String {
        val buffer = StringBuilder()
        if (DETAIL_ENABLE) {
            val stackTraceElement = Thread.currentThread().stackTrace[4]
            buffer.append("[ ")
            buffer.append(Thread.currentThread().name)
            buffer.append(": ")
            buffer.append(stackTraceElement.fileName)
            buffer.append(": ")
            buffer.append(stackTraceElement.lineNumber)
            buffer.append(": ")
            buffer.append(stackTraceElement.methodName)
        }

        buffer.append("() ] _____ ")

        buffer.append(msg)

        return buffer.toString()
    }

    override fun v(tag: String, msg: String) {
        if (LOG_ENABLE) {
            val localTag = PRE_FIX_TAG + tag
            val localMsg = buildMsg(msg)
            Log.v(localTag, localMsg)
        }
    }

    override fun d(tag: String, msg: String) {
        if (LOG_ENABLE) {
            val localTag = PRE_FIX_TAG + tag
            val localMsg = buildMsg(msg)
            Log.d(localTag, localMsg)
        }
    }

    override fun i(tag: String, msg: String) {
        if (LOG_ENABLE) {
            val localTag = PRE_FIX_TAG + tag
            val localMsg = buildMsg(msg)
            Log.i(localTag, localMsg)
        }
    }

    override fun w(tag: String, msg: String) {
        if (LOG_ENABLE) {
            val localTag = PRE_FIX_TAG + tag
            val localMsg = buildMsg(msg)
            Log.w(localTag, localMsg)
        }
    }

    override fun e(tag: String, msg: String) {
        if (LOG_ENABLE) {
            val localTag = PRE_FIX_TAG + tag
            val localMsg = buildMsg(msg)
            Log.e(localTag, localMsg)
        }
    }

}

interface ITLog {
    private companion object {
        private const val TAG = "YumLog"
    }

    fun v(tag: String = TAG, msg: String) {}
    fun d(tag: String = TAG, msg: String) {}
    fun i(tag: String = TAG, msg: String) {}
    fun w(tag: String = TAG, msg: String) {}
    fun e(tag: String = TAG, msg: String) {}
}


