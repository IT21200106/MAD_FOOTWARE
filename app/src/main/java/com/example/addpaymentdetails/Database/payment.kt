package com.example.addpaymentdetails.Database



import android.provider.BaseColumns


object ShippingDetails {
    // Table contents are grouped together in an anonymous object.
    object ShipmentInfo : BaseColumns {
        const val TABLE_NAME = "ShipmentInfo"
        const val col1 = "Name"
        const val col2 = "Email"
        const val col3 = "Address"
        const val col4 = "ZipCode"
        const val col5 = "PhoneNum"

    }