package com.example.shipment.Database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.media.tv.AdResponse
import android.provider.BaseColumns




class DBHandler(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    companion object {
        // If you change the database schema, you must increment the database version.
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "Database.db"
    }


    private val SQL_CREATE_ENTRIES =
        "CREATE TABLE ${ShippingDetails.ShipmentInfo.TABLE_NAME} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                "${ShippingDetails.ShipmentInfo.col1} TEXT," +
                "${ShippingDetails.ShipmentInfo.col2} TEXT," +
                "${ShippingDetails.ShipmentInfo.col3} TEXT," +
                "${ShippingDetails.ShipmentInfo.col4} TEXT," +
                "${ShippingDetails.ShipmentInfo.col5} TEXT)"

    private val SQL_DELETE_ENTRIES =
        "DROP TABLE IF EXISTS ${ShippingDetails.ShipmentInfo.TABLE_NAME}"

    //insert function
    fun addinfo (Name:String,Email:String,Address:String,ZipCode:String,PhoneNum:Int): Long? {
        // Gets the data repository in write mode
        val db = writableDatabase

// Create a new map of values, where column names are the keys
        val values = ContentValues().apply {
            put(ShippingDetails.ShipmentInfo.col1,Name)
            put(ShippingDetails.ShipmentInfo.col2,Email)
            put(ShippingDetails.ShipmentInfo.col3,Address)
            put(ShippingDetails.ShipmentInfo.col4,ZipCode)
            put(ShippingDetails.ShipmentInfo.col5,PhoneNum)

        }

// Insert the new row, returning the primary key value of the new row
        val newRowId = db?.insert(ShippingDetails.ShipmentInfo.TABLE_NAME, null, values)

        return newRowId
    }

    //update function
    fun updateinfo (Name:String,Email:String,Address:String,ZipCode:String,PhoneNum:String) {
        // Gets the data repository in write mode
        val db = writableDatabase


        // New value for one column
        val values = ContentValues().apply {
            put(ShippingDetails.ShipmentInfo.col1, Name)
            put(ShippingDetails.ShipmentInfo.col2, Email)
            put(ShippingDetails.ShipmentInfo.col3, Address)
            put(ShippingDetails.ShipmentInfo.col4, ZipCode)
            //put(ShippingDetails.ShipmentInfo.col5,PhoneNum)
        }

        // Which row to update, based on the title
        val selection = "${ShippingDetails.ShipmentInfo.col5} LIKE ?"
        val selectionArgs = arrayOf(PhoneNum)
        val count = db.update(
            ShippingDetails.ShipmentInfo.TABLE_NAME,
            values,
            selection,
            selectionArgs )
    }
        //delete function
        fun deleteinfo (PhoneNum:String) {
            // Gets the data repository in write mode
            val db = writableDatabase

            // Define 'where' part of query.
            val selection = "${ShippingDetails.ShipmentInfo.col5} LIKE ?"
            // Specify arguments in placeholder order.
            val selectionArgs = arrayOf(PhoneNum)
            // Issue SQL statement.
            val deletedRows =
                db.delete(ShippingDetails.ShipmentInfo.TABLE_NAME, selection, selectionArgs)
        }

        //read function
        fun readAllIinfo():MutableList<Long> {
            val Name : String = "Binu"
            val db = readableDatabase

            // Define 'where' part of query.
            val projection = arrayOf(BaseColumns._ID,(ShippingDetails.ShipmentInfo.col1)
                                                    ,(ShippingDetails.ShipmentInfo.col2)
                                                    ,(ShippingDetails.ShipmentInfo.col3)
                                                    ,(ShippingDetails.ShipmentInfo.col4)
                                                    ,(ShippingDetails.ShipmentInfo.col5)
                // Filter results WHERE "title" = 'My Title'
            val selection ="${ShippingDetails.ShippingInfo.col1} = ? ;"
            val selectionArgs = arrayOf( Name )
            // How you want the results sorted in the resulting Cursor
            val sortOrder = "${ShippingDetails.ShipmentInfo.col1} DESC"

            val cursor = db.query(
                ShippingDetails.ShipmentInfo.TABLE_NAME,
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
               null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
            )

            val shippingData= mutableListOf<Long>()
            with (cursor){
                while(moveToNext()) {
                    val Name:String = getString(getColumnIndexOrThrow(ShippingDetails.ShipmentInfo.col1))
                    val Email = getString(getColumnIndexOrThrow(ShippingDetails.ShipmentInfo.col2))
                    val Address = getString(getColumnIndexOrThrow(ShippingDetails.ShipmentInfo.col3))
                    val ZipCode = getString(getColumnIndexOrThrow(ShippingDetails.ShipmentInfo.col4))
                    val PhoneNum = getString(getColumnIndexOrThrow(ShippingDetails.ShipmentInfo.col5))
                    shippingData.add(Name)//0
                    shippingData.add(Email)//1
                    shippingData.add(Address)//2
                    shippingData.add(ZipCode)//3
                    shippingData.add(PhoneNum)//4

                }
            }
            cursor.close()
            return shippingData
        }
    }

    private fun <E> MutableList<E>.add(name: String) {
    TODO("Not yet implemented")
    }

