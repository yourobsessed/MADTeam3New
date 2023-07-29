package sg.edu.np.mad;

import static java.sql.Types.INTEGER;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Collections;

public class MyDBHandler extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
//    public static final String DATABASE_NAME = "FoodMenu.db";
    private static final String TABLE_FOOD = "FoodDB.db";
    public static final String FOOD_INDEX = "foodIndex";
    public static final String FOOD_NAME = "foodName";
    public static final String FOOD_LOCATION = "foodLocation";
    public static final String FOOD_PRICE = "foodPrice";
    public static final String COLUMN_FOOD_PRICE = FOOD_PRICE;
    public static final String FOOD_CALORIES = "foodCalories";
    public static final String FOOD_DESCRIPTION = "foodDescription";
    public static final String FOOD_NOODLE = "foodNoodle";
    public static final String FOOD_SOUP = "foodSoup";
    public static final String FOOD_RICE = "foodRice";
    public static final String FOOD_HALAL = "foodHalal";
    public static final String FOOD_VEGETARIAN = "foodVegetarian";
    public static final String FOOD_DESSERT = "foodDessert";
    public static final String FOOD_WISHLIST = "foodWishlist";
    public static final String TABLE_FOOD_NAME = "TABLE_FOOD";
    public static final String COLUMN_FOOD_NAME = "FOOD_NAME";
    public static final String COLUMN_FOOD_INDEX = "FOOD_INDEX";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, TABLE_FOOD, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        //String CREATE_FOOD_MENU_TABLE = "CREATE TABLE " + TABLE_FOOD + " (" + COLUMN_FOOD_INDEX + " INTEGER PRIMARY KEY AUTOINCREMENT " + COLUMN_FOOD_NAME + "TEXT," + COLUMN_FOOD_PRICE + "TEXT,)";

        String CREATE_FOOD_TABLE = "CREATE TABLE " + TABLE_FOOD +
                "(" + FOOD_INDEX + "INTEGER PRIMARY KEY AUTOINCREMENT, " + FOOD_NAME + "TEXT, " +
                "" + FOOD_LOCATION + "TEXT, " + FOOD_PRICE + "TEXT, " + FOOD_CALORIES + "TEXT, "
                + FOOD_DESCRIPTION + "TEXT, " + FOOD_NOODLE + "TEXT, " + FOOD_RICE + "TEXT, " + FOOD_SOUP + "TEXT, " + FOOD_HALAL + "TEXT, "
                + FOOD_VEGETARIAN + "TEXT, " + FOOD_DESSERT + "TEXT, " + FOOD_WISHLIST + "TEXT )";
        db.execSQL(CREATE_FOOD_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_FOOD );
        onCreate(db);
    }

    public void addFood (Food food){
        ContentValues values = new ContentValues();
        //values.put(FOOD_INDEX, food.getFoodIndex());
        values.put(FOOD_NAME, food.getFoodName());
        values.put(FOOD_LOCATION, food.getLocation());
        values.put(FOOD_PRICE, food.getPrice());
        values.put(FOOD_CALORIES, food.getCalories());
        values.put(FOOD_DESCRIPTION, food.getDescription());
        values.put(FOOD_NOODLE, food.getNoodle());
        values.put(FOOD_RICE, food.getRice());
        values.put(FOOD_SOUP, food.getSoup());
        values.put(FOOD_HALAL, food.getHalal());
        values.put(FOOD_VEGETARIAN, food.getVegetarian());
        values.put(FOOD_DESSERT, food.getDessert());
        values.put(FOOD_WISHLIST, food.getAddedWishlist());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_FOOD, null, values);
        Log.i("CREATED TABLE", "INSERTED/CREATED USER" + values.toString());
        db.close();

    }

    public Food findFood(int foodIndex){
        String query = "SELECT * FROM " + TABLE_FOOD + "WHERE" + FOOD_INDEX + "=\"" + foodIndex + "\"";
        Log.i("checking query", query);

        Food queryResult = new Food();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()){
            queryResult.setFoodIndex(Integer.parseInt(cursor.getString(0)));
            queryResult.setFoodName(cursor.getString(1));
            queryResult.setLocation(cursor.getString(2));
            queryResult.setPrice(Double.parseDouble(cursor.getString(3)));
            queryResult.setDescription(cursor.getString(4));
            queryResult.setCalories(Integer.parseInt(cursor.getString(5)));
            queryResult.setNoodle(Boolean.parseBoolean(cursor.getString(6)));
            queryResult.setRice(Boolean.parseBoolean(cursor.getString(7)));
            queryResult.setSoup(Boolean.parseBoolean(cursor.getString(8)));
            queryResult.setHalal(Boolean.parseBoolean(cursor.getString(9)));
            queryResult.setVegetarian(Boolean.parseBoolean(cursor.getString(10)));
            queryResult.setDessert(Boolean.parseBoolean(cursor.getString(11)));
            queryResult.setAddedWishlist(Boolean.parseBoolean(cursor.getString(12)));

            cursor.close();
        }
        else{
            queryResult = null;
        }

        //db.close();
        return queryResult;
    }


}
