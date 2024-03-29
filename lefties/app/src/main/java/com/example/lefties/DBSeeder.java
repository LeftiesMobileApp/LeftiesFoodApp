package com.example.lefties;

public class DBSeeder {
    DBHelper dbh;
    long r1,r2,r3;
    long c1,c2, c3;
    long f1,f2,f3,f4,f5,f6,f7, f8, f9, f10;
    long o1,o2,o3, o4;

    public DBSeeder(DBHelper dbh){
        this.dbh = dbh;
    }

    public void seedTables(){
        seedAccounts();
        seedFood();
        seedOrders();
    }

    public void seedAccounts(){
        // RESTAURANTS
        r1 = dbh.addAccount(
                "Dragon City Garden",
                "Restaurant",
                "dragon@gmail.com",
                "pass123",
                "0987 654 321",
                "123 Dragon St, Vancouver",
                "Vancouver"
        );
        dbh.addRestaurant("CHINESE", r1 );
        r2 = dbh.addAccount(
                "Golden Star",
                "Restaurant",
                "golden@gmail.com",
                "pass123",
                "0987 654 321",
                "123 Golden St, Burnaby",
                "Burnaby"
        );

        dbh.addRestaurant("CHINESE", r2 );
        r3 = dbh.addAccount(
                "Biryani City",
                "Restaurant",
                "biryani@gmail.com",
                "pass123",
                "0987 654 321",
                "123 Biryani St, Surrey",

                "Surrey"
        );
        dbh.addRestaurant("INDIAN", r3 );
        // CUSTOMERS
        c1 = dbh.addAccount(
                "John Smith",
                "Customer",
                "john@email.com",
                "pass123",
                "0987 654 321",
                "123 Everywhere St, Burnaby",
                "Burnaby"
        );
        c2 = dbh.addAccount(
                "Jane Darling",
                "Customer",
                "jane@email.com",
                "pass123",
                "0987 654 321",
                "123 Sweetheart St, Vancouver",
                "Vancouver"
        );
        c3 = dbh.addAccount(
                "Dua Lipa",
                "Customer",
                "dua@email.com",
                "pass123",
                "0987 654 321",
                "123 Sodapop St, Surrey",
                "Surrey"
        );
    }

    public void seedFood(){

        // CHINESE 1
        f1 = dbh.addFood(r1, "Fortune Cookies", 2.0, 5.0, 16);

        // CHINESE 2
        f2 = dbh.addFood(r2, "Sweet and Sour", 4.0, 10.0, 16);
        f3 =dbh.addFood(r2, "Wanton Noodles", 4.0, 10.0, 16);
        f8 =dbh.addFood(r2, "Roast Chicken", 14.0, 20.0, 16);

        // INDIAN
        f4 = dbh.addFood(r3, "Butter Chicken", 4.0, 10.0, 16);
        f5 = dbh.addFood(r3, "Goat", 12.0, 20.0, 16);
        f6 = dbh.addFood(r3, "Paneer", 6.0, 10.0, 16);
        f7 = dbh.addFood(r3, "Dal Makhni", 4.5, 10.0, 16);

    }


    public void seedOrders(){
        // ORDER 1
        dbh.addFoodToTempCart((int)f2,c3);
        dbh.addFoodToTempCart((int)f3,c3);
        o1 = dbh.createOrder(
                c3,
                r2,
                8.0
        );
        dbh.updateCartWithOrder(o1, c3);

        // ORDER 2
        dbh.addFoodToTempCart((int)f4,c2);
        dbh.addFoodToTempCart((int)f5,c2);
        dbh.addFoodToTempCart((int)f6,c2);
        o2 = dbh.createOrder(
                c2,
                r3,
                18.0
        );
        dbh.updateCartWithOrder(o2, c2);

        //ORDER 3
        dbh.addFoodToTempCart((int)f2,c1);
        dbh.addFoodToTempCart((int)f3,c1);
        dbh.addFoodToTempCart((int)f8,c1);
        o3 = dbh.createOrder(
                c2,
                r3,
                8.0
        );
        dbh.updateCartWithOrder(o3, c1);

        dbh.addFoodToTempCart((int)f3,c1);
        dbh.addFoodToTempCart((int)f8,c1);
        dbh.addFoodToTempCart((int)f2,c1);

        o4 = dbh.createOrder(
                c1,
                r2,
                22.0
        );
        dbh.updateCartWithOrder(o1, c1);

    }


}
