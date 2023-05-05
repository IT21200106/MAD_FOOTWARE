package com.example.seller.Database

import android.provider.BaseColumns

object AdminProfile {
    // Table contents are grouped together in an anonymous object.
    object Admin_DB : BaseColumns {
        const val TABLE_NAME = "Admin_DB"
        const val PRODUCT_ID="id"
        const val FOOTWARE_TYPE = "type"
        const val FOOTWARE_NAME  = "name"
        const val MANUFACTURE_DATE  = "date"
        const val PRICE  = "price"
        const val SIZE  = "size"
    }
}

//footwear type,name,manufacture
//date,price,size