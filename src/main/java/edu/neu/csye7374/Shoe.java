package edu.neu.csye7374;

interface Shoe {
    String getDescription();

    double getPrice();

    int getSize();

    int getQuantity();

    MakingStrategy getStrategy();
}
