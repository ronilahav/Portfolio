/*
 * MultipleSocketListener.cpp
 *
 *  Created on: Jun 12, 2017
 *      Author: user
 */

#include "MultipleSocketListener.h"

namespace networkingLab {

MultipleSocketListener::MultipleSocketListener() {
	tv.tv_sec = 1;
	tv.tv_usec = 0;
	fdmax = 0;
}

MultipleSocketListener::~MultipleSocketListener() {}

void MultipleSocketListener::addSocket(Socket* socket) {
	list.push_back(socket);
	int sock_fd = socket->getSock();
	FD_SET(sock_fd, &master);
	if (fdmax < sock_fd)
		fdmax = sock_fd;
}

void MultipleSocketListener::addSockets(vector<Socket*> socketVec) {
	for (vector<Socket*>::iterator it = socketVec.begin(); it != socketVec.end(); ++it)
	{
		Socket* sock = *it;
		list.push_back(sock);
		int sock_fd = sock->getSock();
		FD_SET(sock_fd, &master);
		if (fdmax < sock_fd)
				fdmax = sock_fd;
	}
}

Socket* MultipleSocketListener::listenToSocket() {
	Socket* socket = NULL;
	Socket* sock = NULL;
	readfds = master; // copy it
	char buffer[256];
	bzero(buffer,256);
	if (select(fdmax+1, &readfds, NULL, NULL, &tv) == -1)
	{
		perror("select");
		exit(1);
	}
	for(int i = 0; i <= fdmax; i++) {
		if (FD_ISSET(i, &readfds)) {				// handle data from a client
			for (vector<Socket*>::iterator it = list.begin(); it != list.end(); ++it)
			{
				sock = *it;
				if (sock->getSock() == i)
				{
					socket = sock;
					return socket;
				}
			}
		}
	}
	return NULL;
}

void MultipleSocketListener::removeSoket(Socket* socket) {
	for (vector<Socket*>::iterator it = list.begin(); it != list.end(); ++it)
		if(*it == socket)
		{
			list.erase(it);
			break;
		}
}

bool MultipleSocketListener::isInList(string ip, int port) {
	for (vector<Socket*>::iterator it = list.begin(); it != list.end(); ++it)
	{
		Socket* sock = *it;
		if(sock->getPeerPort()==port && (sock->getPeerIp().compare(ip)==0))
		{
			delete sock;
			return true;
		}
	}
	return false;
}

vector<Socket*> MultipleSocketListener::getList() {
	return list;
}

} /* namespace networkingLab */


