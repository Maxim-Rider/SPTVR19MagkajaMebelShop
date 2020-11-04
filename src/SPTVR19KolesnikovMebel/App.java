package SPTVR19KolesnikovMebel;

import entity.Person;
import entity.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import tools.manager.PersonManager;
import tools.manager.ProductManager;
import tools.savers.SaveToFile;


class App {
    
    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";
    
    List<Product> listProducts = new ArrayList<>();
    ProductManager productManager = new ProductManager(); 
    
    List<Person> listPersons = new ArrayList<>();
    PersonManager personManager = new PersonManager();  
    
    
    public App() {
        SaveToFile saveToFile = new SaveToFile();
        this.listProducts = saveToFile.loadFromFile("listProducts");
        this.listPersons = saveToFile.loadFromFile("listPersons");
    }     
    boolean work = true;
    
    Scanner scan = new Scanner(System.in);
    
    public void run() {
        
        System.out.println("✖-- ==="+ BLUE +" ۩ Магазин мягкой мебели ۩"+ RESET +" === --✖");
        boolean repeat = true;
        do{
            
            System.out.println("      ┏━━━━━━Задачи:━━━━━━┓");
            System.out.println("       ╬═══════════════╬");
            System.out.println("       ║ 0. Выход из программы  ║");
            System.out.println("       ║ 1. Добавить товар      ║");
            System.out.println("       ║ 2. Список товаров      ║");
            System.out.println("       ║ 3. Добавить покупателя ║");
            System.out.println("       ║ 4. Список покупателей  ║");
            System.out.println("       ║ 5. Купить товар        ║");
            System.out.println("       ╬═══════════════╬");
            System.out.println("Выберите задачу: ");
            
            int task = scan.nextInt();
            
            switch (task) {
                default:
                case 0:                   
                    System.out.println("------ ======= Выход из программы ======= ------");
                    repeat = false;
                    break;
                case 1:     
                    Product prod = productManager.createProduct();
                    productManager.addProductToList(prod, listProducts);
                    break;
                case 2:
                    if (listProducts.size() == 0) {
                    } else {
                        productManager.showList(listProducts);
                    }   
                    break;
                case 3:
                    Person pers = personManager.createPerson();
                    personManager.addPersonToList(pers, listPersons);
                    break;   
                case 4:
                    if (listPersons.size() == 0) {
                    } else {
                        personManager.showList(listPersons);
                    }  
                    break;      
                case 5:  
                    if (listPersons.size() == 0) {
                    } else if (listProducts.size() == 0) {
                    } else {
                        personManager.showList(listPersons);
                        int choosed_person = scan.nextInt();
                        
                        if (choosed_person >= 0 && choosed_person < listPersons.size()) {
                            productManager.showList(listProducts);
                            int choosed_product = scan.nextInt();
                            
                            if (choosed_product >= 0 && choosed_product < listProducts.size()) {
                                
                                if (listPersons.get(choosed_person).getMoney() >= listProducts.get(choosed_product).getPrice()) {
                                    listPersons.get(choosed_person).setMoney(listPersons.get(choosed_person).getMoney() - listProducts.get(choosed_product).getPrice());
                                    listPersons.get(choosed_person).getListProducts().add(listProducts.get(choosed_product));
                                    listProducts.remove(choosed_product);
                                    SaveToFile saveToFile = new SaveToFile();
                                    saveToFile.saveToFile(listPersons, "listPersons");
                                    saveToFile.saveToFile(listProducts, "listProducts");
                                } else {
                                    System.out.println("У Вас недостаточно денег для покупки. Попробуйте снова.");
                                }
                            
                            } else {
                            }                        
                        } else {
                        }                        
                    }  
                    break;  
                                      
            }
        }while(repeat);
    } 
}
