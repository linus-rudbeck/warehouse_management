package se.distansakademin;

import se.distansakademin.data_objects.*;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static boolean running = true;

    public static void main(String[] args) {
        WarehouseManager warehouseManager = new WarehouseManager();
        ProductManager productManager = new ProductManager();

        addInitialData(warehouseManager, productManager);

        while (running) {
            int selection = getMainMenuSelection();

            handleMenuSelection(selection, warehouseManager, productManager);
        }
    }

    /**
     * Adds a few warehouses to the warehouse manager
     *
     * @param warehouseManager The warehouse manager to add warehouses to
     */
    private static void addInitialData(WarehouseManager warehouseManager, ProductManager productManager) {
        // Add initial warehouses
        Warehouse kista = new Warehouse("Kista");
        Warehouse gothenburg = new Warehouse("Göteborg");
        Warehouse stockholm = new Warehouse("Stockholm");

        warehouseManager.addNewObject(kista);
        warehouseManager.addNewObject(gothenburg);
        warehouseManager.addNewObject(stockholm);

        Product iphone = new Product("iphone", 100, "Some darn phone");
        Product usbcCharger = new Product("Usb-c charger", 5050, "Some darn phone charger");

        productManager.addNewObject(iphone);
        productManager.addNewObject(usbcCharger);

        warehouseManager.addProductToWarehouse(kista.getId(), iphone);
        warehouseManager.addProductToWarehouse(kista.getId(), usbcCharger);
        warehouseManager.addProductToWarehouse(kista.getId(), iphone);

        warehouseManager.addProductToWarehouse(gothenburg.getId(), usbcCharger);
    }

    /**
     * Shows the main menu to the user and returns the selection
     *
     * @return The users selection
     */
    private static int getMainMenuSelection() {
        while (true) {
            try {
                System.out.print("\n\n------ MENU ------\n" +
                        "1. Show warehouses\n" +
                        "2. Add warehouse\n" +
                        "3. Add product\n" +
                        "4. Add product to warehouse\n" +
                        "5. Print everything\n" +
                        "6. Remove product from warehouse\n" +
                        "0. Exit\n" +
                        "Select: ");

                String input = scanner.nextLine();

                return Integer.parseInt(input);
            } catch (NumberFormatException exception) {
                System.out.println("Invalid input! Please enter a number");
            }
        }

    }

    private static void handleMenuSelection(int selection, WarehouseManager warehouseManager, ProductManager productManager) {
        switch (selection) {
            case 1:
                printAllWarehouses(warehouseManager);
                break;
            case 2:
                addWarehouseToManager(warehouseManager);
                break;
            case 3:
                addProductToManager(productManager);
                break;
            case 4:
                addProductToWarehouse(warehouseManager, productManager);
                break;
            case 5:
                printEverything(warehouseManager);
                break;
            case 6:
                removeProductFromWarehouse(warehouseManager, productManager);
                break;
            case 0:
                stopRunning();
                break;
            default:
                System.out.println("Invalid menu selection");
                break;
        }
    }

    private static void removeProductFromWarehouse(WarehouseManager warehouseManager, ProductManager productManager) {
        int productId = getNumberInput("Enter product ID: ");
        int warehouseId = getNumberInput("Enter warehouse ID: ");

        try {
            warehouseManager.removeProductFromWarehouse(productId, warehouseId);
        }catch (IndexOutOfBoundsException e){
            System.out.println("Invalid product ID");
        }
        catch (NoSuchElementException e){
            System.out.println("Invalid warehouse ID");
        }
    }

    private static void printEverything(WarehouseManager warehouseManager) {
        ArrayList<Warehouse> warehouses = warehouseManager.getAllObjects();

        for (var warehouse : warehouses) {
            ArrayList<Product> products = warehouse.getAllProducts();
            System.out.println(warehouse);
            printProducts(products);
            System.out.println("---");
        }
    }


    /**
     * Prints all warehouses of the warehouse manager
     *
     * @param warehouseManager The warehouse manager to print warehouses of
     */
    private static void printAllWarehouses(WarehouseManager warehouseManager) {
        var warehouses = warehouseManager.getAllObjects();

        printWarehouses(warehouses);
    }

    /**
     * Prints all warehouses in the list
     *
     * @param warehouses A list of warehouses to print
     */
    private static void printWarehouses(ArrayList<Warehouse> warehouses) {
        for (Warehouse warehouse : warehouses) {
            System.out.println(warehouse);
        }
    }

    private static void addWarehouseToManager(WarehouseManager warehouseManager) {
        Warehouse newWarehouse = getWarehouseFromUserInput();

        warehouseManager.addNewObject(newWarehouse);

        System.out.println("Warehouse added successfully");
    }

    private static Warehouse getWarehouseFromUserInput() {
        System.out.print("\nInput location: ");
        String location = scanner.nextLine();

        return new Warehouse(location);
    }

    private static void addProductToManager(ProductManager productManager) {
        Product product = getProductFromUserInput();

        productManager.addNewObject(product);
    }

    private static Product getProductFromUserInput() {
        // name, price, description
        System.out.print("\nEnter name: ");
        String name = scanner.nextLine();

        int price = getNumberInput("Enter price: ");

        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        return new Product(name, price, description);
    }

    private static void addProductToWarehouse(WarehouseManager warehouseManager, ProductManager productManager) {
        System.out.println("Select a warehouse");
        printAllWarehouses(warehouseManager);
        int warehouseId = getNumberInput("Enter ID: ");

        System.out.println("Select a product");
        printAllProducts(productManager);
        int productId = getNumberInput("Enter ID: ");

        Product product = productManager.getObjectById(productId);
        warehouseManager.addProductToWarehouse(warehouseId, product);
    }

    private static void printAllProducts(ProductManager productManager) {
        ArrayList<Product> products = productManager.getAllObjects();

        printProducts(products);
    }

    private static void printProducts(ArrayList<Product> products) {
        for (var product: products) {
            System.out.println(product);
        }
    }

    private static int getNumberInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input");
            }
        }
    }

    private static void stopRunning() {
        running = false;
        System.out.println("Program will stop now.\n" +
                "Have a great day!");
    }
}