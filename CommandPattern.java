// System design pattern: Command Pattern

import java.util.Stack;

public class CommandPattern{
    // Command pattern encapsulates a request as an object, which can be used as parameters of other objects
    public static void main(String[] args) {
    
        RemoteControl remoteControl = new RemoteControl();
    
        // requests for kitchen,patio lights
        Light kitchenLight = new Light("kitchen");
        LightOnCommand kitchenLightOnCommand = new LightOnCommand(kitchenLight);
        LightOffCommand kitchenLightOffCommand = new LightOffCommand(kitchenLight);
        
        Light patioLight = new Light("kitchen");
        LightOnCommand patioLightOnCommand = new LightOnCommand(patioLight);
        LightOffCommand patioLightOffCommand = new LightOffCommand(patioLight);
        
        remoteControl.setCommand(0, kitchenLightOnCommand, kitchenLightOffCommand);
        remoteControl.setCommand(1, patioLightOnCommand, patioLightOffCommand);
        
        remoteControl.display();
        
        
        remoteControl.pressOnButton(0);
        remoteControl.pressOffButton(0);
    
        remoteControl.undo();
        
        remoteControl.pressOnButton(1);
        remoteControl.pressOffButton(1);
        remoteControl.undo();
        remoteControl.undo();
    }
    
}

// Invoker class
class RemoteControl{
    Command[] onCommands;
    Command[] offCommands;
    
    Stack<Command> commandHistory;
    
    int size = 0;
    int _MAXSIZE = 5;
    
    public RemoteControl() {
        
        onCommands = new Command[_MAXSIZE];
        offCommands = new Command[_MAXSIZE];
        commandHistory = new Stack<Command>();
    }
    
    public void setCommand(int slot, Command onCommand, Command offCommand) {
        if ( slot >= onCommands.length) {
            System.out.printf("No enough space for slot:%d.\n", slot);
        }
        this.onCommands[slot] = onCommand;
        this.offCommands[slot] = offCommand;
        size++;
    }
    
    public void pressOnButton(int slot) {
        if (onCommands.length <= slot || this.onCommands[slot] == null) {
            System.out.printf("Invalid slot number:%d. Please input an integer between 0 and %d.\n", slot, onCommands.length);
        } else {
            onCommands[slot].execute();
            commandHistory.push(offCommands[slot]);
        }
    }
    
    public void pressOffButton(int slot) {
        if (onCommands.length <= slot || this.offCommands[slot] == null) {
            System.out.printf("Invalid slot number:%d. Please input an integer between 0 and %d.\n", slot, onCommands.length);
        } else {
            offCommands[slot].execute();
            commandHistory.push(onCommands[slot]);
        }
    }
    
    public void undo() {
        if ( commandHistory.isEmpty()) {
            System.out.println("No previous command.");
        } else {
            Command undoCommand = commandHistory.peek();
            undoCommand.execute();
            commandHistory.pop();
        }
    }
    
    public void display() {
        System.out.println("------------------ Remote Control ------------------");
        for ( int i = 0; i < size; ++i) {
            System.out.printf("[slot %d]\t%s\t%s\n",i,onCommands[i].getClass().getName(), offCommands[i].getClass().getName());
        }
    }
}


// Request interface

interface Command{
    public void execute();
}

// Command class
class LightOnCommand implements Command{
    Light light;
    
    public LightOnCommand(Light l) {
        light = l;
    }
    
    public void execute() {
        light.turnOn();
    }
}

class LightOffCommand implements Command{
    Light light;
    
    public LightOffCommand(Light l) {
        light = l;
    }
    
    public void execute() {
        light.turnOff();
    }
}




// Object class
class Light {
    String location;
    boolean onStatus;
    
    public Light() {
        location = "Default";
        onStatus = false;
    }
    
    public Light(String l) {
        location = l;
        onStatus = false;
    }
    
    public void turnOn() {
        if (onStatus) {
            System.out.println("Light was already turned on.");
        } else {
            System.out.println("Successfully turned on light.");
            onStatus = true;
        }
    }
    
    public void turnOff() {
        if (!onStatus) {
            System.out.println("Light was already turned off.");
        } else {
            System.out.println("Successfully turned off light.");
            onStatus = false;
        }
    }
}
