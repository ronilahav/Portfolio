/*
 * TestMultipleSocketListener.cpp
 *
 *  Created on: Jun 12, 2017
 *      Author: user
 */

#include "TestMultipleSocketListener.h"

string message; // the message sent
bool testFail;  // flag that mark if the test pass or not

using namespace std;
namespace networkingLab {

class TCPServer:public MThread{
	vector<Socket*> serverSockets; // vector to hold all the sockets
public:
	TCPServer(int port){
		// create the server sockets
		for (int i=0;i<TEST_SIZE;i++){
			TCPSocket* newSock = new TCPSocket(port+i);
			serverSockets.push_back(newSock);
		}
	}

	void run(){
		int msgLen;
		char buffer[100];
		bzero(buffer,100);

		// create a MultipleTCPSocketsListener and add the sockets to it
		MultipleSocketListener* mspServers = new MultipleSocketListener();
		mspServers->addSockets(serverSockets);

		for(int j=0;j<TEST_SIZE;j++){
			// use MultipleTCPSocketsListener to find the socket that is ready
			TCPSocket* socket = (TCPSocket*)mspServers->listenToSocket();

			// perform listen and accept on the ready socket
			TCPSocket* newSock = socket->listenAndAccept();

			// receive the incoming message
			newSock->read((char*)&msgLen,4);
			msgLen = ntohl(msgLen);
			int rc = newSock->read(buffer, msgLen);

			// compare the incoming message to the global message var
			if (rc != (int)message.length() || message != buffer){
				testFail = true;
			}

			// create a new message
			message = "Test 0987654321";

			// send the message back to the client
			msgLen = htonl(message.length());
			newSock->write((char*)&msgLen, 4);
			newSock->write(message.data(), message.length());

			// close and delete the new peer socket
			newSock->close();
			delete newSock;
		}
	}
	~TCPServer(){
		//close and delete all server sockets
		for (int i=0;i<TEST_SIZE;i++){
			serverSockets[i]->close();
			delete serverSockets[i];
		}
	}
};

TestMultipleSocketListener::TestMultipleSocketListener() {}

TestMultipleSocketListener::~TestMultipleSocketListener() {}

bool TestMultipleSocketListener::test() {
	testFail = false;
	int port = 44444;
	// create and start the server part
	TCPServer* tcpServer = new TCPServer(port);
	tcpServer->start();
	sleep(2);	//give a chance for the TCP server to start

	//call the client test TEST_SIZE times
	for (int i=0;i<TEST_SIZE;i++){
		//cout << "testing client #" <<i<< endl;
		createClientAndSendRecvMsg(port+i);
	}

	//wait for the server to close and delete it
	tcpServer->waitForThread();
	delete tcpServer;
	return !testFail;
}

void TestMultipleSocketListener::createClientAndSendRecvMsg(int port) {
	//create client TCP socket named cSocket
	TCPSocket* cSocket1 = new TCPSocket("127.0.0.1",port);

	// this part test the send methods
	message = "Test 1234567890";
	//cout<<"sending message:"<<message<<endl;
	int msgLen = htonl(message.length());
	cSocket1->write((char*)&msgLen, 4);
	cSocket1->write(message.data(), message.length());

	char buffer[100];
	bzero(buffer,100);

	MultipleSocketListener* msp = new MultipleSocketListener();
	msp->addSocket(cSocket1);

	for (int i=0;i<1;i++){
		TCPSocket* readtCSocket = (TCPSocket*)msp->listenToSocket();
		readtCSocket->read((char*)&msgLen,4);
		msgLen = ntohl(msgLen);
		int rc = readtCSocket->read(buffer, msgLen);
		//cout<<"recv msg:"<<buffer<<endl;
		if (rc != (int)message.length()){
			perror("FAIL1: receive different message length");
			testFail = true;
		}
		if (message != buffer){
			perror("FAIL2: receive different message");
			testFail = true;
		}
	}
	cSocket1->close();
}

} /* namespace networkingLab */
