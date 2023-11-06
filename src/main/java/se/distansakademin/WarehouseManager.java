package se.distansakademin;

import java.lang.module.FindException;
import java.util.ArrayList;
import java.util.Optional;

public class WarehouseManager {
    private ArrayList<Warehouse> warehouses = new ArrayList<>();

    /**
     * Adds a new warehouse to the list of all warehouses
     *
     * @param warehouse The warehouse to add
     * @throws IllegalArgumentException If a warehouse with the same ID exists already
     */
    public void addNewWarehouse(Warehouse warehouse) throws IllegalArgumentException {
        boolean idExists = warehouses.stream()
                .anyMatch(w -> w.getWarehouseId() == warehouse.getWarehouseId());

        if(idExists){
            throw new IllegalArgumentException("A warehouse with that ID already exists");
        }

        warehouses.add(warehouse);
    }

    /**
     * Gets all warehouses currently available
     *
     * @return All warehouses
     */
    public ArrayList<Warehouse> getAllWarehouses() {
        return warehouses;
    }

    /**
     * Adds a product to the warehouse with the specified ID
     *
     * @param warehouseId The ID of the warehouse to add a product to
     * @param product     The product to add to the warehouse
     * @throws FindException If no warehouse exists with the ID
     */
    public void addProductToWarehouse(int warehouseId, Product product) throws FindException {
        Warehouse warehouse = getWarehouseById(warehouseId);
        warehouse.addProduct(product);
    }

    /**
     * Gets the warehouse with the specified ID
     *
     * @param id The ID of the warehouse
     * @return The warehouse
     * @throws FindException If no warehouse exists with the ID
     */
    public Warehouse getWarehouseById(int id) throws FindException {
        // Filter all warehouses on ID
        Optional<Warehouse> optionalWarehouse = warehouses.stream()
                .filter(w -> w.getWarehouseId() == id)
                .findFirst();

        // If there is a warehouse with the ID...
        if (optionalWarehouse.isPresent()) {

            // ...return it
            return optionalWarehouse.get();
        }

        // Otherwise throw an error
        throw new FindException("Warehouse with ID not found");
    }

}
