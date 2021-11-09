package com.company;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Groceries[] list = readTxt();
        double[] pricesPrProduct = pricePrProduct(list);
        double[] discountedPricesPrProduct = discountedPricePrProduct(list);

        totalPrice(pricesPrProduct);
        totalDiscountedPrice(discountedPricesPrProduct);

        receipt(list, pricesPrProduct, discountedPricesPrProduct);
    }

    public static Groceries[] readTxt() throws IOException {
        int count = 0;
        Groceries[] list = new Groceries[5];
        Scanner input = new Scanner(new File("grocerielist.txt"));

        while(input.hasNext()) {
            int amount = input.nextInt();
            String product = input.next();
            double price = input.nextDouble();

            Groceries newGrocerie = new Groceries(amount, product, price);
            list[count] = newGrocerie;
            count++;
        }
        return list;

    }

    public static void printObject(Groceries grocerie) {
        System.out.println(grocerie.getAmount() + " " + grocerie.getProduct() + " " + grocerie.getPrice());
    }

    public static void printObjectArray(Groceries[] groceries) {
        for (Groceries grocery : groceries) {
            System.out.println(grocery.getAmount() + " " + grocery.getProduct() + " " + grocery.getPrice());
        }
    }

    public static double[] pricePrProduct(Groceries[] groceries) {
        double[] prices = new double[groceries.length];
        for (int i = 0; i < groceries.length; i++) {
            double price = groceries[i].getAmount() * groceries[i].getPrice();
//            System.out.println("The total price without discount for " + groceries[i].getProduct() + " is: " + price);

            prices[i] = price;
        }
        return prices;
    }

    public static double[] discountedPricePrProduct(Groceries[] groceries) {
        double[] prices = new double[groceries.length];
        for (int i = 0; i < groceries.length; i++) {
            double price = groceries[i].getAmount() * groceries[i].getPrice();

            if (groceries[i].getAmount() >= 10) {
                price *= 0.85;
//                System.out.println("You get a discount on " + groceries[i].getProduct() + ". The price is now: " + price);
            } else if (groceries[i].getAmount() < 10) {
//                System.out.println("You haven't earned a discount on " + groceries[i].getProduct() + ". The price is: " + price);
            }

            prices[i] = price;
        }
        return prices;
    }

    public static double totalPrice(double[] prices) {
        double total = 0;
        for (double price : prices) {
            total += price;
        }
//        System.out.println("The total price is: " + total);
        return total;
    }

    public static double totalDiscountedPrice(double[] prices) {
        double total = 0;
        for (double price : prices) {
            total += price;
        }
//        System.out.println("The total discounted price is: " + total);
        return total;
    }

    public static void receipt(Groceries[] groceries, double[] prices, double[] discountPrices) {
        try {
            FileWriter input = new FileWriter("receipt.txt");
            for (int i = 0; i < groceries.length; i++) {
                input.write("Product: " + groceries[i].getProduct() + "\n");
                input.write("Amount: " + groceries[i].getAmount() + "\n");
                input.write("Price pr unit: " + groceries[i].getPrice() + "\n");
                input.write("Total price without discount: " + prices[i] + "\n");
                if (groceries[i].getAmount() >= 10) {
                    input.write("Total price with discount: " + discountPrices[i] + "\n");
                }
                input.write("\n\n");
            }
            input.write("The total price without discount: " + totalPrice(prices) + "\n");
            input.write("The total price with discount: " + totalDiscountedPrice(discountPrices) + "\n\n");
            input.write("* Discount is achieved when you buy 10 or more units of the same product.");
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        readFile();
    }

    public static void readFile() {
        try {
            File output = new File("receipt.txt");
            Scanner scanner = new Scanner(output);
            while (scanner.hasNext()) {
                System.out.println(scanner.nextLine());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
