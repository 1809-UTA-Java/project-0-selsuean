/**
 * 
 * Class Person
 *      name
 *      username
 *      password
 * 
 * 
 *      address
 *      age
 *      birthday
 *      numAccount
 *      permission - customer, employee, admin
 *   
 *       
 *      openApplication
 *      
 * Customer extends Person
 * 
 * Employee extends Person
 * 
 * Admin extends Person 
 * 
 *      
 * Class Account
 *      account number 
 * 
 * 
 *
 *
 * public interface permissionAccess - contains a method to traverse the map->arraylist 
 * the way to implement employee and bank admin permissions 
 *
 * application as an object?     
 */


public interface layout {

public class accountInfo {
    //private variables means no way for other
    //classes to retrieve or modify these values
    //directly

    //maybe make them protected so subclasses can still have 
    //access to the value? 
    private double checkingsAmount;
    private double savingsAmount;

    //the following public methods make the
    //values available outside of this class 
    public double getCheckings() {
        return this.checkingsAmount;
    }

    public double getSavings() {
        return this.savingsAmount;
    }

}
    
}
