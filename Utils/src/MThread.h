/*
 * MThread.h
 *
 *  Created on: Apr 24, 2017
 *      Author: user
 */

#ifndef SRC_MTHREAD_H_
#define SRC_MTHREAD_H_

#include <pthread.h>
#include <iostream>
#include <stdio.h>

namespace networkingLab {

class MThread {
public:
	pthread_t threadId;
public:
	virtual void run() = 0;
	void start();
	void waitForThread();
	virtual ~MThread();
};

} /* namespace networkingLab */

#endif /* SRC_MTHREAD_H_ */
