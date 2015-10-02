// Design pattern: Observer Pattern = Subject + Observer

// Observer could subscribe or remove from Subject
// Subject would notify all observers with updates

import java.util.ArrayList;

public class SystemDesignWeatherStation{

    public static void main(String[] args) {
        // DEMO
        WeatherData weather = new WeatherData();
        
        CurrentCondition currentCondition = new CurrentCondition(weather);
        StatisticsDisplay statistics = new StatisticsDisplay(weather);
        
        weather.setMeasurements( 80, 65, 30.4f);
        weather.setMeasurements( 22, 21, 91.4f);
        weather.setMeasurements( 44, 0, 30.4f);        
    }
    
}

interface Subject{
    public void registerObserver(Observer o);
    public void removeObserver(Observer o);
    public void notifyObservers();
}

class WeatherData implements Subject{
    private ArrayList<Observer> observers;
    private float temperature;
    private float humidity;
    private float press;
    
    public WeatherData() {
        observers = new ArrayList<Observer>();
    }
    
    public void registerObserver(Observer o) {
        observers.add(o);
    }
    public void removeObserver(Observer o) {
        int index = observers.indexOf(o);
        
        if ( index >= 0) {
            observers.remove(index);
        }
        
    }
    public void notifyObservers() {
        System.out.println("WeatherData notifying observers...");
        for ( int i = 0; i < observers.size(); ++i ) {
            Observer observer = observers.get(i);
            observer.update(temperature, humidity, press);
        }
    }
    
    public void measurementsChanged() {
        notifyObservers();
    }
    
    public void setMeasurements(float t, float h, float p) {
        temperature = t;
        humidity = h;
        press = p;
        measurementsChanged();
    }
}

interface Observer{
    public void update(float temperature, float humidity, float press);
}

interface DisplayElement{
    public void display();
}

// Three different display and third-party API 

class CurrentCondition implements Observer, DisplayElement{
    private Subject weatherData;    // This is used for unregistered this Observer from the Subject
    private float temperature;
    private float humidity;
    private float press;
    
    public CurrentCondition(Subject w) {
        weatherData = w;
        w.registerObserver(this);
    }
    
    public void update(float t, float h, float p){
        temperature = t;
        humidity = h;
        press = p;
        display();
    }
    
    public void display() {
        System.out.printf("Current Conditions: Temperature: %.1fF, Humidity: %.1f%% , Press: %.1f.\n", temperature, humidity, press);
    }
}

class StatisticsDisplay implements Observer, DisplayElement{
    private Subject weatherData;    // This is used for unregistered this Observer from the Subject
    int size;
    int step;
    private float[] temperatures;
    private float avg;
    private float max;
    private float min;
    
    public StatisticsDisplay(Subject w) {
        weatherData = w;
        w.registerObserver(this);
        
        size = 0;
        step = 10;
        temperatures = new float[step];
        
        avg = 0;
        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;
        
    }
    
    public void update(float t, float h, float p){
        if ( size >= temperatures.length ) {
            float[] newtemperatures = new float[temperatures.length+step];
            
            for ( int i = 0; i < temperatures.length; ++i) {
                newtemperatures[i] = temperatures[i];
            }
            temperatures = newtemperatures;
            
        }
        
        temperatures[size] = t;
        
        avg = (avg * size + t)/(size+1);
        size++;
        
        max = max >= t? max : t;
        min = min <= t? min : t;
        
        display();
    }
    
    public void display() {
        System.out.printf("AVG/MAX/MIN Temperature: %f.1F/%.1f/%.1f.\n", avg, max, min);
    }
}

class ForcastDisplay implements Observer, DisplayElement{
    private Subject weatherData;    // This is used for unregistered this Observer from the Subject
    private float temperature;
    private float humidity;
    private float press;
    
    public ForcastDisplay(Subject w) {
        weatherData = w;
        w.registerObserver(this);
    }
    
    public void update(float t, float h, float p){
        temperature = t;
        humidity = h;
        press = p;
        display();
    }
    
    public void display() {
        System.out.printf("Current Conditions: Temperature: %fF, Humidity: %f% , Press: %f.\n", temperature, humidity, press);
    }
}

class ThirdPartyDisplay implements Observer, DisplayElement{
    private Subject weatherData;    // This is used for unregistered this Observer from the Subject
    private float temperature;
    private float humidity;
    private float press;
    
    public ThirdPartyDisplay(Subject w) {
        weatherData = w;
        w.registerObserver(this);
    }
    
    public void update(float t, float h, float p){
        temperature = t;
        humidity = h;
        press = p;
        display();
    }
    
    public void display() {
        System.out.printf("Current Conditions: Temperature: %fF, Humidity: %f% , Press: %f.\n", temperature, humidity, press);
    }
}