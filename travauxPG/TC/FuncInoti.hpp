/*
 * FuncInoti.hpp
 *
 *  Created on: Mar 18, 2015
 *      Author: kaiwen
 */

#ifndef FUNCINOTI_HPP_
#define FUNCINOTI_HPP_

#include <vector>

class FuncInoti {
public:

	FuncInoti();

	~FuncInoti();

	std::vector<char*> getSubDir(char* dir);

	int init_fd();

	int* init_wd(int fd, std::vector<char*> dirpath);

	std::vector<char*> Events_Handle(int fd, int* wd, std::vector<char*> dirpath);
};

#endif /* FUNCINOTI_HPP_ */
