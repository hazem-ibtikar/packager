package com.monh.packager.utils.network

object Services {
    object EndPoints{
        const val LOG_IN              = "packager/authenticate"
        const val RESET_PASSWORD      = "packager/resetPassword"
        const val LOG_OUT             = "packager/logout"
        const val CONTACT_US          = "packager/contactUs"
        const val USER_INFO           = "packager/orders/stats"
        const val CHANGE_PASSWORD     = "packager/changePassword"
        const val UPDATE_PACKAGER     = "packager/update"
        const val UPLOAD_IMAGE        = "packager/uploadImg"
        const val TERMS_CONDITIONS    = "packager/terms"
        const val USER_TOKEN          = "packager/device"
        const val ORDERS              = "packager/orders"
        const val CHANGE_STATUS       = "packager/setStatus"
        const val ORDER_PRODUCTS      = "packager/order/items"
        const val START_ORDER         = "packager/orders/assignToPackager"
        const val MARK_ORDER_UN_FOUND = "packager/order/item/setNotFound"
        const val MARK_ORDER_FOUND    = "packager/order/item/setFoundCount"
        const val NOTIFICATIONS       = "packager/getNotifications"
        const val MARK_AS_PACKAGED    = "packager/order/markAsPackaged"
    }
    object Path{
    }
    object QueryParams{
        const val ORDER_ID = "orderId"
        const val PRODUCT_IN_ORDER = "orderItemId"
        const val STATUS = "status"
        const val PAGE = "page"
    }
    object Headers{
        const val UUID = "x-device-uuid"
    }
}