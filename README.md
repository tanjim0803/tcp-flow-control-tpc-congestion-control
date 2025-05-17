# TCP Flow and Congestion Control Simulation

A Java implementation demonstrating TCP's flow control (sliding window protocol) and congestion control mechanisms.

## Features

- **Flow Control Simulation**:
  - Implements sliding window protocol
  - Simulates frame transmission and acknowledgement
  - Dynamically adjusts window size

- **Congestion Control Simulation**:
  - Implements TCP's congestion control algorithm
  - Includes both slow start and congestion avoidance phases
  - Simulates packet loss and recovery

## Prerequisites

- Java JDK 8 or later
- Maven (for building)

## How to Run

### Flow Control Simulation
```bash
javac TCPFlowControl.java
java TCPFlowControl
