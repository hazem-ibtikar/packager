package com.monh.packager.utils.network

object Services {
    object EndPoints{
        const val LOG_IN           = "packager/authenticate"
        const val RESET_PASSWORD   = "packager/resetPassword"
        const val LOG_OUT          = "packager/logout"
        const val CONTACT_US       = "packager/contactUs"
        const val USER_INFO        = "packager/orders/stats"
        const val TERMS_CONDITIONS = "packager/terms"
        const val URGENT_ORDERS = "urgent-orders"
        const val OPEN_ORDERS   = "urgent-orders"
        const val CLOSED_ORDERS = "urgent-orders"
        const val USER_TOKEN = "packager/device"
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
    object Headers{
        const val UUID = "x-device-uuid"
    }
}