package se.distansakademin;

public class Main {
    public static void main(String[] args) {
        var wh = new Warehouse(1, "Kista");

        var p1 = new Product(1, "iphone", 10, "An exlusive phone");
        var p2 = new Product(2, "juice", 5, "Something to drink");
        var p3 = new Product(1, "iphone", 10, "An exlusive phone");

        wh.addProduct(p1);
        wh.addProduct(p2);
        wh.addProduct(p3);

        wh.listAllProducts();

        System.out.println("---");

        wh.removeProductById(1);

        wh.listAllProducts();

    }
}