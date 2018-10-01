# Java-delegate

Simple example of a client delegating tasks to a server, using the 3 following collaborative protocols :
* SOURCEColl
* BYTEColl
* OBJECTColl

There are two projects :
* java-delegate-client
* java-delegate-server

First run the server, then the client. On the server side, the files received from the client will be saved on the "delegated" folder.

## java-delegate-client
The client lets the user choose the type of protocol he wants to use, as well as the method and parameters to send to the server. I used a stragey pattern for the protocol, each protocol has its own class that extend the "Strategy" abstract class.

## java-delegate-server
The server can handle the 3 protocols and call any method present in the Calculator class. The server uses a strategy pattern as well, using the same structure as the client's. 

## Execution trace
1. The server waits for incoming connections
2. The client tries to connect to the server
3. The server receives the request and launches a connection on a thread to handle the client
4. The client sends a request, stating the protocol, class name, method name and parameters to use
5. The server responds with either an aknowledgment, or an error if the request is malformed
6. The client sends the file or the serialized object to the server
7. The server receives it, compiles and/or instantiates the class if needed, does the requested operation and sends back the result. It then closes the connection and the thread
8. The client receives the result, displays it and closes the connection.