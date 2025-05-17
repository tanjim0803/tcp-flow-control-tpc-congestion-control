/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oop.tcp.control;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Tanjim Ahmed
 */

class CongestionControl {
    Random randomGenerator = new Random();

    boolean isAckReceived() {
        int probability = randomGenerator.nextInt(10);
        if (probability < 8) {
            return true;  // 80% chance of ACK
        } else {
            return false; // 20% chance of packet loss
        }
    }
}

public class TCPCongestionControl {
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        CongestionControl controller = new CongestionControl();

        System.out.print("Enter total number of data packets to transmit: ");
        int totalPackets = inputScanner.nextInt();

        int congestionWindow = 1;
        int slowStartThreshold = 8;
        int transmittedPackets = 0;

        while (transmittedPackets < totalPackets) {
            System.out.println("\nCurrent congestion window size: " + congestionWindow + " packets");

            for (int i = 0; i < congestionWindow && transmittedPackets < totalPackets; i++) {
                int currentPacket = transmittedPackets + 1;
                System.out.print("Attempting to send packet " + currentPacket + "... ");

                if (controller.isAckReceived()) {
                    System.out.println("Successfully acknowledged!");
                    transmittedPackets++;
                } else {
                    System.out.println("Packet lost! Network congestion detected.");
                    slowStartThreshold = congestionWindow / 2;
                    if (slowStartThreshold < 1) slowStartThreshold = 1;
                    congestionWindow = 1;
                    break;
                }
            }

            if (congestionWindow < slowStartThreshold) {
                congestionWindow = congestionWindow * 2; // Slow start phase
            } else {
                congestionWindow = congestionWindow + 1; // Congestion avoidance phase
            }
        }

        System.out.println("\nAll data packets transmitted and acknowledged successfully!");
    }
}
