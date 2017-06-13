/*
 * TestTCPSocket.cpp
 *
 *  Created on: Jun 12, 2017
 *      Author: user
 */

#include "TestTCPSocket.h"

namespace networkingLab {

TestTCPSocket::TestTCPSocket() {
	server = NULL;
	connect_socket = NULL;
	client = NULL;
	stop = false;
}

TestTCPSocket::~TestTCPSocket() {
	delete server;
	delete connect_socket;
	delete client;
}

bool TestTCPSocket::test() {
	bool flag = false;
	string ip = "127.0.0.1";
	int port = 3123;

	server = new TCPSocket(port);
	stop = false;
	start();

	sleep(2);
	client = new TCPSocket(ip, port);

	string msg = "hello";
	client->write(msg.data(), msg.length());

	sleep(2);
	char buffer[256];
	bzero(buffer,256);
	int i = connect_socket->read(buffer, 255);
	buffer[i] = 0;
	if (strcasecmp(msg.data(), buffer) == 0)
		flag = true;
	else
		flag = false;

	string fromAddr = connect_socket->getPeerIp();
	if (strcasecmp(ip.data(), fromAddr.data()) == 0)
		flag = true;
	else
		flag = false;

	stop = true;
	connect_socket->close();
	client->close();
	server->close();

	return flag;
}

void TestTCPSocket::run() {
	while(!stop)
		connect_socket = server->listenAndAccept();
}

} /* namespace networkingLab */
