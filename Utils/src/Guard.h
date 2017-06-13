/*
 * Guard.h
 *
 *  Created on: Apr 24, 2017
 *      Author: user
 */

#ifndef SRC_GUARD_H_
#define SRC_GUARD_H_

#include <pthread.h>

namespace networkingLab {
/**
 * To init a mutex use:	pthread_mutex_t	mutex = PTHREAD_MUTEX_INITIALIZER;
 */
class Guard {
	pthread_mutex_t* mutex;
public:
	Guard(pthread_mutex_t* m){
		mutex = m;
		pthread_mutex_lock(mutex);
	}
	~Guard(){
		pthread_mutex_unlock(mutex);
	}
};

} /* namespace networkingLab */

#endif /* SRC_GUARD_H_ */
