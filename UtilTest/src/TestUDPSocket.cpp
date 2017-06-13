/*
 * TestUDPSocket.cpp
 *
 *  Created on: Jun 12, 2017
 *      Author: user
 */

#include "TestUDPSocket.h"

namespace networkingLab {

TestUDPSocket::TestUDPSocket() {}

TestUDPSocket::~TestUDPSocket() {}

bool TestUDPSocket::test() {
	bool flag = false;
	int portServer = 2123;
	int portClient = 2234;
	//create UDP server
	UDPSocket* server = new UDPSocket(portServer);

	//create udp client
	UDPSocket* client = new UDPSocket(portClient);

	//send a message from the client to the server
	string msg = "hello";
	string ip = "127.0.0.1";
	client->sendTo(msg, ip, portServer);

	// read the message by the server
	char buff[256];
	server->read(buff, 10);

	// compare the message sent with the message received
	if (strcasecmp(msg.data(), buff) == 0)
		flag = true;
	else
		flag = false;

	// reply
	string msg2 = "hey";
	char buff2[256];
	server->reply(msg2);
	client->read(buff2, 10);
	if (strcasecmp(msg2.data(), buff2) == 0)
			flag = true;
		else
			flag = false;

	//from address
	string fromAddr = server->getPeerIp();
	if (strcasecmp(ip.data(), fromAddr.data()) == 0)
		flag = true;
	else
		flag = false;

	//close all sockets
	server->close();
	client->close();

	//clear mem
	delete server;
	delete client;

	return flag;
}

} /* namespace networkingLab */
