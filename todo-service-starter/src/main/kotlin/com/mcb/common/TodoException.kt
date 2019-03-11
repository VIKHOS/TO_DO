package com.mcb.common

const val ITEM_NOT_FOUND = "Item not found"
const val LIST_EMPTY = "List is empty"
const val STATUS_ALREADY_SET = "No status update required"
const val ALREADY_EXISTS = "Item already exists"
const val BLANK_INPUT = "Input parameter is blank"

class TodoException(message:String = ""): Exception(message)
