package com.example.lefties;

public class DBSeeder {
    DBHelper dbh;
    long r1,r2,r3;
    long c1,c2;

    public DBSeeder(DBHelper dbh){
        this.dbh = dbh;
    }

    public void seedTables(){
        seedAccounts();
        seedFood();
    }

    public void seedAccounts(){
        // RESTAURANTS
        r1 = dbh.addAccount(
                "Dragon City Garden",
                "Restaurant",
                "dragon@gmail.com",
                "pass123",
                "0987 654 321",
                "123 Dragon St, Surrey",
                "Surrey"
        );
        dbh.addRestaurant("CHINESE", r1 );
        r2 = dbh.addAccount(
                "Golden Star",
                "Restaurant",
                "golden@gmail.com",
                "pass123",
                "0987 654 321",
                "123 Golden St, Surrey",
                "Surrey"
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
        dbh.addAccount(
                "John Smith",
                "Customer",
                "john@email.com",
                "pass123",
                "0987 654 321",
                "123 Dragon St, Surrey",
                "Surrey"
        );
        dbh.addAccount(
                "Jane Darling",
                "Customer",
                "jane@email.com",
                "pass123",
                "0987 654 321",
                "123 Dragon St, Surrey",
                "Surrey"
        );
    }

    public void seedFood(){

        // CHINESE 1
        dbh.addFood(r1, "Fortune Cookies", 2.0, 5.0, 16);

        // CHINESE 2
        dbh.addFood(r2, "Sweet and Sour", 4.0, 10.0, 16);
        dbh.addFood(r2, "Wanton Noodles", 4.0, 10.0, 16);

        // INDIAN
        dbh.addFood(r3, "Butter Chicken", 4.0, 10.0, 16);
        dbh.addFood(r3, "Goat", 4.0, 10.0, 16);
        dbh.addFood(r3, "Paneer", 4.0, 10.0, 16);
        dbh.addFood(r3, "Dal Makhni", 4.0, 10.0, 16);

    }

//    public void seedFoodTable(){
//        addFood(1, "Tandoori Chicken", 30.0, 25.0, 3);
//        addFood(2, "Bread", 4.0, 10.0, 16);
//        addFood(3, "Iced Coffee", 3.0, 8.0, 160);
//        addFood(4, "Beef Stir-fry", 30.0, 25.0, 3);
//        addFood(2, "Burito", 20.0, 25.0, 4);
//        addFood(3, "Cookies", 4.0, 10.0, 16);
//        addFood(3, "Brownie Fudge", 4.0, 10.0, 16);
//        addFood(4, "Kimchi", 4.0, 10.0, 16);
//        addFood(4, "Manchurian", 4.0, 10.0, 16);
//
//    }

}
