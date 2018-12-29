/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package joesautos;

import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ryan
 */
public class JoesAUTOS {
    
    /**
     * This is the main entry point into the application, the employee must choose either login or exit
     * @param args the command line arguments
     * @throws joesautos.InvalidOptionException
     */
    public static void main(String[] args) throws InvalidOptionException {
        // TODO code application logic here
        Scanner keyboard = new Scanner(System.in);
        Model model = Model.getInstance();
        
        int opt = 0;
        
        do {
            System.out.println("\n******** Joe's AUTOS ********");
            System.out.println("1. Log in");
            System.out.println("2. Exit");
            System.out.println("\nEnter option : ");
            
            try {
                String line = keyboard.nextLine();
                opt = Integer.parseInt(line);
                
                switch(opt) {
                    case 1: {
                        employeeLogin(keyboard, model);
                        break;
                    }
                    
                    case 2: {
                        break;
                    }
                    
                    default: {
                        throw new InvalidOptionException();
                    }
                }
            }
            catch (NumberFormatException | InvalidOptionException ex) {
                System.out.println("\nInvalid option, try again.");
            }
        }
        while (opt !=2);
        System.out.println("\nGoodbye!");
        System.exit(0);
    }
    
    /**
     * Employee enters their employee ID to simulate logging into the system. There are three main roles
     * Admin, Salesman and Sales Manager. The employee is presented with a different set of options depending
     * on their role.
     * @param keyboard
     * @param model
     * @throws InvalidOptionException
     */
    private static void employeeLogin(Scanner keyboard, Model model) throws InvalidOptionException {
        int id;
        
        String role;
        
        Employee e;
        Admin a;
        Salesman s;
        SalesManager sm;
        
        do {
            try {
                System.out.println("\nEnter Employee ID : ");
                String line = keyboard.nextLine();
                id = Integer.parseInt(line);
                
                e = getEmployeeById(model, id);
                
                role = e.getRole();
                
                System.out.println("Enter password : ********");
                
                switch(role.toLowerCase()) {
                    case "admin": {
                        a = new Admin(role, e.getCommissionRate(), e.getFirstName(), e.getLastName(), e.getEmail(),
                                e.getTelephone(), e.getAddress(), e.getTown(), e.getCity(), e.getCountry(), e.getPostCode());
                        a.setId(id);
                        adminMenu(keyboard, model, a);
                        break;
                    }
                    case "salesman": {
                        s = new Salesman(role, e.getCommissionRate(), e.getFirstName(), e.getLastName(), e.getEmail(),
                                e.getTelephone(), e.getAddress(), e.getTown(), e.getCity(), e.getCountry(), e.getPostCode());
                        s.setId(id);
                        salesmanMenu(keyboard, model, s);
                        break;
                    }
                    case "sales manager": {
                        sm = new SalesManager(role, e.getCommissionRate(), e.getFirstName(), e.getLastName(), e.getEmail(),
                                e.getTelephone(), e.getAddress(), e.getTown(), e.getCity(), e.getCountry(), e.getPostCode());
                        sm.setId(id);
                        salesManagerMenu(keyboard, model, sm);
                        break;
                    }
                }
            }
            catch (NumberFormatException | NullPointerException ex) {
                System.out.println("\nEmployee ID not recognised.");
            }
        }
        while(true);
    }
    
    /**
     * Admin menu. Admins can manage employees; they may view all, add, edit or delete employees.
     * @param keyboard
     * @param model
     * @param a
     * @throws InvalidOptionException
     */
    private static void adminMenu(Scanner keyboard, Model model, Admin a) throws InvalidOptionException {
        int opt = 0;
        
        do {
            
            System.out.println("\n******** ADMIN MENU ********");
            System.out.println("1. Manage employees");
            System.out.println("2. Log out");
            System.out.println("\nWelcome back "+ a.getFirstName() + ", enter option : ");
            
            try {
                
                String line = keyboard.nextLine();
                opt = Integer.parseInt(line);
                
                switch(opt) {
                    case 1: {
                        manageEmployees(keyboard, model);
                        break;
                    }
                    
                    case 2: {
                        break;
                    }
                    
                    default: {
                        throw new InvalidOptionException();
                    }
                }
            }
            catch (NumberFormatException | InvalidOptionException ex) {
                System.out.println("\nInvalid option, try again.");
            }
        }
        while (opt != 2);
        System.out.println("\nGoodbye!");
        System.exit(0);
    }
    
    /**
     * Employees with Salesman role are brought here. Salesman can view all motorbikes, search motorbikes by frame
     * create sales and view their sales.
     * @param keyboard
     * @param model
     * @param s
     * @throws InvalidOptionException
     */
    private static void salesmanMenu(Scanner keyboard, Model model, Salesman s) throws InvalidOptionException {
        Motorbike m;
        
        int id, opt = 0;
        
        do {
            
            System.out.println("\n******** SALES MENU ********");
            System.out.println("1. View all motorbikes");
            System.out.println("2. Filter motorbikes by frame");
            System.out.println("3. Create new sale");
            System.out.println("4. View my sales");
            System.out.println("5. Log out");
            System.out.println("\nWelcome back "+ s.getFirstName() + ", enter option : ");
            
            try {
                
                String line = keyboard.nextLine();
                opt = Integer.parseInt(line);
                
                switch(opt) {
                    case 1: {
                        viewMotorbikes(model, "");
                        break;
                    }
                    
                    case 2: {
                        System.out.println("Enter frame to search (Chopper, Cruiser, Motorcross, Scooter or Sport) : ");
                        String frame = keyboard.nextLine();
                        viewMotorbikes(model, frame);
                        break;
                    }
                    
                    case 3: {
                        createSale(keyboard, model, s);
                        break;
                    }
                    
                    case 4: {
                        viewSales(model, "", s.getId(), false);
                        break;
                    }
                    
                    case 5: {
                        break;
                    }
                    
                    default: {
                        throw new InvalidOptionException();
                    }
                }
            }
            catch (NumberFormatException | InvalidOptionException ex) {
                System.out.println("\nInvalid option, try again.");
            }
        }
        while (opt != 5);
        System.out.println("\nGoodbye!");
        System.exit(0);
    }
    
    /**
     * Sales Manager menu, sales manager can manage motorbikes, manage salesmen and manage sales.
     * @param keyboard
     * @param model
     * @param sm
     * @throws InvalidOptionException 
     */
    private static void salesManagerMenu(Scanner keyboard, Model model, SalesManager sm) throws InvalidOptionException {
        Motorbike m;
        
        int id, opt = 0;
        
        do {
            
            System.out.println("\n******** SALES MANAGER MENU ********");
            System.out.println("1. Manage motorbikes");
            System.out.println("2. Manage salesmen");
            System.out.println("3. Manage sales");
            System.out.println("4. View total sum of sales");
            System.out.println("5. View total motorbikes sold");
            System.out.println("6. View top performing salesmen");
            System.out.println("7. Log out");
            System.out.println("\nWelcome back "+ sm.getFirstName() + ", enter option : ");
            
            try {
                
                String line = keyboard.nextLine();
                opt = Integer.parseInt(line);
                
                switch(opt) {
                    case 1: {
                        manageMotorbikes(keyboard, model);
                        break;
                    }
                    
                    case 2: {
                        manageSalesmen(keyboard, model);
                        break;
                    }
                    
                    case 3: {
                        manageSales(keyboard, model);
                        break;
                    }
                    
                    case 4: {
                        viewTotalSumSales(model, sm);
                        break;
                    }
                    
                    case 5: {
                        viewNumMotorbikesSold(model, sm);
                        break;
                    }
                    
                    case 6: {
                        viewTopSalesmen(model, sm);
                        break;
                    }
                    
                    case 7: {
                        break;
                    }
                    
                    default: {
                        throw new InvalidOptionException();
                    }
                }
            }
            catch (NumberFormatException | InvalidOptionException ex) {
                System.out.println("\nInvalid option, try again.");
            }
        }
        while (opt != 7);
        System.out.println("\nGoodbye!");
        System.exit(0);
    }
    
    /**
     * Sales manager can manage salesmen here, including adding, viewing and editing existing salesmen.
     * @param keyboard
     * @param model
     * @throws InvalidOptionException 
     */
    private static void manageSalesmen(Scanner keyboard, Model model) throws InvalidOptionException {
        int opt = 0;
        
        Employee e;
        
        do {
            
            System.out.println("\n******** MANAGE SALESMEN ********");
            System.out.println("1. Add new salesman");
            System.out.println("2. View all salesmen");
            System.out.println("3. Edit salesman");
            System.out.println("4. Main Menu");
            System.out.println("\nEnter option : ");
            
            try {
                String line = keyboard.nextLine();
                opt = Integer.parseInt(line);
                
                switch(opt){
                    case 1: {
                        // add salesman
                        e = readEmployee(keyboard, null, false, true);
                        model.addEmployee(e);
                        System.out.println("Salesman added");
                        break;
                    }
                    
                    case 2: {
                        // view all salesmen
                        viewEmployees(model, "salesman");
                        break;
                    }
                    
                    case 3: {
                        // edit salesman
                        e = readEmployee(keyboard, model, true, true);
                        model.updateEmployee(e);
                        System.out.println("\nSalesman updated");
                        break;
                    }
                    
                    case 4: {
                        break;
                    }
                    
                    default: {
                        throw new InvalidOptionException();
                    }
                }
            }
            catch (NumberFormatException | InvalidOptionException ex) {
                System.out.println("\nInvalid option, try again.");
            }
        }
        while (opt != 4);
    }
    
    /**
     * Sales manager can view all sales and filter sales by status with this method
     * @param keyboard
     * @param model
     * @throws InvalidOptionException 
     */
    private static void manageSales(Scanner keyboard, Model model) throws InvalidOptionException {
        int opt = 0;
        
        do {
            
            System.out.println("\n******** MANAGE SALES ********");
            System.out.println("1. View all sales");
            System.out.println("2. Filter sales by status");
            System.out.println("3. Main Menu");
            System.out.println("\nEnter option : ");
            
            try {
                String line = keyboard.nextLine();
                opt = Integer.parseInt(line);
                
                switch(opt){
                    case 1: {
                        // lists all sales
                        viewSales(model, "", null, true);
                        break;
                    }
                    
                    case 2: {
                        // filter sales by status
                        System.out.println("Enter status (complete or void) : ");
                        String status = keyboard.nextLine();
                        viewSales(model, status, null, true);
                        break;
                    }
                    
                    case 3: {
                        break;
                    }
                    
                    default: {
                        throw new InvalidOptionException();
                    }
                }
            }
            catch (NumberFormatException | InvalidOptionException ex) {
                System.out.println("\nInvalid option, try again.");
            }
        }
        while (opt != 3);
    }
    
    /**
     * Creates a sale model and stores it in the database, along with the employee ID and motorbike ID
     * Motorbike must be in stock for the sale to go through. We use the Saleman's public methods to
     * calculate VAT and commission earned. Void sales are stored in db too.
     * @param keyboard
     * @param model
     * @param s
     */
    private static void createSale(Scanner keyboard, Model model, Salesman s) {
        final double VAT = 0.23;
        double total, commission;
        int id, stock;
        
        String status;
        
        Motorbike m;
        Sale sale;
        
        System.out.println("Enter ID of motorbike to sell : ");
        id = keyboard.nextInt();
        keyboard.nextLine();
        m = getMotorbikeById(model, id);
        
        if(m != null){
            stock = m.getStock();
            // can't sell if the motorbike isnt in stock!
            if(stock > 0){
                // let the salesman calculate sales total incl VAT and commission
                total = s.getSaleTotal(VAT, m.getPrice());
                commission = s.getSaleCommission(m.getPrice());
                
                System.out.println("\n******** SALE CONFIRMATION ********");
                System.out.println("\nMake : " + m.getMake());
                System.out.println("Model : " + m.getModel());
                System.out.println("Engine : " + m.getEngine());
                System.out.println("Frame : " + m.getFrame());
                System.out.println("Colour : " + m.getColour());
                System.out.println("Year : " + m.getYear());
                System.out.println("Price : €" + m.getPrice());
                System.out.println("Stock : " + m.getStock());
                System.out.println("\nTotal Incl VAT @ " + VAT * 100 + "% : €" + total);
                
                System.out.println("\nSalesman : " + s.getFirstName() + " " + s.getLastName());
                System.out.println("Sale commission : €" + commission);
                
                System.out.println("\nConfirm sale [y] : ");
                String confirm = keyboard.nextLine();
                confirm = confirm.toLowerCase();
                
                if(confirm.equals("") || confirm.equals("y")){
                    status = "complete";
                    // set Motorbike -1 stock and store in db
                    stock--;
                    m.setStock(stock);
                    model.updateMotorbike(m);
                } else {
                    status = "void";
                }
                
                // create instance of sale model and store in db
                sale = new Sale(status, total, commission, s.getId(), m.getId());
                model.addSale(sale);
                
                System.out.println("Sale " + status);
                
            } else {
                System.out.println("\nMotorbike is out of stock!");
            }
        }
    }
    
    /**
     * Sales Manager has full CRUD access over all the motorbikes in the database with this method.
     * They can also filter motorbikes by frame.
     * @param keyboard
     * @param model
     * @throws InvalidOptionException
     */
    private static void manageMotorbikes(Scanner keyboard, Model model) throws InvalidOptionException {
        int opt = 0;
        
        Motorbike m;
        
        do {
            
            System.out.println("\n******** MANAGE MOTORBIKES ********");
            System.out.println("1. Add new motorbike");
            System.out.println("2. View all motorbikes");
            System.out.println("3. Filter motorbikes by frame");
            System.out.println("4. Edit motorbike");
            System.out.println("5. Delete motorbike");
            System.out.println("6. Main Menu");
            System.out.println("\nEnter option : ");
            
            try {
                String line = keyboard.nextLine();
                opt = Integer.parseInt(line);
                
                switch(opt){
                    case 1: {
                        // add motorbike
                        m = readMotorbike(keyboard, null, false);
                        model.addMotorbike(m);
                        System.out.println("Motorbike added");
                        break;
                    }
                    
                    case 2: {
                        // view all motorbikes
                        viewMotorbikes(model, "");
                        break;
                    }
                    
                    case 3: {
                        // search motorbikes by frame
                        System.out.println("Enter frame to search (Chopper, Cruiser, Motocross, Scooter or Sport) : ");
                        String frame = keyboard.nextLine();
                        viewMotorbikes(model, frame);
                        break;
                    }
                    
                    case 4: {
                        // edit motorbike
                        m = readMotorbike(keyboard, model, true);
                        model.updateMotorbike(m);
                        System.out.println("\nMotorbike updated");
                        break;
                    }
                    
                    case 5: {
                        // delete motorbike
                        System.out.println("Enter ID of motorbike to delete : ");
                        int id = keyboard.nextInt();
                        keyboard.nextLine();
                        model.deleteMotorbike(id);
                        System.out.println("\nMotorbike deleted");
                        break;
                    }
                    
                    case 6: {
                        break;
                    }
                    
                    default: {
                        throw new InvalidOptionException();
                    }
                }
            }
            catch (NumberFormatException | InvalidOptionException ex) {
                System.out.println("\nInvalid option, try again.");
            }
        }
        while (opt != 6);
    }
    
    /**
     * Admin has full CRUD access over all the employees in the database with this method.
     * They can also filter employees by role.
     * @param keyboard
     * @param model
     * @throws InvalidOptionException
     */
    private static void manageEmployees(Scanner keyboard, Model model) throws InvalidOptionException {
        int opt = 0;
        
        Employee e;
        
        do {
            
            System.out.println("\n******** MANAGE EMPLOYEES ********");
            System.out.println("1. Add new employee");
            System.out.println("2. View all employees");
            System.out.println("3. Filter employees by role");
            System.out.println("4. Edit employee");
            System.out.println("5. Delete employee");
            System.out.println("6. Main Menu");
            System.out.println("\nEnter option : ");
            
            try {
                String line = keyboard.nextLine();
                opt = Integer.parseInt(line);
                
                switch(opt){
                    case 1: {
                        // add employee
                        e = readEmployee(keyboard, null, false, false);
                        model.addEmployee(e);
                        System.out.println("Employee added");
                        break;
                    }
                    
                    case 2: {
                        // view all employees
                        viewEmployees(model, "");
                        break;
                    }
                    
                    case 3: {
                        // view all employees by role
                        System.out.println("Enter role to search (Admin, Salesman or Sales Manager) : ");
                        String role = keyboard.nextLine();
                        viewEmployees(model, role);
                        break;
                    }
                    
                    case 4: {
                        // edit employee
                        e = readEmployee(keyboard, model, true, false);
                        model.updateEmployee(e);
                        System.out.println("\nEmployee updated");
                        break;
                    }
                    
                    case 5: {
                        // delete employee
                        System.out.println("Enter ID of employee to delete : ");
                        int id = keyboard.nextInt();
                        keyboard.nextLine();
                        model.deleteEmployee(id);
                        System.out.println("\nEmployee deleted");
                        break;
                    }
                    
                    case 6: {
                        break;
                    }
                    
                    default: {
                        throw new InvalidOptionException();
                    }
                }
            }
            catch (NumberFormatException | InvalidOptionException ex) {
                System.out.println("\nInvalid option, try again.");
            }
        }
        while (opt != 6);
    }
    
    /**
     * This method is used by Admin to edit or update existing employees. It is also called by Sales Managers
     * to allow them to edit or add Salesmen 
     * @param keyboard
     * @param model
     * @param update
     * @return
     */
    private static Employee readEmployee(Scanner keyboard, Model model, boolean update, boolean isSalesman) {
        String role, firstName, lastName, email, telephone,
                address, town, city, country, postCode, text;
        
        double commissionRate;
        
        Employee e = null;
        
        int id = 0;
        
        try {
            
            // are we updating an existing employee?
            if (update) {
                // are they a salesman or new employee?
                text = ((isSalesman)) ? "Enter ID of salesman to update : " : "Enter ID of employee to update : ";
                System.out.println(text);
                id = keyboard.nextInt();
                keyboard.nextLine();
                e = getEmployeeById(model, id);
            }
            
            if(!isSalesman) {
                text = ((e==null)) ? "Enter role (Admin, Salesman or Sales Manager) : " : "Update role [" + e.getRole() + "] : ";
                System.out.println(text);
                role = keyboard.nextLine();
            } else {
                role = "salesman";
            }
            
            text = ((e==null)) ? "Enter commission : " : "Update commission [" + e.getCommissionRate() + "] : ";
            System.out.println(text);
            commissionRate = keyboard.nextDouble();
            keyboard.nextLine();
            
            text = ((e==null)) ? "Enter first name : " : "Update first name [" + e.getFirstName() + "] : ";
            System.out.println(text);
            firstName = keyboard.nextLine();
            
            text = ((e==null)) ? "Enter last name : " : "Update last name [" + e.getLastName() + "] : ";
            System.out.println(text);
            lastName = keyboard.nextLine();
            
            text = ((e==null)) ? "Enter email : " : "Update email [" + e.getEmail() + "] : ";
            System.out.println(text);
            email = keyboard.nextLine();
            
            text = ((e==null)) ? "Enter telephone : " : "Update telephone [" + e.getTelephone() + "] : ";
            System.out.println(text);
            telephone = keyboard.nextLine();
            
            text = ((e==null)) ? "Enter address : " : "Update address [" + e.getAddress() + "] : ";
            System.out.println(text);
            address = keyboard.nextLine();
            
            text = ((e==null)) ? "Enter town : " : "Update town [" + e.getTown() + "] : ";
            System.out.println(text);
            town = keyboard.nextLine();
            
            text = ((e==null)) ? "Enter city : " : "Update city [" + e.getCity() + "] : ";
            System.out.println(text);
            city = keyboard.nextLine();
            
            text = ((e==null)) ? "Enter country : " : "Update country [" + e.getCountry() + "] : ";
            System.out.println(text);
            country = keyboard.nextLine();
            
            text = ((e==null)) ? "Enter post code : " : "Update post code [" + e.getPostCode() + "] : ";
            System.out.println(text);
            postCode = keyboard.nextLine();
            
            // create an instance of one of the employee sub classes
            switch (role.toLowerCase()) {
                case "admin": {
                    Admin a
                            = new Admin(role, commissionRate, firstName, lastName, email, telephone, address, town, city, country, postCode);
                    a.setId(id);
                    return a;
                }
                
                case "salesman": {
                    Salesman s
                            = new Salesman(role, commissionRate, firstName, lastName, email, telephone, address, town, city, country, postCode);
                    s.setId(id);
                    return s;
                }
                
                case "sales manager": {
                    SalesManager s
                            = new SalesManager(role, commissionRate, firstName, lastName, email, telephone, address, town, city, country, postCode);
                    s.setId(id);
                    return s;
                }
                
                default: {
                    throw new InvalidRoleException();
                }
            }
        } catch (InputMismatchException | InvalidRoleException ex) {
            System.out.println(ex);
            System.exit(1);
        }
        
        return e;
    }
    
    /**
     * Allows Sales Managers to add, or edit and existing motorbike
     * @param keyboard
     * @param model
     * @param update
     * @return
     */
    private static Motorbike readMotorbike(Scanner keyboard, Model model, boolean update) {
        String make, _model, engine, frame, colour, text;
        
        Motorbike m = null;
        
        int id = 0, year, stock;
        double price;
        
        try {
            // are we updating an existing motorbike?
            if (update) {
                System.out.println("Enter ID of motorbike to update : ");
                id = keyboard.nextInt();
                keyboard.nextLine();
                m = getMotorbikeById(model, id);
            }
            
            text = ((m==null)) ? "Enter make : " : "Update make [" + m.getMake() + "] : ";
            System.out.println(text);
            make = keyboard.nextLine();
            
            text = ((m==null)) ? "Enter model : " : "Update model [" + m.getModel() + "] : ";
            System.out.println(text);
            _model = keyboard.nextLine();
            
            text = ((m==null)) ? "Enter engine : " : "Update engine [" + m.getEngine() + "] : ";
            System.out.println(text);
            engine = keyboard.nextLine();
            
            text = ((m==null)) ? "Enter frame (Chopper, Cruiser, Motocross, Scooter or Sport) : " : "Update frame [" + m.getFrame() + "] : ";
            System.out.println(text);
            frame = keyboard.nextLine();
            
            text = ((m==null)) ? "Enter colour : " : "Update colour [" + m.getColour() + "] : ";
            System.out.println(text);
            colour = keyboard.nextLine();
            
            text = ((m==null)) ? "Enter year : " : "Update year [" + m.getYear() + "] : ";
            System.out.println(text);
            year = keyboard.nextInt();
            keyboard.nextLine();
            
            text = ((m==null)) ? "Enter price : " : "Update price [" + m.getPrice() + "] : ";
            System.out.println(text);
            price = keyboard.nextDouble();
            keyboard.nextLine();
            
            text = ((m==null)) ? "Enter stock : " : "Update stock [" + m.getStock() + "] : ";
            System.out.println(text);
            stock = keyboard.nextInt();
            keyboard.nextLine();
            
            // create an instance of one of the motorbike sub classes
            switch (frame.toLowerCase()) {
                case "chopper": {
                    Chopper c
                            = new Chopper(make, _model, engine, frame, colour, year, price, stock);
                    c.setId(id);
                    return c;
                }
                case "crusier": {
                    Cruiser c
                            = new Cruiser(make, _model, engine, frame, colour, year, price, stock);
                    c.setId(id);
                    return c;
                }
                case "motocross": {
                    Motocross mc
                            = new Motocross(make, _model, engine, frame, colour, year, price, stock);
                    mc.setId(id);
                    return mc;
                }
                case "scooter": {
                    Scooter s
                            = new Scooter(make, _model, engine, frame, colour, year, price, stock);
                    s.setId(id);
                    return s;
                }
                case "sport": {
                    Sport s
                            = new Sport(make, _model, engine, frame, colour, year, price, stock);
                    s.setId(id);
                    return s;
                }
                default: {
                    throw new InvalidFrameException();
                }
            }
        } catch (NumberFormatException | InputMismatchException | InvalidFrameException ex) {
            System.out.println(ex);
            System.exit(1);
        }
        
        return m;
    }
    
    /**
     * Displays a listing of all the motorbikes, we can also filter motorbikes with a specific frame.
     * @param model
     * @param frame
     */
    private static void viewMotorbikes(Model model, String frame) {
        List<Motorbike> motorbikes;
        
        frame = frame.toLowerCase();
        
        if(frame.equals("")) {
            // list all motorbikes
            motorbikes = model.getMotorbikes();
        } else {
            // list by frameß
            motorbikes = model.getMotorbikesByFrame(frame);
        }
        
        // did db return any motorbikes?
        if(motorbikes.size() > 0) {
            for (Motorbike m: motorbikes) {
                System.out.println("\nID : " + m.getId());
                System.out.println("Make : " + m.getMake());
                System.out.println("Model : " + m.getModel());
                System.out.println("Engine : " + m.getEngine());
                System.out.println("Frame : " + m.getFrame());
                System.out.println("Colour : " + m.getColour());
                System.out.println("Year : " + m.getYear());
                System.out.println("Price : €" + m.getPrice());
                System.out.println("Stock : " + m.getStock());
            }
        } else {
            System.out.println("\nNo motorbikes found!");
        }
    }
    
    /**
     * Displays a listing of all employees, we can also filter employees with a specific role.
     * @param model
     * @param role
     */
    private static void viewEmployees(Model model, String role) {
        List<Employee> employees;
        
        role = role.toLowerCase();
        
        if(role.equals("")){
            // list all employees
            employees = model.getEmployees();
        } else {
            // list by role
            employees = model.getEmployeesByRole(role);
        }
        
        // did db return any employees?
        if(employees.size() > 0) {
            for (Employee e: employees) {
                // step through each employee in employees array
                System.out.println("\nID : " + e.getId());
                System.out.println("Role : " + e.getRole());
                System.out.println("Commission rate : " + e.getCommissionRate());
                System.out.println("First name : " + e.getFirstName());
                System.out.println("Last name : " + e.getLastName());
                System.out.println("Email : " + e.getEmail());
                System.out.println("Telephone : " + e.getTelephone());
                System.out.println("Address  : " + e.getAddress());
                System.out.println("Town : " + e.getTown());
                System.out.println("City : " + e.getCity());
                System.out.println("Country : " + e.getCountry());
                System.out.println("Post code : " + e.getPostCode());
            }
        } else {
            System.out.println("\nNo employees found!");
        }
    }
    
    /**
     * Prints out all the sales. They can be filtered by status, or from a specific employee.
     * @param model
     * @param status
     * @param employeeId
     * @param includeEmployee
     */
    private static void viewSales(Model model, String status, Integer employeeId, boolean includeEmployee) {
        List<Sale> sales;
        Motorbike m;
        
        if(employeeId == null && status.equals("")) {
            // list all sales
            sales = model.getSales();
        } else if(employeeId == null && !"".equals(status)) {
            // list by sales status, either complete or void
            status = status.toLowerCase();
            sales = model.getSalesByStatus(status); 
        } else {
            // do we want to include the employees details too?
            sales = model.getSalesByEmployeeId(employeeId);
        }
        
        // did db return any sales?
        if(sales.size() > 0) {
            for (Sale s: sales) {
                // step through each sale in sales array
                m = getMotorbikeById(model, s.getMotorbikeId());
                
                System.out.println("\n****************");
                System.out.println("\nSale ID : " + s.getId());
                System.out.println("Status : " + s.getStatus());
                System.out.println("Total Incl VAT : €" + s.getTotal());
                
                if(includeEmployee) {
                    Employee e = getEmployeeById(model, s.getEmployeeId());
                    
                    System.out.println("\nEmployee ID : " + e.getId());
                    System.out.println("Salesman : " + e.getFirstName() + " " + e.getLastName());
                    System.out.println("Email : " + e.getEmail());
                    System.out.println("Telephone : " + e.getTelephone());
                    System.out.println("Commission @ " + e.getCommissionRate() * 1 + "% : €" + s.getCommission());
                }
                
                System.out.println("\nMotorbike ID : " + m.getId());
                System.out.println("Make : " + m.getMake());
                System.out.println("Model : " + m.getModel());
                System.out.println("Engine : " + m.getEngine());
                System.out.println("Frame : " + m.getFrame());
                System.out.println("Colour : " + m.getColour());
                System.out.println("Year : " + m.getYear());
                System.out.println("Price : " + m.getPrice());
            }
        } else {
            System.out.println("\nNo sales found");
        }
    }
    
    /**
     * Prints out the total sum of sales
     * @param model
     * @param sm 
     */
    private static void viewTotalSumSales(Model model, SalesManager sm) {
        System.out.println("\nTotal sum of sales : €" + sm.getTotalSumSales(model));
    }
    
    /**
     * Prints out the total number of motorbikes sold
     * @param model
     * @param sm 
     */
    private static void viewNumMotorbikesSold(Model model, SalesManager sm) {
        System.out.println("\nTotal motorbikes sold : " + sm.getNumMotorbikesSold(model).toString());
    }
    
    /**
     * Prints out a list of top performing salesmen
     * @param model
     * @param sm 
     */
    private static void viewTopSalesmen(Model model, SalesManager sm) {
        // ask the Sales Manager to gives us the top salesman
        List<TopSalesman> topSalesmen = sm.getTopSalesmen(model);
        
        int count = 1;
        
        if(topSalesmen.size() > 0) {
            for (TopSalesman t: topSalesmen) {
                System.out.println("\n******** Top Salesman #" + count + " ********");
                System.out.println("\nEmployee ID : " + t.getId());
                System.out.println("First name : " + t.getFirstName());
                System.out.println("Last name : " + t.getLastName());
                System.out.println("Total sale count : " + t.getSaleCount());
                System.out.println("Total sales value : €" + t.getTotalSalesValue());
                System.out.println("Total commission earned : €" + t.getTotalCommissionEarned());
                count++;
            }
        } else {
            System.out.println("\nNo top salesmen found");
        }
    }
    
    /**
     * Get an employee by ID
     * @param model
     * @param id
     * @return
     */
    private static Employee getEmployeeById(Model model, int id) {
        Employee e;
        e = model.getEmployeeById(id);
        return e;
    }
    
    /**
     * Get a motorbike by ID
     * @param model
     * @param id
     * @return
     */
    private static Motorbike getMotorbikeById(Model model, int id) {
        Motorbike m;
        m = model.getMotorbikeById(id);
        
        if(m == null){
            System.out.println("\nNo motorbike found with ID " + id);
        }
        
        return m;
    }
}
