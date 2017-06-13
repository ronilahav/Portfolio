/*
 * P2PServer.cpp
 *
 *  Created on: Apr 27, 2017
 *      Author: user
 */

#include "P2PServer.h"

using namespace std;
namespace networkingLab {

P2PServer::P2PServer(View* view) {
	serverSocket = NULL;
	connectSocket = NULL;
	this->handler = view;
	runningFlag = false;
}

P2PServer::~P2PServer() {
	delete connectSocket;
	delete serverSocket;
}

void P2PServer::run() {
	char buff[256];
	bzero(buff,256);
	int recive;
	while(runningFlag)
	{
		recive = connectSocket->read(buff, 255);
		if (recive > 0)
		{
			buff[recive] = 0;
			handler->handleMsg(buff);
			bzero(buff,256);
		}
	}
}

void P2PServer::init (int port) {
	serverSocket = new TCPSocket(port);
	runningFlag = true;
	connectSocket = serverSocket->listenAndAccept();
	start();
	while(runningFlag)
		connectSocket = serverSocket->listenAndAccept();
}

void P2PServer::reply (string msg) {
	connectSocket->write(msg.data(), msg.length());
}

void P2PServer::close () {
	runningFlag = false;
	if (connectSocket != NULL)
		connectSocket->close();
	if (serverSocket != NULL)
		serverSocket->close();
}

bool networkingLab::P2PServer::isConnect() {
	if (connectSocket != NULL)
		return true;
	return false;
}
} /* namespace networkingLab */
