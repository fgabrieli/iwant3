package local;

public class Car {
  private boolean isOn = false;
  private String brand = "";

  public void on() {
    this.isOn = true;
  }
  
  public void off() {
    this.isOn = false;
  }
  
  public boolean isOn() {
    return this.isOn;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }
  
  
}
