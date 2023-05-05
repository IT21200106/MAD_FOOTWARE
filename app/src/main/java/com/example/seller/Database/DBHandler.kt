package com.example.seller.Database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
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
        const val DATABASE_NAME = "FeedReader.db"
    }


    private val SQL_CREATE_ENTRIES =
        "CREATE TABLE ${AdminProfile.Admin_DB.TABLE_NAME} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                "${AdminProfile.Admin_DB.PRODUCT_ID} TEXT," +
                "${AdminProfile.Admin_DB.FOOTWARE_TYPE} TEXT," +
                "${AdminProfile.Admin_DB.FOOTWARE_NAME} TEXT," +
                "${AdminProfile.Admin_DB.MANUFACTURE_DATE} TEXT," +
                "${AdminProfile.Admin_DB.PRICE} INTEGER," +
                "${AdminProfile.Admin_DB.SIZE} INTEGER)"


    private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${AdminProfile.Admin_DB.TABLE_NAME}"


    //Insert Function

    fun addInfo(id:Int,type:String,name:String,date:String,price:Int,size:Int):Long?{
        // Gets the data repository in write mode
        val db = writableDatabase

// Create a new map of values, where column names are the keys
        val values = ContentValues().apply {
            put(AdminProfile.Admin_DB.PRODUCT_ID,id)
            put(AdminProfile.Admin_DB.FOOTWARE_TYPE,type)
            put(AdminProfile.Admin_DB.FOOTWARE_NAME,name)
            put(AdminProfile.Admin_DB.MANUFACTURE_DATE,date)
            put(AdminProfile.Admin_DB.PRICE,price)
            put(AdminProfile.Admin_DB.SIZE,size)
        }

// Insert the new row, returning the primary key value of the new row
        val newRowId = db?.insert(AdminProfile.Admin_DB.TABLE_NAME, null, values)

        return newRowId
    }

    //update function

    fun updateInfo(id:Int,type:String,name:String,date:String,price:Int,size:Int){
        val db=writableDatabase



// New value for one column
        //val title = "MyNewTitle"
        val values = ContentValues().apply {
            put(AdminProfile.Admin_DB.FOOTWARE_NAME,name)
            put(AdminProfile.Admin_DB.PRICE,price)
            put(AdminProfile.Admin_DB.SIZE,size)
        }

// Which row to update, based on the title
        val selection = "${AdminProfile.Admin_DB.PRODUCT_ID} LIKE ?"
        val selectionArgs = arrayOf(id)   //dout
        val count = db.update(
            AdminProfile.Admin_DB.TABLE_NAME,
            values,
            selection,
            selectionArgs)
    }


    //delete function

    fun deleteInfo(id:Int){
        val db=writableDatabase

        // Define 'where' part of query.
        val selection = "${AdminProfile.Admin_DB.PRODUCT_ID} LIKE ?"
// Specify arguments in placeholder order.
        val selectionArgs = arrayOf(id)
// Issue SQL statement.
        val deletedRows = db.delete(AdminProfile.Admin_DB.TABLE_NAME, selection, selectionArgs)
    }

    //read function

    fun readAllInfo():MutableList<Long>{
        val id:Int=1234
        val db=readableDatabase


// Define a projection that specifies which columns from the database
// you will actually use after this query.
        val projection = arrayOf(BaseColumns._ID,
                                                  AdminProfile.Admin_DB.PRODUCT_ID,
                                                  AdminProfile.Admin_DB.FOOTWARE_TYPE,
                                                  AdminProfile.Admin_DB.FOOTWARE_NAME,
                                                  AdminProfile.Admin_DB.MANUFACTURE_DATE,
                                                  AdminProfile.Admin_DB.PRICE,
                                                  AdminProfile.Admin_DB.SIZE,

        )

// Filter results WHERE "title" = 'My Title'
        val selection = "${AdminProfile.Admin_DB.PRODUCT_ID} = ?"
        val selectionArgs = arrayOf(id)

// How you want the results sorted in the resulting Cursor
        val sortOrder = "${AdminProfile.Admin_DB.PRODUCT_ID} DESC"

        val cursor = db.query(
            AdminProfile.Admin_DB.TABLE_NAME,   // The table to query
            projection,             // The array of columns to return (pass null to get all)
            null,              // The columns for the WHERE clause
            null,          // The values for the WHERE clause
            null,                   // don't group the rows
            null,                   // don't filter by row groups
            sortOrder               // The sort order
        )

        val itemIds = mutableListOf<Long>()
        with(cursor) {
            while (moveToNext()) {
                val id = getLong(getColumnIndexOrThrow(AdminProfile.Admin_DB.PRODUCT_ID))
                val type = getLong(getColumnIndexOrThrow(AdminProfile.Admin_DB.FOOTWARE_TYPE))
                val name = getLong(getColumnIndexOrThrow(AdminProfile.Admin_DB.FOOTWARE_NAME))
                val date = getLong(getColumnIndexOrThrow(AdminProfile.Admin_DB.MANUFACTURE_DATE))
                val price = getLong(getColumnIndexOrThrow(AdminProfile.Admin_DB.PRICE))
                val size = getLong(getColumnIndexOrThrow(AdminProfile.Admin_DB.SIZE))
                itemIds.add(id)
                itemIds.add(type)
                itemIds.add(name)
                itemIds.add(date)
                itemIds.add(price)
                itemIds.add(size)


            }
        }
        cursor.close()
        return itemIds

    }

}

private fun SQLiteDatabase.update(
    tableName: String,
    values: ContentValues,
    selection: String,
    selectionArgs: Array<Int>
): Int {
    TODO("Not yet implemented")
}

private fun SQLiteDatabase.delete(
    tableName: String,
    selection: String,
    selectionArgs: Array<Int>
): Int {
    TODO("Not yet implemented")
}


