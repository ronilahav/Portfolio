/*
 * P2PClient.cpp
 *
 *  Created on: Apr 27, 2017
 *      Author: user
 */

#include "P2PClient.h"

using namespace std;
namespace networkingLab {

P2PClient::P2PClient() {
	clientSocket = NULL;
	runningFlag = false;
}

P2PClient::~P2PClient() {
	delete clientSocket;
}

void P2PClient::run() {
	char buff[256];
	bzero(buff,256);
	int recive;
	while(runningFlag)
	{
		recive = clientSocket->read(buff, 255);
		if (recive > 0)
		{
			buff[recive] = 0;
			cout<< ">" << buff <<endl;
			bzero(buff,256);
		}
	}
}

bool P2PClient::open (string peerIp, int port) {
	clientSocket = new TCPSocket(peerIp, port);
	if (clientSocket->getSock() > 0)
	{
		runningFlag = true;
		start();
		return true;
	}
	return false;
}

void P2PClient::send (string msg) {
	clientSocket->write(msg.data(), msg.length());
}

void P2PClient::close () {
	runningFlag = false;
	if (clientSocket != NULL)
		clientSocket->close();
}
} /* namespace networkingLab */
