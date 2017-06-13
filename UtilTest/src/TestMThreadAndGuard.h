/*
 * TestMThreadAndGuard.h
 *
 *  Created on: Jun 12, 2017
 *      Author: user
 */

#ifndef TESTMTHREADANDGUARD_H_
#define TESTMTHREADANDGUARD_H_

#include <iostream>
#include <stdio.h>
#include "MThread.h"
#include "Guard.h"
#include <pthread.h>
#include <unistd.h>

namespace networkingLab {

class TestMThreadAndGuard {
public:
	TestMThreadAndGuard();
	virtual ~TestMThreadAndGuard();
	bool test();
};

} /* namespace networkingLab */

#endif /* TESTMTHREADANDGUARD_H_ */
