package com.becomedev.unliminetpro.data.enums

enum class NetworkCompEnum(name: String) {
    TRUE("true move"),
    AIS("ais"),
    DTAC("dtac")
    ;

    companion object {
        fun fromCode(pos: Int): NetworkCompEnum {
            NetworkCompEnum.entries.find { pos == it.ordinal }?.let {
                return it
            }
            return TRUE
        }
    }
}
