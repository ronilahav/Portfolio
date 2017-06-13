/*
 * TestMThreadAndGuard.cpp
 *
 *  Created on: Jun 12, 2017
 *      Author: user
 */

#include "TestMThreadAndGuard.h"

using namespace std;
namespace networkingLab {

class Counter{
private:
	pthread_mutex_t mutex;
	int mcount;
public:
	Counter(){
		mutex = PTHREAD_MUTEX_INITIALIZER;
		mcount = 0;
	}
	int raize(){
		//TODO 3: remove the '//' from the next line
		Guard guard(&mutex);
		int tmp = mcount;
		sleep(1);
		mcount = tmp + 1;
		return mcount;
	}
	int count(){
		return mcount;
	}
};

class MyThread : public MThread{
	bool runningFlag;
	int count;
	Counter* sharedCount;
public:
	MyThread(Counter* scount){
		runningFlag = true;
		count = 0;
		sharedCount = scount;
	}

	void run(){
		while(runningFlag){
			//cout<<"thread "<< this->threadId  <<" is running: " << count << " scount:"<<sharedCount->count()<<endl;
			count++;
			sharedCount->raize();
			if(count == 10) break;
		}
		//cout<<"thread "<<this->threadId<<" exit with count:"<<count<<endl;
	}
};

TestMThreadAndGuard::TestMThreadAndGuard() {}

TestMThreadAndGuard::~TestMThreadAndGuard() {}

bool TestMThreadAndGuard::test() {
	//TODO 1: add your names instead of 'student # name' in the next line
	//cout << "Lab 2 submitted by: Roni Lahav" << endl;
	Counter scount;
	MyThread* mt1 = new MyThread(&scount);
	MyThread* mt2 = new MyThread(&scount);
	mt1->start();
	mt2->start();
	//TODO 2: remove the '//' from the next two lines
	mt1->waitForThread();
	mt2->waitForThread();
	delete mt1;
	delete mt2;
	return true;
}

} /* namespace networkingLab */
