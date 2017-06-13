/*
 * Socket.cpp
 *
 *  Created on: May 29, 2017
 *      Author: user
 */

#include "Socket.h"

namespace networkingLab {

Socket::Socket(int type) {
	socket_fd = socket(AF_INET, type, 0);		// Create socket
}
Socket::Socket() {
	socket_fd = 0;
}
Socket::~Socket() {}

int Socket::bind(int port) {
	bzero(&local_addr, sizeof(local_addr));
	local_addr.sin_family = AF_INET;
	local_addr.sin_port = htons(port);
	local_addr.sin_addr.s_addr = htonl(INADDR_ANY);
	if(::bind(socket_fd, (struct sockaddr *)&local_addr, sizeof(local_addr))<0)	// Bind the socket to IP+PORT
	{
		perror ("Error naming channel");
		return -1;
	}
	return 1;
}

int Socket::connect(string peerIp, int port) {
	bzero(&remote_addr, sizeof(remote_addr));
	remote_addr.sin_family = AF_INET;
	remote_addr.sin_addr.s_addr = inet_addr(peerIp.data());
	remote_addr.sin_port = htons(port);
	if (::connect(socket_fd, (struct sockaddr *)&remote_addr, sizeof(remote_addr)) < 0)
	{
		perror ("Error establishing communications");
		return -1;
	}
	return 1;
}

void Socket::close() {
	::close(socket_fd);
}
int Socket::getSock() {
	return socket_fd;
}
string Socket::getPeerIp() {
	return inet_ntoa(remote_addr.sin_addr);
}
int Socket::getPeerPort() {
	return ntohs(remote_addr.sin_port);
}
int Socket::getLocalPort() {
	struct sockaddr_in temp;
	unsigned int size = sizeof(temp);
	bzero(&temp, sizeof(temp));
	getsockname(socket_fd, (struct sockaddr *)&temp, &size);
	return ntohs(temp.sin_port);
}
string Socket::getLocalIp() {
	struct sockaddr_in temp;
	unsigned int size = sizeof(temp);
	bzero(&temp, sizeof(temp));
	getsockname(socket_fd, (struct sockaddr *)&temp, &size);
	return inet_ntoa(temp.sin_addr);
}
} /* namespace networkingLab */
