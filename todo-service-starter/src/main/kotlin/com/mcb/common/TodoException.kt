package com.mcb.common

const val ITEM_NOT_FOUND = "Item not found"
const val LIST_EMPTY = "List is empty"
const val STATUS_ALREADY_SET = "No status update required"

class TodoException(message:String): Exception(message)