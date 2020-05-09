package com.monh.packager.utils.network

object Services {
    object EndPoints{
        const val LOG_IN           = "packager/authenticate"
        const val RESET_PASSWORD   = "packager/resetPassword"
        const val LOG_OUT          = "packager/logout"
        const val CONTACT_US       = "packager/contactUs"
        const val USER_INFO        = "packager/orders/stats"
        const val CHANGE_PASSWORD  = "packager/changePassword"
        const val TERMS_CONDITIONS = "packager/terms"
        const val USER_TOKEN = "packager/device"

        const val NOTIFICATIONS    = "notifications"
        const val ORDERS = "packager/orders"
        const val CHANGE_STATUS = "packager/setStatus"
        const val ORDER_PRODUCTS = "packager/order/items"
        const val START_ORDER = "start_order"
        const val MARK_ORDER_UN_FOUND = "mark_product_unfound"
        const val MARK_ORDER_FOUND = "mark_order_found"
    }
    object Path{
    }
    object QueryParams{
        const val ORDER_ID = "orderId"
        const val STATUS = "status"
        const val PAGE = "page"
    }
    object Headers{
        const val UUID = "x-device-uuid"
    }
}