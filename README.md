# MegalomaniaK

![Java](https://img.shields.io/badge/Java-17+-blue?logo=openjdk&logoColor=white)
![Gradle](https://img.shields.io/badge/build-Gradle-blue)
![License](https://img.shields.io/badge/License-Apache_2.0-green.svg)

Experimental virtual actor model framework for Java.

## Cluster

The largest construct is the "cluster" which represents the set of nodes which participate in a single deployment.

The implementation will support single cluster only. There could be a multi-cluster deployment possibility in the future.

`Cluster` → `Nodes`

## Nodes

These are the applications that run in different server instances. A cluster consists of "N" instances.

A small cluster could be 2-3 nodes, while a bigger one could have much more.

Nodes have two socket listeners:
1. `NodeListener` that listens to traffic that is directed to it from the application running in the same JVM runtime.
2. `TransNodeListener` that listens to traffic that is directed to it from other nodes in the cluster.

## Actor

`Actor` is the basic unit of implementation where something that needs to be tracked, or messaged is given a representation as an actor.

Some common actors could be:

- User
- Player
- Device
- Vehicle
- Sensor
- Group of users
- A match in a game
- A leaderboard in a game

Etc.

Actors communicate by sending messages to each other. The idea is similar to how object-oriented programs work, but in this case the messages can either happen withing the same JVM runtime, or they can cross over network.

Each actor has an "inbox" that contains messages that have been sent to that actor. The inbox functions like a FIFO stack; first message to arrive is the first to be processed.

Actor has 4 states:
1. Inactive
2. Being activated
3. Active
4. Being inactivated

The abstract actor base class `AbstractActor` has hooks for the lifecycle stages:

- onActivate()
- onDeactivate()

