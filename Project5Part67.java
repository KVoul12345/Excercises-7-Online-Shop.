
package project5part6.pkg7;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class Project5Part67 {

    private static final DecimalFormat df2 = new DecimalFormat(".##"); // Double variable in 2 decimal points format.  

    public static class Customer{ // Customer Class.
        private String Name,Contact,BillAd,ShipAd ; // Name , Contact Info , Billing-Shipping Address.
        private int ID ; // ID.
        Customer(String Name,int ID,String Contact,String BillAd,String ShipAd){ // Create Customer Object.
        this.Name=Name; this.Contact=Contact; this.BillAd=BillAd; this.ShipAd=ShipAd;
        this.ID=ID;
        }
        @Override  // Overriding ToString()
        public String toString(){
        return (Name + " " + ID + " " + Contact + " " + BillAd + " " + ShipAd);
        }
    }
    
    public static class Product{ // Product Class.
        private String Pname; // Product Name.
        private int PID; // Product ID.
        private double Pprice; // Product price.
        Product(String Pname,int PID , double Pprice){ // Create Product Object.
        this.Pname=Pname;
        this.PID=PID;
        this.Pprice=Pprice;
        }
        @Override // Override toString()
        public String toString(){
        return (Pname + " " + PID + " " + df2.format(Pprice)); 
        }
    }
    
    public static class Item{ // Item Class.
        private Product product; // Product Object.
        private int Iquantity; // Item Quantity.
        private double Icost ; // Item cost=price*quantity.
        Item(Product product,int Iquantity){ // Create Item Object.
            this.product=product;
            this.Iquantity=Iquantity;
            Icost=product.Pprice*Iquantity;
        }
        @Override // Override toString.
        public String toString(){
        return (product.Pname+ " " + product.PID+ " " + Iquantity + " " + df2.format(product.Pprice) + " " + df2.format(Icost));
        }        
    }
    
    public static class Invoice{ // Class Invoice.
        private List Iitems; // Item List.
        private Customer Ic; // Customer.
        private double Ipaid,Isum,Idue; // Paid Amount , Total Sum , Due.
        private int InID; // Invoice ID.
        Invoice(List Iitems,Customer Ic,int InID,double Ipaid,double Isum,double Idue){ // Create Invoice Object.
        this.Iitems=Iitems;
        this.Ic=Ic;
        this.Ipaid=Ipaid; this.Isum=Isum; this.Idue=Idue;
        this.InID=InID;
        }
        @Override // Override toString()
        public String toString(){
        return("Name: " + Ic.Name + " Invoice ID: " + InID + " Order cost: " + df2.format(Isum) 
               + " Paid: " + df2.format(Ipaid) + " Still to pay: " + df2.format(Idue) + ".");
        }
    }
    
 // METHOD : MENU   
    public static void Menu(){ 
            System.out.println("Menu:");
            System.out.println("1.Create a Customer.");
            System.out.println("2.Show Product list.");
            System.out.println("3.Add an Item to Itembasket.");
            System.out.println("4.Remove an Item from Itembasket.");
            System.out.println("5.Empty Itembasket.");
            System.out.println("6.Complete Order and Sent Invoice.");
            System.out.println("7.Print a Customer's Invoice.");
            System.out.println("8.Print all current Invoices.");
            System.out.println("9.Remove an Invoice.");
            System.out.println("10.Exit.");
    }
// METHOD : PRINT INVOICE
    public static void PrintInvoice(Invoice invoice){ 
    System.out.println("----------------------------------------------------------"); // Invoice Barrier.
    System.out.println("Invoice owner: " + invoice.Ic.Name); // Print Customer Name.
    System.out.println("Invoice ID: " + invoice.InID); line(); // Print Invoice ID.
    System.out.println("Product:  ID:      Quant: Price: Cost:"); // Print Invoice Categories.
    for(int i=0; i<invoice.Iitems.size(); i++){ // Print Items loop.
        Item x = (Item) invoice.Iitems.get(i); // Why (item) ? 
        System.out.println(x.product.Pname + " " + x.product.PID + " " + x.Iquantity + "      " // Print Product Name,ID,Quantity.
       + df2.format(x.product.Pprice) + "  " + df2.format(x.Icost) + "."); // Print Price and Cost.
    }
    line();
    System.out.println("Total cost of the Order: " + df2.format(invoice.Isum) + "."); // Print total order cost.
    System.out.println(invoice.Ic.Name + " has paid: " + df2.format(invoice.Ipaid) + "."); // Print Amount paid.
    System.out.println("Amount yet to be paid: " + df2.format(invoice.Idue) + "."); // Print Due.
    line();
    System.out.println("Order has been sent to: " + invoice.Ic.ShipAd); // Print Shipping Address.
    System.out.println("Invoice has been sent to: " + invoice.Ic.BillAd); // Print Billing Address.
    System.out.println("----------------------------------------------------------"); // Invoice Barrier.
    }
// METHOD : Skip Line.    
    public static void line(){
        System.out.println();
    }
// METHOD : MAIN.
    public static void main(String[] args) {
        List<Customer> Custlist = new ArrayList<>(); // List of all Customer Objects.
        List<Product>  Prodlist = new ArrayList<>(); // List of all Product Objects.
        List<Item>     Itemlist = new ArrayList<>(); // List of all Item Objects.
        List<Invoice>  Involist = new ArrayList<>(); // List of all Invoice Objects.
        List<Invoice>  Cinvlist = new ArrayList<>(); // List of all the Invoice Objects of 1 Customer.
        
        boolean condition = true;
        int menu = 3; // if menu = menu +3 print Menu.
        int temp = 0; // whatever needs temporary save.
        Scanner in = new Scanner(System.in); // Set Scanner.
        
       for( int i =1; i<10; i++){ // Create 9 Products for Customers to shop from.
           String pname = "Keyboard" + i;
           int ppid = new Random().nextInt(89999999)+10000000; // Set Product Id as an 8digit number.
           double pprice = new Random().nextDouble()*(89.99)+(10); // Set Product price between 10-100.
           Product product = new Product(pname,ppid,pprice); // Create Product.
           Prodlist.add(product); // Add to Product List.
       }
        while(condition){ // While (true).
            if(menu%3==0){ // Print menu every 3 actions.
                Menu(); // Print Menu.
            }
            System.out.println("Enter your Menu choice: ");
            int choice = in.nextInt();  in.nextLine(); // Menu choice.
        
            switch(choice){
                case 1: // Create Customer.
                    System.out.println("Enter your full name."); // Add Customer Name.
                    String name = in.nextLine();
                    System.out.println("Enter your ID number."); // Add Customer ID.
                    int id = in.nextInt(); in.nextLine();
                    System.out.println("Enter your contact information."); // Add Customer Contact info.
                    String contact = in.nextLine();
                    System.out.println("Enter a billing address."); // Add Billing Address.
                    String billad = in.nextLine();
                    System.out.println("Enter a shipping address."); // Add Shipping Address.
                    String shipad = in.nextLine();
                    Customer customer = new Customer(name,id,contact,billad,shipad); // Create Customer.
                    Custlist.add(customer); // Add Customer to CustomerList.
                    menu++;
                    break;
                case 2: // Print Product List.
                    System.out.println("Product:  ID:      Price:"); // Print Product Categories.
                    for(int i=0; i<Prodlist.size(); i++) // Print Product Loop
                        System.out.println(Prodlist.get(i) + ".");
                    menu++; line();
                    break;
                case 3: // Add Item to ItemBasket.
                    System.out.println("Which item do you want to add in your basket?"); // Item to add to Itembasket.
                    temp = in.nextInt();
                    System.out.println("How many of this item do you want to purchase?");
                    int iquantity = in.nextInt(); in.nextLine(); // Quantity of each Item.
                    Product product = Prodlist.get(temp-1); 
                    Item item = new Item(product,iquantity); // Create Item.
                    Itemlist.add(item); // Add Item to ItemList.
                    menu++;
                    break;
                case 4: // Remove Item from Itembasket.
                    System.out.println("Which item do you want to remove from your basket?"); // Item to remove from Itembasket.
                    for(int i=0; i<Itemlist.size(); i++) // Print Itemlist loop.
                        System.out.println(i+1 + ") " + Itemlist.get(i) + ".");
                    temp = in.nextInt(); in.nextLine(); // Which Item to remove.
                    Itemlist.remove(temp-1); // Remove Item from List.
                    menu++;
                    break;
                case 5: // Empty Itembasket.
                    Itemlist.clear(); // Empty fanction.
                    menu++;
                    break;
                case 6: // Verify Order - Create Invoice.
                    System.out.println("Which Customer are you ?"); 
                    for(int i=0; i<Custlist.size(); i++)
                        System.out.println(i+1 + ") " + Custlist.get(i)+ "."); // Print Customer List.
                    temp = in.nextInt(); in.nextLine(); // Choose Customer.
                    Customer c1 = Custlist.get(temp-1); 
                    System.out.println("How much have you already paid ?"); 
                    int inid = 1; // Invoice unique ID.
                    double paid = in.nextDouble(); in.nextLine(); // Paid Amount.
                    double sum = 0; // Total Cost.
                    for(int i=0; i<Itemlist.size(); i++){ // Calculate Sum.
                        sum = sum + Itemlist.get(i).Icost;
                    }
                    double due = sum - paid; // Due Amount.
                    Invoice invoice = new Invoice(Itemlist,c1,inid,paid,sum,due); // Create Invoice.
                    Involist.add(invoice); // Add Invoice to Invoice List.
                    System.out.println("Your order has been completed.");
                    System.out.println("Invoice has been sent to billing address.");
                    inid++; menu++;
                    break;
                case 7:   // Print a Customers Invoice.
                    System.out.println("Which Customer's invoice do you want to print?"); 
                    for(int i=0; i<Custlist.size(); i++) // Print Customer List.
                        System.out.println(i+1 + ") " + Custlist.get(i) + ".");
                    temp = in.nextInt(); in.nextLine(); // Choose Customer.
                    Customer c2 = Custlist.get(temp-1);
                    int Cinv = 0; // Amount of Customer's Invoices.
                    for( Invoice invoice2: Involist){
                        if(c2==invoice2.Ic){ // Find Invoices of specific Customer
                            System.out.println(Cinv+1 + ") " + invoice2);
                            Cinvlist.add(invoice2); // Add these Invoices to NEW List.
                            Cinv++;
                        }
                    }
                    System.out.println("Do you want a more detailed analysis?(type: yes/no).");
                    String s = in.nextLine(); // Detailed Analysis Loop
                    if(s.equals("yes") || s.equals("YES")){
                        do{
                            for(int i=0; i<Cinvlist.size(); i++){ // Print Invoices of Specific Customer.
                                System.out.println(i+1 + ") " + Cinvlist.get(i));
                            }
                            System.out.println("For Which invoice do you want a more detailed analysis?");
                            temp = in.nextInt(); in.nextLine(); // Choose Invoice.
                            PrintInvoice(Cinvlist.get(temp-1)); // Call Detail Analysis Method.
                            System.out.println("Do you want a more detailed analysis for another of this Customers Invoices?(type: yes/no).");
                            s = in.nextLine(); // Check parameter.
                        } while(s.equals("yes") || s.equals("YES"));
                    }
                    else    
                        menu++;
                        break;
                case 8: // Print ALL Invoices.
                    for(int i=0; i<Involist.size(); i++) // Print all invoices.
                        System.out.println(i+1 + ") " + Involist.get(i)); 
                    System.out.println("Do you want a more detailed analysis?(type yes/no).");
                    String s1 = in.nextLine(); // condition for loop.
                    if(s1.equals("yes") || s1.equals("YES")){
                        do{
                            System.out.println("For which invoice do you want a more detailed analysis?");
                            temp=in.nextInt(); in.nextLine(); // Choose Invoice.
                            PrintInvoice(Involist.get(temp-1)); // Call Detail Analysis Method.
                            System.out.println("Do you want a more detailed analysis for another Invoice?(type: yes/no).");
                            s1 = in.nextLine(); // Verify Condition.
                        } while(s1.equals("yes") || s1.equals("YES"));
                    }
                    
                        break;
                case 9: // Delete a Invoice.
                    System.out.println("Which Invoice do you want to remove?");
                    for(int i=0; i<Involist.size(); i++){ // Print All Invoices.
                        System.out.println(i+1 + ") " + Involist.get(i));
                    }
                    temp = in.nextInt(); in.nextLine(); // Choose Invoice to remove.
                    Involist.remove(temp-1); // Remove invoice from Invoice List.
                    break;
                case 10: // Exit the program.
                    condition = false; 
                    break;
            }
        }
    }
    
}
