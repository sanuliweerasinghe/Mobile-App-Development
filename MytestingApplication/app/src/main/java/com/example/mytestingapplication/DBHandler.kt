package com.example.mytestingapplication

import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import com.example.mytestingapplication.DBHandler
import android.database.sqlite.SQLiteDatabase
import android.content.ContentValues
import android.database.Cursor

class DBHandler(context: Context?) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object {
        // creating a constant variables for our database below variable is for our database name.
        private const val DB_NAME = "ServiceStationDB"

        // below int is our database version
        private const val DB_VERSION = 1

        // variables for ServiceTable.
        private const val TABLE_NAME1 = "ServiceTable"
        private const val ID_COL1 = "ServiceID"
        private const val Date1 = "Date"
        private const val Name1 = "OwnerName"
        private const val No1 = "VehicleNo"
        private const val Brand1 = "VehicleBrand"
        private const val Service1 = "ElectricalSystem"
        private const val Service2 = "AC"
        private const val Service3 = "Suspension"
        private const val Service4 = "OilFiltre"
        private const val Service5 = "Radiator"
        private const val Service6 = "WheelBearing"

        // variables for RepairTable
        private const val TABLE_NAME2 = "RepairTable"
        private const val ID_COL2 = "RepairID"
        private const val Date2 = "Date"
        private const val Name2 = "OwnerName"
        private const val No2 = "VehicleNo"
        private const val Brand2 = "VehicleBrand"
        private const val Repair1 = "IgnitionSystem"
        private const val Repair2 = "Tyre"
        private const val Repair3 = "SparkPlug"
        private const val Repair4 = "Brakes"
        private const val Repair5 = "BodyPainting"
        private const val Repair6 = "Light"

        // variables for StockTable
        private const val TABLE_NAME3 = "StockTable"
        private const val COL1 = "PartID"
        private const val COL2 = "PartName"
        private const val COL3 = "AvailableNo"

        // variables for ReminderTable
        private const val TABLE_NAME4 = "ReminderTable"
        private const val TITLE = "Title"
        private const val DATE = "ReminderDate"
        private const val PART_NAME = "PartName"
        private const val AMOUNT = "PartAmount"
    }

    // creating a constructor for our database handler.
    init {
        // in order to create the database and the tables when run for the first time.
        val db = this.writableDatabase
    }

    // below method is for creating a database by running a sqlite query
    override fun onCreate(db: SQLiteDatabase) {
        // creating an sqlite query for ServiceTable by setting our column name along with their data types.
        db.execSQL(
            "CREATE TABLE " + TABLE_NAME1 + " ("
                    + ID_COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + Date1 + " TEXT,"
                    + Name1 + " TEXT,"
                    + No1 + " TEXT,"
                    + Brand1 + " TEXT,"
                    + Service1 + " TEXT,"
                    + Service2 + " TEXT,"
                    + Service3 + " TEXT,"
                    + Service4 + " TEXT,"
                    + Service5 + " TEXT,"
                    + Service6 + " TEXT)"
        )

        // creating an sqlite query for RepairTable
        db.execSQL(
            "CREATE TABLE " + TABLE_NAME2 + " ("
                    + ID_COL2 + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + Date2 + " TEXT,"
                    + Name2 + " TEXT,"
                    + No2 + " TEXT,"
                    + Brand2 + " TEXT,"
                    + Repair1 + " TEXT,"
                    + Repair2 + " TEXT,"
                    + Repair3 + " TEXT,"
                    + Repair4 + " TEXT,"
                    + Repair5 + " TEXT,"
                    + Repair6 + " TEXT)"
        )

        // creating an sqlite query for StockTable
        db.execSQL(
            "CREATE TABLE " + TABLE_NAME3 + " ("
                    + COL1 + " TEXT,"
                    + COL2 + " TEXT,"
                    + COL3 + " INTEGER )"
        )

        // creating an sqlite query for ReminderTable
        db.execSQL(
            "CREATE TABLE " + TABLE_NAME4 + " ("
                    + TITLE + " TEXT,"
                    + DATE + " TEXT,"
                    + PART_NAME + " TEXT,"
                    + AMOUNT + " TEXT)"
        )
    }

    // this method is to add new Service record to the service table
    fun addService(
        date: String?,
        ownerName: String?,
        vehicleNo: String?,
        vehicleBrand: String?,
        electricSystem: String?,
        airCondition: String?,
        suspension: String?,
        oilFiltre: String?,
        radiator: String?,
        wheelBearing: String?
    ): Long {
        //on below line we are creating a variable for our sqlite database and calling writable method as we are writing data in our database.
        val db = this.writableDatabase
        val values = ContentValues() // on below line we are creating a variable for content values.

        // on below line we are passing all values along with its key and value pair.
        values.put(Date1, date)
        values.put(Name1, ownerName)
        values.put(No1, vehicleNo)
        values.put(Brand1, vehicleBrand)
        values.put(Service1, electricSystem)
        values.put(Service2, airCondition)
        values.put(Service3, suspension)
        values.put(Service4, oilFiltre)
        values.put(Service5, radiator)
        values.put(Service6, wheelBearing)
        val status = db.insert(TABLE_NAME1, null, values) // // inserting the row to ServiceTable.
        // if successfully inserted, it will return the row value or else -1
        db.close() // at last we are closing our database after adding database.
        return status
    }

    // this method is to add new Repair record to the Repair table.
    fun addRepair(
        date: String?,
        ownerName: String?,
        vehicleNo: String?,
        vehicleBrand: String?,
        ignitionSystem: String?,
        tyre: String?,
        sparkPlug: String?,
        brakes: String?,
        bodyPainting: String?,
        light: String?
    ): Long {
        // creating a variable for our sqlite database and calling writable method as we are writing data in our database.
        val db = this.writableDatabase
        val values = ContentValues() // creating a variable for content values.

        // on below line we are passing all values along with its key and value pair.
        values.put(Date1, date)
        values.put(Name1, ownerName)
        values.put(No1, vehicleNo)
        values.put(Brand1, vehicleBrand)
        values.put(Repair1, ignitionSystem)
        values.put(Repair2, tyre)
        values.put(Repair3, sparkPlug)
        values.put(Repair4, brakes)
        values.put(Repair5, bodyPainting)
        values.put(Repair6, light)
        val status = db.insert(TABLE_NAME2, null, values) // inserting the row to RepairTable.
        // if successfully inserted, it will return a positive value or else -1
        db.close() // at last we are closing our database after adding database.
        return status
    }

    // this method is to add new Reminder to the reminder table.
    fun addReminder(title: String?, date: String?, partName: String?, partNo: String?): Long {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(TITLE, title)
        values.put(DATE, date)
        values.put(PART_NAME, partName)
        values.put(AMOUNT, partNo)
        val status = db.insert(TABLE_NAME4, null, values)
        db.close()
        return status
    }

    fun insertStockTablePart1() {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL1, "PA001")
        values.put(COL2, "Air Filtres")
        values.put(COL3, 56)
        db.insert(TABLE_NAME3, null, values)
        db.close()
    }

    fun insertStockTablePart2() {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL1, "PA002")
        values.put(COL2, "Brake Disks")
        values.put(COL3, 102)
        db.insert(TABLE_NAME3, null, values)
        db.close()
    }

    fun insertStockTablePart3() {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL1, "PA003")
        values.put(COL2, "Fuel Caps")
        values.put(COL3, 87)
        db.insert(TABLE_NAME3, null, values)
        db.close()
    }

    fun insertStockTablePart4() {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL1, "PA004")
        values.put(COL2, "Oil Filtres")
        values.put(COL3, 48)
        db.insert(TABLE_NAME3, null, values)
        db.close()
    }

    fun insertStockTablePart5() {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL1, "PA005")
        values.put(COL2, "Spark Plugs")
        values.put(COL3, 61)
        db.insert(TABLE_NAME3, null, values)
        db.close()
    }

    fun insertStockTablePart6() {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL1, "PA006")
        values.put(COL2, "Shock Absorbers")
        values.put(COL3, 94)
        db.insert(TABLE_NAME3, null, values)
        db.close()
    }

    fun insertStockTablePart7() {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL1, "PA007")
        values.put(COL2, "Wheel Bearings")
        values.put(COL3, 33)
        db.insert(TABLE_NAME3, null, values)
        db.close()
    }

    // this method is to update (add) new spare parts to the stock table.
    fun addStock(partName: String, partNo: Int): Long {
        val db = this.writableDatabase
        val values = ContentValues()
        val getAvailableAmountQuery =
            "SELECT AvailableNo FROM StockTable WHERE PartName =$partName"
        val c = db.rawQuery(getAvailableAmountQuery, null)
        val availableAmount = c.toString().toInt()
        val newAmount = availableAmount + partNo
        values.put(COL3, newAmount)
        val status = db.update(
            TABLE_NAME3,
            values,
            " PartName = ?",
            arrayOf(partName)
        ).toLong()
        db.close()
        return status
    }

    // this method is to update (remove) spare parts from the stock table.
    fun removeStock(partName: String, partNo: Int): Long {
        val db = this.writableDatabase
        val values = ContentValues()
        val getAvailableAmountQuery =
            "SELECT AvailableNo FROM StockTable WHERE PartName =$partName"
        val c = db.rawQuery(getAvailableAmountQuery, null)
        val availableAmount = c.toString().toInt()
        val newAmount = availableAmount - partNo
        values.put(COL3, newAmount)
        val status = db.update(
            TABLE_NAME3,
            values,
            " PartName = ?",
            arrayOf(partName)
        ).toLong()
        db.close()
        return status
    }

    fun checkForStockShortages(): Cursor {
        val db = this.writableDatabase
        return db.rawQuery("select * from $TABLE_NAME3 where AvailableNo < 25 ", null)
    }

    //method for deleting reminders from the recycler view
    fun deleteReminder(title: String) {
        val db = this.writableDatabase
        val whereClause = "Title=?"
        val whereArgs = arrayOf(title)
        db.delete("ReminderTable", whereClause, whereArgs)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1)
        onCreate(db)
    }


}