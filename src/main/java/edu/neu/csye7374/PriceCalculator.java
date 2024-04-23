package edu.neu.csye7374;

import java.util.List;

class PriceCalculator {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public double calculatePrice(List<Shoe> shoes) {
        if (command == null) {
            throw new IllegalStateException("Command not set in PriceCalculator");
        }
        return command.execute(shoes);
    }
}
