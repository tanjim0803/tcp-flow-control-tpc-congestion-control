/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oop.tcp.control;
import static java.lang.Math.random;
import java.util.Scanner;
import java.util.Random;
/**
 *
 * @author Tanjim Ahmed
 */

public class TCPFlowControl {

    public static int generateFrames(int windowSize) {
        Random random = new Random();
        int generatedFrames = random.nextInt(500) % windowSize;

        if (generatedFrames == 0)
            return windowSize;
        else
            return generatedFrames;
    }

    public static int generateAcks(int sentFrames) {
        Random random = new Random();
        int acknowledgedFrames = (int) (random.nextInt(500) % sentFrames);
        return acknowledgedFrames;
    }

    public static void main(String[] args) {
        int totalFrames, windowSize, firstUnacknowledged = 0, lastSent = 0, acknowledgedFrames = 0, sentFrames = 0;

        Scanner scn = new Scanner(System.in);

        System.out.println("Enter the total number of frames to transmit: ");
        totalFrames = scn.nextInt();

        System.out.println("Enter the sliding window size: ");
        windowSize = scn.nextInt();

        int remainingFrames = totalFrames;

        while (remainingFrames >= 0) {
            sentFrames = generateFrames(windowSize);
            lastSent += sentFrames;

            if (lastSent > totalFrames)
                lastSent = totalFrames;

            for (int i = firstUnacknowledged + 1; i <= lastSent; i++) {
                System.out.println("Transmitting frame number " + i);
            }

            acknowledgedFrames = generateAcks(sentFrames);
            firstUnacknowledged += acknowledgedFrames;

            if (firstUnacknowledged > totalFrames)
                firstUnacknowledged = totalFrames;

            System.out.println("Received acknowledgements for frames up to " + firstUnacknowledged);

            remainingFrames -= acknowledgedFrames;
            lastSent = firstUnacknowledged;
        }

        System.out.println("\nSliding Window Protocol simulation completed successfully.");
    }
}
