# qMe
This helped us won a Hackathon organised by Coding Ninjas.

## Overview
qMe is an Android based automated Queueing System for public places like banks, hospitals, government offices, malls, etc. It aims to prevent the time and effort spent in standing in long queues. It uses bluetooth low energy (BLE) devices for the process. 

## Technologies used
Android SDK v24, Firebase, Beacons, Material Designing

## Features
1. When a user approaches a Beacon device, various available queues are shown to the user and the user can select which queue to be a part of.
2. Current queue size is displayed on each device along with number of people in front of the user in the queue.
3. When the user's turn arrives, a push notification is sent to the user for call out.
4. The documents that will be asked for the service can be conveyed in advance to the user.

## Why use Beacons?
1. Token Generator machines do not solve the problem as there might be a queue for taking a token itself. Moreover such machines are expensive.
2. GPS cannot be used for Micro-navigation, i.e. won't be able to differentiate between 2 queues next to each other.
Hence, Beacons (BLE devices) are a good solution for this problem.
