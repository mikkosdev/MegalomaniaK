# MegalomaniaK

![Java](https://img.shields.io/badge/Java-17+-blue?logo=openjdk&logoColor=white)
![Gradle](https://img.shields.io/badge/build-Gradle-blue)
![License](https://img.shields.io/badge/License-GPLv3-red.svg)

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

`AbstractActor` is the basic unit of implementation where something that needs to be tracked, or messaged is given a representation as an actor.

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

## To Do

- Add actor catalog
- Add MediatorJ aspects to the app
- Add inter-node catalog distribution
- Notify cluster members of new nodes
- Notify cluster members of removed nodes
- Investigate the graph structure needed for efficient inter-node communication
- Performance and load testing
  - Monitor thread creation and usage
- Create system for allowing both String based and binary based protocols for socket listeners
- Create `AbstractSocketListener` from which `NodeListener` and `TransNodeListener` inherit
- Add more unit tests
- Add integration test support
  - Investigate good pattern for folder structure and how to run them
- Add integration tests
- Investigate how the communication would work
  - What are the existing popular choices
  - Should this framework have it's own client library?
- Evaluate actor configuration options
  - The number of actor instantiations
  - Actor placement (current node vs some other node)
- Add the actor inbox processing system
- Do we need support for persistence?
    - Might be better to leave out and give examples for best practice patterns (Repository)
- Add proper patterns to the example application
- Add a system how actors can save their state
  - Manual saving via calling a method
  - Automatic saving via a timer
  - Automatic saving via some other mechanism
- Study "dirty flag" in actor to decide if the actor state needs to be saved
- Consider adding CQRS-style naming to requests
- Evaluate actor interface vs implementation class separation
- Investigate how to dispatch messages from clients
  - String-based matching?
  - Binary field?
  - Possibly both
- Design how to provide the message "pattern matching" for the socket messages (see above)

# Example Application Structure

## How the REST API code path works

- `Controller` receives the HTTP(S) request and creates an `IRequest`object and sends it via **MediatorJ** to a `Handler` object
- Request object contains everything needed to inform handler what needs to be done
- Handler object receives the request from the **MediatorJ** object
- Handler calls an actor object
- The actor does what is needed and saves it's state via a call to a `Repository` object
- Repository queries/modifies database

## How the socket code path works

- `Node` object receives the message from socket as a String to it's `handleNodeMessage()` method
- handleNodeMessage() then can can create the `IRequest` object and pass it to **MediatorJ**, which then passes it to a `Handler`object
- Handler can call an actor or `Repository` or some other object