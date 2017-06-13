/*
* View.h
 *
 *  Created on: May 28, 2017
 *      Author: user
 */

#ifndef SRC_VIEW_H_
#define SRC_VIEW_H_

namespace networkingLab {

class View {
public:
	View(){}
	virtual ~View(){}
	virtual void handleMsg(char* msg)=0;
};

} /* namespace networkingLab */

#endif /* SRC_VIEW_H_ */
