package tools.manager;



import entity.Person;
import java.util.List;
import java.util.Scanner;
import tools.savers.SaveToFile;

public class PersonManager {

    Scanner scan = new Scanner(System.in);
    
    public Person createPerson() {       
        
        String name;
        String surname;
        String phone;
        double money;
        
        while (true) {
            System.out.println("Введите имя: ");
            name = scan.nextLine();
            if (name.length() > 0 && name.length() < 41 && !name.matches(" (.*)")) {
                break;
            } else {
                System.out.println("Имя написано не правильно!");
            }
        }

        while (true) {
            System.out.println("Введите фимилию: ");
            surname = scan.nextLine();
            if (surname.length() > 0 && surname.length() < 41 && !surname.matches(" (.*)")) {
                break;
            } else {
                System.out.println("Фамилия написана не правильно: ");
            }
        }
                
        while (true) {
            System.out.println("Введите номер: ");
            phone = scan.nextLine();
            if (phone.length() > 2 && phone.length() < 21 && !phone.matches(" (.*)")) {
                break;
            } else {
                System.out.println("Номер написан не правильно: ");
            }
        }
        
        while (true) {
            System.out.println("Введите сумму: ");
            money = scan.nextDouble();
            if (money >= 0 && money < 10000 && (money % 1) == 0) {
                break;
            } else {
                System.out.println("Сумма написана не правильно: ");
            }
        }
        
        Person pers = new Person(name, surname, phone, (int)(money));
        return pers;
    }
    public void addPersonToList(Person person, List<Person> listPersons) {
        listPersons.add(person);
        SaveToFile saveToFile = new SaveToFile();
        saveToFile.saveToFile(listPersons, "listPersons");
    }
    
    public void showList(List<Person> listPersons) {
        System.out.println("Выберите попупателя: ");
        for (int i = 0; i < listPersons.size(); i++) {
            System.out.println(i + ". " + (String)(listPersons.get(i).toString()));
        }
    }    
}
