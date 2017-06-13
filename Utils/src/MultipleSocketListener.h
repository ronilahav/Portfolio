/*
 * MultipleSocketListener.h
 *
 *  Created on: Jun 12, 2017
 *      Author: user
 */

#ifndef SRC_MULTIPLESOCKETLISTENER_H_
#define SRC_MULTIPLESOCKETLISTENER_H_

#include <stdio.h>
#include <vector>
#include "Socket.h"

using namespace std;
namespace networkingLab {

class MultipleSocketListener {
private:
	vector<Socket*> list;
	struct timeval tv;
	fd_set readfds;		// temp file descriptor list for select()
	fd_set master;		// master file descriptor list
	int fdmax;			// maximum file descriptor number

public:
	MultipleSocketListener();
	virtual ~MultipleSocketListener();
	void addSocket(Socket* socket);
	void addSockets(vector<Socket*> socketVec);
	Socket* listenToSocket();
	void removeSoket(Socket* socket);
	bool isInList(string ip, int port);
	vector<Socket*> getList();
};

} /* namespace networkingLab */

#endif /* SRC_MULTIPLESOCKETLISTENER_H_ */
