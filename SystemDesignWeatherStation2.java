// Design pattern: Observer pattern using Java Plugin


// Java PlugIn: Observable, Observer
// Observable: setChange(), notifyObservers(), deleteObserver(ob), deleteObservers();
// Observer: update(Observable,args){ if (observer instanceof w) then ...}=> ignore message from unmatched Subject

import java.util.ArrayList;

import java.util.Observable;
import java.util.Observer;


public class SystemDesignWeatherStation2{

    public static void main(String[] args) {
        // DEMO
        WeatherData weather = new WeatherData();
        
        CurrentCondition currentCondition = new CurrentCondition(weather);
        StatisticsDisplay statistics = new StatisticsDisplay(weather);
        
        weather.setMeasurements( 80, 65, 30.4f);
        weather.setMeasurements( 22, 21, 91.4f);
        weather.setMeasurements( 44, 0, 30.4f);     
        
        currentCondition.remove();   
    }
    
}

class WeatherData extends Observable{
    // Private data member
    private float temperature;
    private float humidity;
    private float press;
    
    public WeatherData() {}
    
    public void measurementsChanged() {
        // Observable uses setChanged() to set changed filed
        setChanged();
        
        // notifyObservers() is provided by Observable class
        // if(changed) {for every observers on list call update(this, arg)}
        // Then set changed = false
        
        // If there's no observers then call notify
        notifyObservers();
        
    }
    
    public void setMeasurements(float t, float h, float p) {
        temperature = t;
        humidity = h;
        press = p;
        measurementsChanged();
    }
    
    
    // Getter
    
    public float getTemperature() {
        return temperature;
    }
    
    public float getHumidity() {
        return humidity;
    }
    
    public float getPress() {
        return press;
    }
    
    
}




//-----------------Display behaviors-------------------//

interface DisplayElement{
    public void display();
}

class CurrentCondition implements Observer, DisplayElement{
    private Observable ob;
    private float temperature;
    private float humidity;
    private float press;
    
    public CurrentCondition(Observable observable) {
        this.ob = observable;
        
        // Register this observer to observable subject
        ob.addObserver(this);
    }
    
    public void remove() {
        this.ob.deleteObserver(this);
        ob = null;
    }
    
    public void update(Observable obs, Object arg) {
    
        // Make sure the Subject is an instance of WeatherData
        if (obs instanceof WeatherData) {
            WeatherData w = (WeatherData) obs;
            
            // Get data from the Subject
            this.temperature = w.getTemperature();
            this.humidity = w.getHumidity();
            this.press = w.getPress();
            
            display();
        }
    }
    
    public void display() {
        System.out.printf("Current Conditions: Temperature: %fF, Humidity: %f%% , Press: %f.\n", temperature, humidity, press);
    }
    
}


class StatisticsDisplay implements Observer, DisplayElement{
    private Observable weatherData;    // This is used for unregistered this Observer from the Subject
    int size;
    
    private float avg;
    private float max;
    private float min;
    
    public StatisticsDisplay(Observable observable) {
        this.weatherData = observable;
        this.weatherData.addObserver(this);
        
        size = 0;
        
        avg = 0;
        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;
        
    }
    
    public void update(Observable ob, Object arg){
        
        if ( ob instanceof WeatherData) {
            WeatherData w = (WeatherData) ob;
            
            float t = w.getTemperature();
            
            avg = (avg * size + t)/(size+1);
            size++;
        
            max = max >= t? max : t;
            min = min <= t? min : t;
        
            display();
        }
    }
    
    public void display() {
        System.out.printf("AVG/MAX/MIN Temperature: %f.1F/%.1f/%.1f.\n", avg, max, min);
    }
}

