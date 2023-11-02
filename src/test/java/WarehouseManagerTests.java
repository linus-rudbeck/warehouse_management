import org.junit.Test;
import se.distansakademin.Product;
import se.distansakademin.Warehouse;
import se.distansakademin.WarehouseManager;

public class WarehouseManagerTests {
    @Test
    public void testAddNewWarehouse(){
        var warehouseManager = new WarehouseManager();
        var warehouse = new Warehouse(1, "Kista");

        warehouseManager.addNewWarehouse(warehouse);

        assert warehouseManager.getAllWarehouses().size() == 1;
    }

    @Test
    public void testAddProductToWarehouse(){
        var warehouseManager = new WarehouseManager();
        var warehouse = new Warehouse(1, "Kista");
        var product = new Product(1, "iphone", 10, "An exlusive phone");

        warehouseManager.addNewWarehouse(warehouse);
        warehouseManager.addProductToWarehouse(1, product);

        warehouse = warehouseManager.getWarehouseById(1);

        assert warehouse.getAllProducts().size() == 1;
    }
}
