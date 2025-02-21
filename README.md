## Overview

This project is a custom implementation of HashMap and Queue data structures in Java, designed to function similarly to Java's built-in collections but without relying on pre-existing libraries like HashMap or LinkedList. The goal of this project was to gain a deeper understanding of data structures, generics, and design patterns by building a fully functional, reusable library that mirrors standard Java implementations.

## Features

- Custom HashMap – Implements a hash table with collision handling using a custom Queue (chaining technique).
- Custom Queue – Singly linked list implementation of a FIFO (First-In-First-Out) queue.
- Generic Design – Supports different data types, similar to Java’s ArrayList<>.
- Iterator Design Pattern – Provides multiple custom iterators for easy traversal of stored elements.

## Implementation Details

- Queue: Implemented as a singly linked list, supporting standard enqueue and dequeue operations.

- HashMap: Uses an array of Queue nodes to handle collisions and supports operations like put, get, and remove.

- Iterators: Custom iterator implementations allow seamless iteration through both data structures.

- Maven Project Structure: Organized with separate classes for each data structure inside src/main/java.
