import org.junit.Test;
import se.distansakademin.DataObjectManager;
import se.distansakademin.data_objects.Product;
import se.distansakademin.data_objects.Warehouse;
import org.junit.Assert;

import java.lang.runtime.ObjectMethods;

public class DataObjectManagerTests {
    @Test
    public void testAddNewObject(){
        Warehouse warehouse = new Warehouse("Kista");
        DataObjectManager<Warehouse> warehouseDataObjectManager = new DataObjectManager<>();

        warehouseDataObjectManager.addNewObject(warehouse);

        Assert.assertEquals(1, warehouseDataObjectManager.getAllObjects().size());
    }

    @Test
    public void testAddObjectsWithIdNoId(){
        DataObjectManager<Product> productDataObjectManager = new DataObjectManager<>();
        Product product1 = new Product(200, "iphone", 100, "An exclusive phone");
        Product product2 = new Product("usb-c charger", 1000, "Charge your phone and / or computer");

        productDataObjectManager.addNewObject(product1);
        productDataObjectManager.addNewObject(product2);

        Assert.assertEquals(201, product2.getId());
    }

    @Test
    public void testGetObjectById(){
        DataObjectManager<Product> productDataObjectManager = new DataObjectManager<>();
        Product product1 = new Product(200, "iphone", 100, "An exclusive phone");
        Product product2 = new Product("usb-c charger", 1000, "Charge your phone and / or computer");

        productDataObjectManager.addNewObject(product1);
        productDataObjectManager.addNewObject(product2);

        Assert.assertEquals("iphone", productDataObjectManager.getObjectById(200).getProductName());
        Assert.assertEquals("usb-c charger", productDataObjectManager.getObjectById(201).getProductName());
    }
}
