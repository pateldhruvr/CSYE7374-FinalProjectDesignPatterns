package edu.neu.csye7374;

import java.util.List;

// Command Pattern
interface Command {
    double execute(List<Shoe> shoes);
}
