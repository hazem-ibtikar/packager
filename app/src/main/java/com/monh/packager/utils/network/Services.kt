package com.monh.packager.utils.network

object Services {
    object EndPoints{
        const val URGENT_ORDERS = "urgent-orders"
        const val OPEN_ORDERS   = "urgent-orders"
        const val CLOSED_ORDERS = "urgent-orders"
        const val LOG_IN = "packager/authenticate"
        const val USER_TOKEN = "packager/device"
        const val USER_INFO = "info"
        const val CHANGE_STATUS = "changeStatus"
        const val ORDER_PRODUCTS = "order_products"
        const val START_ORDER = "start_order"
        const val MARK_ORDER_UN_FOUND = "mark_product_unfound"
        const val MARK_ORDER_FOUND = "mark_order_found"
    }
    object Path{
    }
    object QueryParams{
        const val ORDER_ID = "order_id"
    }
}