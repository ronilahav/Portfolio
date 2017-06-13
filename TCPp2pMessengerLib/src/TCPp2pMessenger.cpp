/*
 * TCPp2pMessenger.cpp
 *
 *  Created on: Apr 27, 2017
 *      Author: user
 */

#include "TCPp2pMessenger.h"

using namespace std;
namespace networkingLab {

TCPp2pMessenger::TCPp2pMessenger(int serverPort) {
	this->serverPort = serverPort;
	server = new P2PServer(this);
	client = new P2PClient();
	isActiveClient = false;
	start();
}

TCPp2pMessenger::~TCPp2pMessenger() {
	delete client;
	delete server;
}

bool TCPp2pMessenger::open (string peerIp, int port) {
	isActiveClient = client->open(peerIp, port);
	return isActiveClient;
}

void TCPp2pMessenger::send (string msg) {
	client->send(msg);
}

void TCPp2pMessenger::reply (string msg) {
	server->reply(msg);
}

void TCPp2pMessenger::close () {
	if (client != NULL)
		client->close();
	if (server != NULL)
		server->close();
}

bool TCPp2pMessenger::isActiveClientSession () {
	return isActiveClient;
}

bool TCPp2pMessenger::isActivePeerSession () {
	return server->isConnect();
}

void networkingLab::TCPp2pMessenger::run() {
	server->init(serverPort);
}

void networkingLab::TCPp2pMessenger::handleMsg(char* msg) {
	cout<< ">" << msg <<endl;
}
} /* namespace networkingLab */


