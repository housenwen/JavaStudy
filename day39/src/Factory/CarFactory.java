package Factory;

public class CarFactory {
    public Car createCar(String carName){
        if (carName.contains("BMW")){
          return  new BMW();
        }
        if (carName.contains("Benz")){
           return new Benz();
        }
        return null;
    }
}
