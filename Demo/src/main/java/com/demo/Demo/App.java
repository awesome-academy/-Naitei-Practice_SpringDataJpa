package com.demo.Demo;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.demo.Demo.config.AppConfig;
import com.demo.Demo.entity.*;
import com.demo.Demo.service.ProductService;
import com.demo.Demo.service.SupplierService;

public class App 
{
    public static void main( String[] args )
    {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        ProductService productService = context.getBean(ProductService.class);
        SupplierService supplierService = context.getBean(SupplierService.class);
        productService.deleteAll();
        supplierService.deleteAll();


        Product butchi = new Product("But chi", 12.50);
        Product bubi = new Product("bu bi",13.00);
        Product vo = new Product("vo",20.50);
        Product sach = new Product("sach",30.00); 

        Supplier supplierA = new Supplier("Supplier A");
        Supplier supplierB = new Supplier("Supplier B");
        
        supplierA.addProduct(sach);
        supplierA.addProduct(vo);
        supplierB.addProduct(bubi);
        supplierB.addProduct(butchi);
        
        supplierService.save(supplierA);
        supplierService.save(supplierB);
        
        
        // Ds san pham sau khi khoi tao
        System.out.println("danh sach san pham hien tai:");
        // Sau khi them - ReadOnly
        List<Product> listsp = productService.findAll();
        for(Product p : listsp) {
        	System.out.println(p.toString());
        }  
        
        Long supplierAId = supplierA.getId();
        
        // DS sp cua Supplier A co gia tu 10 -> 20.90
        List<Product> listspa = productService.findByPriceAndSupplier(supplierAId, 10.00, 20.90);
        System.out.println("DS sp cua Supplier A co gia tu 10 -> 20.90");
        for(Product p: listspa) {
        	System.out.println(p.toString());
        }
        
        // Xoa sp
        Product p = supplierA.getProducts().get(0);
        supplierA.removeProduct(p);
        supplierService.save(supplierA);
        
        //DS sp sau khi xoa
        System.out.println("danh sach san pham hien tai:");
        listsp = productService.findAll();
        for(Product sp : listsp) {
        	System.out.println(sp.toString());
        }
        
        // Xoa cac sp cua supplier A
        
        productService.deleteInBulkBySupplierId(supplierAId);
        System.out.println("danh sach san pham hien tai:");
        listsp = productService.findAll();
        for(Product sp : listsp) {
        	System.out.println(sp.toString());
        }        
        
        context.close();
    }
}
