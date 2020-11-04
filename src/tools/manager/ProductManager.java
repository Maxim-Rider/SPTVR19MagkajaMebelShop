package tools.manager;



import entity.Product;
import java.util.List;
import java.util.Scanner;
import tools.savers.SaveToFile;

public class ProductManager {
    
    Scanner scan = new Scanner(System.in);
    
    public Product createProduct() {       
        String name;
        double price;
        
        while (true) {
            System.out.println("Введите название: ");
            name = scan.nextLine();
            if (name.length() > 0 && name.length() < 41 && !name.matches(" (.*)")) {
                break;
            } else {
                System.out.println("Название не правильно!");
            }
        }
        
        while (true) {
            System.out.println("Введите цену: ");
            price = scan.nextDouble();
            if (price >= 0 && price < 10000 && (price % 1) == 0) {
                break;
            } else {
                System.out.println("Цена не правильна!");
            }
        }
        Product prod = new Product(name, (int)(price));
        return prod;
    }
    
    public void addProductToList(Product product, List<Product> listProducts) {
        listProducts.add(product);
        SaveToFile saveToFile = new SaveToFile();
        saveToFile.saveToFile(listProducts, "listProducts");
    }
    
    public void showList(List<Product> listProducts) {
        System.out.println("Выберите товар: ");
        for (int i = 0; i < listProducts.size(); i++) {
            System.out.println(i + ". " + (String)(listProducts.get(i).toString()));
        }
    }
}